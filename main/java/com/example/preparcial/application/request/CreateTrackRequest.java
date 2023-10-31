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
public class CreateTrackRequest {

    @NotBlank(message = "Track name is mandatory")
    @Size(max = 200, message = "Track name must not exceed 120 characters")
    String name;

    @NotNull(message = "Album ID is mandatory")
    Integer albumId;

    @NotNull(message = "Media type ID is mandatory")
    Integer mediaTypeId;

    @NotNull(message = "Genre ID is mandatory")
    Integer genreId;

    @Size(max = 220, message = "Composer must not exceed 220 characters")
    String composer;

    @NotNull(message = "Milliseconds are mandatory")
    @Positive(message = "Milliseconds must be positive")
    Integer milliseconds;

    @NotNull(message = "Bytes are mandatory")
    @Positive(message = "Bytes must be positive")
    Integer bytes;

    @NotNull(message = "Unit price is mandatory")
    @Positive(message = "Unit price must be positive")
    Double unitPrice;
}
