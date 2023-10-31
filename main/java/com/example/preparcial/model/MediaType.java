package com.example.preparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = MediaType.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaType {
    public static final String TABLE_NAME = "media_types";

    @Id
    @Column(name = "MediaTypeId")
    Integer mediaTypeId;

    @Column(name = "Name")
    String name;

    public MediaType() {
        super();
    }

    public MediaType(Integer mediaTypeId, String name) {
        this.mediaTypeId = mediaTypeId;
        this.name = name;
    }

    public void update(String name) {
    }
}
