package com.example.preparcial.services;

import com.example.preparcial.model.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    Track create(
            String name,
            Integer albumId,
            Integer mediaTypeId,
            Integer genreId,
            String composer,
            Integer milliseconds,
            Integer bytes,
            Double unitPrice
    );

    List<Track> findAllByCustomerId(Integer customerId);


    List<Track> findAll();

    Optional<Track> findById(Integer trackId);

    void delete(Integer trackId);

    void update(
            Integer trackId,
            String name,
            Integer albumId,
            Integer mediaTypeId,
            Integer genreId,
            String composer,
            Integer milliseconds,
            Integer bytes,
            Double unitPrice
    );
}
