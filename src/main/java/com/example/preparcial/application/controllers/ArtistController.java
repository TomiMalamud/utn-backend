package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateArtistRequest;
import com.example.preparcial.application.request.UpdateArtistRequest;
import com.example.preparcial.application.response.ArtistResponse;
import com.example.preparcial.model.Artist;
import com.example.preparcial.services.ArtistService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artists")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ArtistController {
    ArtistService artistService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val artists = artistService.findAll()
                    .stream()
                    .map(ArtistResponse::from)
                    .toList();

            return ResponseHandler.success(artists);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateArtistRequest aRequest) {
        try {
            val artist = artistService.create(
                    aRequest.getName());

            return ResponseHandler.success(ArtistResponse.from((Artist) artist));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return artistService.findById(id)
                    .map(aArtist -> ResponseHandler.success(ArtistResponse.from(aArtist)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            artistService.delete(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateArtistRequest aRequest) {
        try {
            artistService.update(
                    aRequest.getArtistId(),
                    aRequest.getName());
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}