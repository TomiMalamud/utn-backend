package com.example.preparcial.services;

import com.example.preparcial.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    Artist create(
            String name
    );

    List<Artist> findAll();

    Optional<Artist> findById(Integer artistId);

    void delete(Integer artistId);

    void update(
            Integer artistId,
            String name
    );
}
