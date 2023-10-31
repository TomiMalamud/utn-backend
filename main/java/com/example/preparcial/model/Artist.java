package com.example.preparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = Artist.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Artist {
    public static final String TABLE_NAME = "artists";

    @Id
    @Column(name = "artistid")
    Integer artistId;
    @Column(name = "name")
    String name;

    public Artist() {
        super();
    }

    public Artist(Integer artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    public void update(String name) {
    }
}
