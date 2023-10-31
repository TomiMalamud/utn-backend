package com.example.preparcial.application.response;

import com.example.preparcial.model.Playlist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaylistResponse {
    Integer id;
    String name;

    public static PlaylistResponse from(Playlist aPlaylist) {
        return PlaylistResponse.builder()
                .id(aPlaylist.getPlaylistId())
                .name(aPlaylist.getName())
                .build();
    }
}
