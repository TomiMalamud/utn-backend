package com.example.preparcial.application.response;

import com.example.preparcial.model.Genre;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreResponse {
    Integer id;
    String name;

    public static GenreResponse from(Genre aGenre) {
        return GenreResponse.builder()
                .id(aGenre.getGenreId())
                .name(aGenre.getName())
                .build();
    }
}
