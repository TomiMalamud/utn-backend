package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreatePlaylistRequest;
import com.example.preparcial.application.request.CustomPlaylistRequest;
import com.example.preparcial.application.request.UpdatePlaylistRequest;
import com.example.preparcial.application.response.PlaylistResponse;
import com.example.preparcial.services.PlaylistService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PlaylistController {
    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);
    PlaylistService playlistService;

    @PostMapping("/custom")
    public ResponseEntity<Object> createCustomPlaylist(@RequestBody CustomPlaylistRequest aRequest) {
        try {
            val playlist = playlistService.createCustomPlaylist(
                    aRequest.getName(),
                    aRequest.getCustomerId(),
                    aRequest.getComposerFilter(),
                    aRequest.getMaxDuration()
            );

            if (playlist == null) {
                return ResponseHandler.notFound();
            } else if (playlist.getPlaylistId() == null) {
                return ResponseHandler.noContent();
            } else {
                return ResponseHandler.success(PlaylistResponse.from(playlist));
            }
        } catch (Exception e) {
            logger.error("An error occurred while creating a custom playlist: ", e);
            return ResponseHandler.internalError();
        }

    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val playlists = playlistService.findAll()
                    .stream()
                    .map(PlaylistResponse::from)
                    .toList();

            return ResponseHandler.success(playlists);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreatePlaylistRequest aRequest) {
        try {
            val playlist = playlistService.create(
                    aRequest.getName());

            return ResponseHandler.success(PlaylistResponse.from(playlist));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return playlistService.findById(id)
                    .map(aPlaylist -> ResponseHandler.success(PlaylistResponse.from(aPlaylist)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            playlistService.delete(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdatePlaylistRequest aRequest) {
        try {
            playlistService.update(
                    id,
                    aRequest.getName());
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}