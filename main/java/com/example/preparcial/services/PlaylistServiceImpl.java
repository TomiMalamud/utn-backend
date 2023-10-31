package com.example.preparcial.services;

import com.example.preparcial.model.Playlist;
import com.example.preparcial.model.Track;
import com.example.preparcial.repositories.IdentifierRepository;
import com.example.preparcial.repositories.InvoiceRepository;
import com.example.preparcial.repositories.PlaylistRepository;
import com.example.preparcial.repositories.TrackRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    PlaylistRepository playlistRepository;
    IdentifierRepository identifierRepository;
    TrackRepository trackRepository;
    InvoiceRepository invoiceRepository;
    PlaylistTrackService playlistTrackService;

    @Override
    @Transactional
    public Playlist create(final String name) {

        val playlistId = identifierRepository.nextValue(Playlist.TABLE_NAME);
        val playlist = new Playlist(playlistId, name);

        return playlistRepository.save(playlist);
    }


    @Override
    @Transactional
    public Playlist createCustomPlaylist(String name, Integer customerId, String composerFilter, Long maxDuration) {

        List<Integer> invoiceIds = invoiceRepository.findInvoiceIdsByCustomerId(customerId);

        if (invoiceIds.isEmpty()) {
            return null;
        }

        List<Track> eligibleTracks = trackRepository.findAllByInvoiceIdsAndComposerFilter(invoiceIds, composerFilter);

        val playlistId = identifierRepository.nextValue(Playlist.TABLE_NAME);
        Playlist playlist = new Playlist(playlistId, name);
        playlistRepository.save(playlist);

        Long totalDuration = 0L;
        for (Track track : eligibleTracks) {
            if (totalDuration + track.getMilliseconds() <= maxDuration) {
                playlistTrackService.addTrackToPlaylist(playlist.getPlaylistId(), track.getTrackId());
                totalDuration += track.getMilliseconds();
            }
        }

        return playlist;
    }

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Optional<Playlist> findById(final Integer id) {
        return playlistRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(final Integer id) {
        playlistRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(final Integer id,
                       final String name) {

        val playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("playlist not found"));

        playlist.update(name);

        playlistRepository.save(playlist);
    }
}
