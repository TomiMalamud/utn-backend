package com.example.preparcial.services;

import com.example.preparcial.model.Artist;
import com.example.preparcial.repositories.ArtistRepository;
import com.example.preparcial.repositories.IdentifierRepository;
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
public class ArtistServiceImpl implements ArtistService {

    ArtistRepository artistRepository;
    IdentifierRepository identifierRepository;

    @Override
    @Transactional
    public Artist create(final String name) {
        val artistId = identifierRepository.nextValue(Artist.TABLE_NAME);
        val artist = new Artist(artistId, name);

        return artistRepository.save(artist);
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Optional<Artist> findById(final Integer artistId) {
        return artistRepository.findById(artistId);
    }

    @Override
    @Transactional
    public void delete(final Integer artistId) {
        artistRepository.deleteById(artistId);
    }

    @Override
    @Transactional
    public void update(final Integer artistId,
                       final String name) {
        val artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));

        artist.update(name);

        artistRepository.save(artist);
    }
}
