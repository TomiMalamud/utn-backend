package com.example.preparcial.services;

import com.example.preparcial.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    Album create(
            String title,
            Integer artistId
    );

    List<Album> findAll();

    Optional<Album> findById(Integer albumId);

    void delete(Integer albumId);

    void update(
            Integer albumId,
            String title,
            Integer artistId
    );
}
