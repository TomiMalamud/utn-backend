package com.example.preparcial.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomPlaylistRequest {

    @Size(max = 120, message = "Playlist name must not exceed 120 characters")
    @NotBlank(message = "Playlist name is required")
    String name;

    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID must be positive")
    Integer customerId;

    @Size(max = 70, message = "Composer filter must not exceed 70 characters")
    String composerFilter;

    @NotNull(message = "Maximum duration is required")
    @Positive(message = "Maximum duration must be positive")
    Long maxDuration;
}
