package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateAlbumRequest;
import com.example.preparcial.application.request.UpdateAlbumRequest;
import com.example.preparcial.application.response.AlbumResponse;
import com.example.preparcial.model.Album;
import com.example.preparcial.services.AlbumService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlbumController {
    AlbumService albumService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val albums = albumService.findAll()
                    .stream()
                    .map(AlbumResponse::from)
                    .toList();

            return ResponseHandler.success(albums);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateAlbumRequest aRequest) {
        try {
            val album = albumService.create(
                    aRequest.getTitle(),
                    aRequest.getArtistId());

            return ResponseHandler.success(AlbumResponse.from((Album) album));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return albumService.findById(id)
                    .map(aAlbum -> ResponseHandler.success(AlbumResponse.from(aAlbum)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            albumService.delete(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateAlbumRequest aRequest) {
        try {
            albumService.update(
                    id,
                    aRequest.getTitle(),
                    aRequest.getArtistId());
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}