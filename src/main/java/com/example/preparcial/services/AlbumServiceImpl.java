package com.example.preparcial.services;

import com.example.preparcial.model.Album;
import com.example.preparcial.repositories.AlbumRepository;
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
public class AlbumServiceImpl implements AlbumService {

    AlbumRepository albumRepository;
    IdentifierRepository identifierRepository;
    ArtistService artistService;

    @Override
    @Transactional
    public Album create(final String title,
                        final Integer artistId) {

        val artist = artistService.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        val albumId = identifierRepository.nextValue(Album.TABLE_NAME);
        val album = new Album(albumId, title, artist);

        return albumRepository.save(album);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(final Integer albumId) {
        return albumRepository.findById(albumId);
    }

    @Override
    @Transactional
    public void delete(final Integer albumId) {
        albumRepository.deleteById(albumId);
    }

    @Override
    @Transactional
    public void update(final Integer albumId,
                       final String title,
                       final Integer artistId) {
        val artist = artistService.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        val album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        album.update(title, artist);

        albumRepository.save(album);
    }
}
