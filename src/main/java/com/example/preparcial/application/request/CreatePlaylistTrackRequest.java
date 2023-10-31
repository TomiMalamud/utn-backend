package com.example.preparcial.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePlaylistTrackRequest {
    @NotNull(message = "Playlist ID is required")
    Integer playlistId;

    @NotNull(message = "Track ID is required")
    Integer trackId;
}
