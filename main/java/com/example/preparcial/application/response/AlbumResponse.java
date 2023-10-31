package com.example.preparcial.application.response;

import com.example.preparcial.model.Album;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlbumResponse {
    Integer id;
    String title;
    ArtistResponse artist;

    public static AlbumResponse from(Album anAlbum) {
        return AlbumResponse.builder()
                .id(anAlbum.getAlbumId())
                .title(anAlbum.getTitle())
                .artist(ArtistResponse.from(anAlbum.getArtist()))
                .build();
    }
}
