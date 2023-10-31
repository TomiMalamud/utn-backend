package com.example.preparcial.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = Album.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Album {

    public static final String TABLE_NAME = "Albums";

    @Id
    @Column(name = "albumid")
    Integer albumId;

    @Column(name = "title")
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artistid", nullable = false)
    Artist artist;

    public Album() {
        super();
    }

    public Album(Integer albumId, String title, Artist artist) {
        this.albumId = albumId;
        this.title = title;
        this.artist = artist;
    }

    public void update(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }
}
