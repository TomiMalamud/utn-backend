package com.example.preparcial.application.request;


import com.example.preparcial.model.Artist;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAlbumRequest {

    String title;

    Artist artist;

    public Integer getArtistId() {
        return artist.getArtistId();
    }
}
