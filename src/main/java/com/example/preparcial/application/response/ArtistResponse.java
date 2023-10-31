package com.example.preparcial.application.response;

import com.example.preparcial.model.Artist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistResponse {
    Integer id;
    String name;

    public static ArtistResponse from(Artist anArtist) {
        return ArtistResponse.builder()
                .id(anArtist.getArtistId())
                .name(anArtist.getName())
                .build();
    }
}
