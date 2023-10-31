package com.example.preparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = Genre.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre {

    public static final String TABLE_NAME = "Genres";

    @Id
    @Column(name = "genreid")
    Integer genreId;

    @Column(name = "name")
    String name;

    public Genre() {
        super();
    }

    public Genre(Integer genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public void update(String name) {
    }
}
