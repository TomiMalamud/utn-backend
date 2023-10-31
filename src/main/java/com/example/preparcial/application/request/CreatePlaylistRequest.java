package com.example.preparcial.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePlaylistRequest {

    @Size(max = 120, message = "Playlist name must not exceed 70 characters")
    @NotBlank(message = "Playlist name is required")
    String name;
}
