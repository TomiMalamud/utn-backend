package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreatePlaylistTrackRequest;
import com.example.preparcial.application.response.PlaylistTrackResponse;
import com.example.preparcial.model.PlaylistTrack;
import com.example.preparcial.model.PlaylistTrackId;
import com.example.preparcial.services.PlaylistTrackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlist_track")
@RequiredArgsConstructor
public class PlaylistTrackController {

    private final PlaylistTrackService playlistTrackService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val customers = playlistTrackService.findAll()
                    .stream()
                    .map(PlaylistTrackResponse::from)
                    .toList();

            return ResponseHandler.success(customers);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<PlaylistTrackResponse> addTrackToPlaylist(@Valid @RequestBody CreatePlaylistTrackRequest aRequest) {
        try {
            PlaylistTrack addedTrack = playlistTrackService.addTrackToPlaylist(
                    aRequest.getPlaylistId(), aRequest.getTrackId());
            return ResponseEntity.ok(PlaylistTrackResponse.from(addedTrack));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeTrackFromPlaylist(@RequestBody PlaylistTrackId playlistTrackId) {
        try {
            playlistTrackService.removeTrackFromPlaylist(playlistTrackId);
            return ResponseEntity.ok("Track removed from playlist successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove track from playlist");
        }
    }
}
