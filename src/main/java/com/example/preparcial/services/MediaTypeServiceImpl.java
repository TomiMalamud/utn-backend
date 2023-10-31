package com.example.preparcial.services;

import com.example.preparcial.model.MediaType;
import com.example.preparcial.repositories.IdentifierRepository;
import com.example.preparcial.repositories.MediaTypeRepository;
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
public class MediaTypeServiceImpl implements MediaTypeService {

    MediaTypeRepository mediaTypeRepository;
    IdentifierRepository identifierRepository;

    @Override
    @Transactional
    public MediaType create(final String name) {
        val mediaTypeId = identifierRepository.nextValue(MediaType.TABLE_NAME);
        val mediaType = new MediaType(mediaTypeId, name);

        return mediaTypeRepository.save(mediaType);
    }

    @Override
    public List<MediaType> findAll() {
        return mediaTypeRepository.findAll();
    }

    @Override
    public Optional<MediaType> findById(final Integer mediaTypeId) {
        return mediaTypeRepository.findById(mediaTypeId);
    }

    @Override
    @Transactional
    public void delete(final Integer mediaTypeId) {
        mediaTypeRepository.deleteById(mediaTypeId);
    }

    @Override
    @Transactional
    public void update(final Integer mediaTypeId,
                       final String name) {
        val mediaType = mediaTypeRepository.findById(mediaTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Media Type not found"));

        mediaType.update(name);

        mediaTypeRepository.save(mediaType);
    }
}
