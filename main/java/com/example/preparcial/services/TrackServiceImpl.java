package com.example.preparcial.services;

import com.example.preparcial.model.Track;
import com.example.preparcial.repositories.IdentifierRepository;
import com.example.preparcial.repositories.InvoiceRepository;
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
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;
    IdentifierRepository identifierRepository;
    AlbumService albumService;
    MediaTypeService mediaTypeService;
    GenreService genreService;
    InvoiceRepository invoiceRepository;
    CustomerService customerService;

    @Override
    @Transactional
    public Track create(final String name,
                        final Integer albumId,
                        final Integer mediaTypeId,
                        final Integer genreId,
                        final String composer,
                        final Integer milliseconds,
                        final Integer bytes,
                        final Double unitPrice) {

        val album = albumService.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        val mediaType = mediaTypeService.findById(mediaTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Media Type not found"));
        val genre = genreService.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        val trackId = identifierRepository.nextValue(Track.TABLE_NAME);
        val track = new Track(trackId, name, album, mediaType, genre, composer, milliseconds, bytes, unitPrice);

        return trackRepository.save(track);
    }

    @Override
    public List<Track> findAllByCustomerId(Integer customerId) {
        if (!customerService.existsById(customerId)) {
            throw new IllegalArgumentException("Customer not found");
        }

        List<Integer> invoiceIds = invoiceRepository.findInvoiceIdsByCustomerId(customerId);

        return trackRepository.findAllByInvoiceIds(invoiceIds);
    }


    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public Optional<Track> findById(final Integer trackId) {
        return trackRepository.findById(trackId);
    }

    @Override
    @Transactional
    public void delete(final Integer trackId) {
        trackRepository.deleteById(trackId);
    }

    @Override
    @Transactional
    public void update(final Integer trackId,
                       final String name,
                       final Integer albumId,
                       final Integer mediaTypeId,
                       final Integer genreId,
                       final String composer,
                       final Integer milliseconds,
                       final Integer bytes,
                       final Double unitPrice) {
        val album = albumService.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        val mediaType = mediaTypeService.findById(mediaTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Media Type not found"));
        val genre = genreService.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        val track = trackRepository.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));

        track.update(name, album, mediaType, genre, composer, milliseconds, bytes, unitPrice);

        trackRepository.save(track);
    }
}
