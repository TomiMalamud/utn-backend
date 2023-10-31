package com.example.preparcial.services;

import com.example.preparcial.model.MediaType;

import java.util.List;
import java.util.Optional;

public interface MediaTypeService {

    MediaType create(
            String name
    );

    List<MediaType> findAll();

    Optional<MediaType> findById(Integer mediaTypeId);

    void delete(Integer mediaTypeId);

    void update(
            Integer mediaTypeId,
            String name
    );
}
