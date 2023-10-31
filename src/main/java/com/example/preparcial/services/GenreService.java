package com.example.preparcial.services;

import com.example.preparcial.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(
            String name
    );

    List<Genre> findAll();

    Optional<Genre> findById(Integer genreId);

    void delete(Integer genreId);

    void update(
            Integer genreId,
            String name
    );
}
