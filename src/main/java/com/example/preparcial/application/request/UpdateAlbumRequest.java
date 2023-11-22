package com.example.preparcial.application.request;

import com.example.preparcial.model.Artist;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAlbumRequest {


    String title;

    Artist artist;


    public Integer getArtistId() {
        return artist.getArtistId();
    }

}
