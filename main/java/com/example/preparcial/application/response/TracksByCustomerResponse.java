package com.example.preparcial.application.response;

import com.example.preparcial.model.Track;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TracksByCustomerResponse {
    Integer trackId;
    String name;
    String composer;
    Integer milliseconds;

    public static TracksByCustomerResponse from(Track aTrack) {
        return TracksByCustomerResponse.builder()
                .trackId(aTrack.getTrackId())
                .name(aTrack.getName())
                .composer(aTrack.getComposer())
                .milliseconds(aTrack.getMilliseconds())
                .build();
    }
}
