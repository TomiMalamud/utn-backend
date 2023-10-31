package com.example.preparcial.application.response;

import com.example.preparcial.model.Track;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackResponse {
    Integer id;
    String name;
    AlbumResponse album;
    MediaTypeResponse mediaType;
    GenreResponse genre;
    String composer;
    Integer milliseconds;
    Integer bytes;
    Double unitPrice;

    public static TrackResponse from(Track aTrack) {
        return TrackResponse.builder()
                .id(aTrack.getTrackId())
                .name(aTrack.getName())
                .album(AlbumResponse.from(aTrack.getAlbum()))
                .mediaType(MediaTypeResponse.from(aTrack.getMediaType()))
                .genre(GenreResponse.from(aTrack.getGenre()))
                .composer(aTrack.getComposer())
                .milliseconds(aTrack.getMilliseconds())
                .bytes(aTrack.getBytes())
                .unitPrice(aTrack.getUnitPrice())
                .build();
    }
}
