package com.example.preparcial.application.response;

import com.example.preparcial.model.PlaylistTrack;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaylistTrackResponse {
    Integer playlistId;
    Integer trackId;

    public static PlaylistTrackResponse from(PlaylistTrack aPlaylistTrack) {
        return PlaylistTrackResponse.builder()
                .playlistId(aPlaylistTrack.getPlaylist().getPlaylistId())
                .trackId(aPlaylistTrack.getTrack().getTrackId())
                .build();
    }
}
