package com.example.preparcial.services;

import com.example.preparcial.model.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {

    Playlist create(
            String name
    );

    Playlist createCustomPlaylist(String name, Integer customerId, String composerFilter, Long maxDuration);

    List<Playlist> findAll();

    Optional<Playlist> findById(Integer id);

    void delete(Integer id);

    void update(
            Integer id,
            String name
    );
}
