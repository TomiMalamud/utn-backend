package com.example.preparcial.services;

import com.example.preparcial.model.Playlist;
import com.example.preparcial.model.PlaylistTrack;
import com.example.preparcial.model.PlaylistTrackId;
import com.example.preparcial.model.Track;
import com.example.preparcial.repositories.PlaylistRepository;
import com.example.preparcial.repositories.PlaylistTrackRepository;
import com.example.preparcial.repositories.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistTrackServiceImpl implements PlaylistTrackService {

    private final PlaylistTrackRepository playlistTrackRepository;
    private final PlaylistRepository playlistRepository;
    private final TrackRepository trackRepository;

    @Override
    @Transactional
    public PlaylistTrack addTrackToPlaylist(Integer playlistId, Integer trackId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Playlist Id"));
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Track Id"));

        PlaylistTrackId newId = new PlaylistTrackId(playlistId, trackId);
        PlaylistTrack newPlaylistTrack = new PlaylistTrack(newId, playlist, track);

        return playlistTrackRepository.save(newPlaylistTrack);
    }

    @Override
    public List<PlaylistTrack> findAll() {
        return playlistTrackRepository.findAll();
    }

    @Override
    @Transactional
    public void removeTrackFromPlaylist(PlaylistTrackId playlistTrackId) {
        playlistTrackRepository.deleteById(playlistTrackId);
    }
}
