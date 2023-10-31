package com.example.preparcial.application.response;

import com.example.preparcial.model.MediaType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaTypeResponse {
    Integer id;
    String name;

    public static MediaTypeResponse from(MediaType aMediaType) {
        return MediaTypeResponse.builder()
                .id(aMediaType.getMediaTypeId())
                .name(aMediaType.getName())
                .build();
    }
}
