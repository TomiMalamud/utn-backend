package com.example.preparcial.services;

import com.example.preparcial.model.Genre;
import com.example.preparcial.repositories.GenreRepository;
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
public class GenreServiceImpl implements GenreService {

    GenreRepository genreRepository;
    IdentifierRepository identifierRepository;

    @Override
    @Transactional
    public Genre create(final String name) {
        val genreId = identifierRepository.nextValue(Genre.TABLE_NAME);
        val genre = new Genre(genreId, name);

        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(final Integer genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    @Transactional
    public void delete(final Integer genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    @Transactional
    public void update(final Integer genreId, final String name) {
        val genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));

        genre.update(name);

        genreRepository.save(genre);
    }
}
