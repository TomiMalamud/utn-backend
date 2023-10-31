package com.example.preparcial.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Table(name = Track.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Track {

    public static final String TABLE_NAME = "tracks";

    @Id
    @Column(name = "trackid")
    Integer trackId;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "albumid")
    Album album;

    @ManyToOne
    @JoinColumn(name = "mediatypeid")
    MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "genreid")
    Genre genre;

    @Column(name = "composer")
    String composer;

    @Column(name = "milliseconds")
    Integer milliseconds;

    @Column(name = "bytes")
    Integer bytes;

    @Column(name = "unitprice")
    Double unitPrice;

    @OneToMany(mappedBy = "track")
    List<InvoiceItem> invoiceItems;

    @OneToMany(mappedBy = "track")
    List<PlaylistTrack> playlistTracks;

    public Track() {
        super();
    }

    public Track(Integer trackId, String name, Album album, MediaType mediaType, Genre genre, String composer, Integer milliseconds, Integer bytes, Double unitPrice) {
        this.trackId = trackId;
        this.name = name;
        this.album = album;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public void update(String name, Album album, MediaType mediaType, Genre genre, String composer, Integer milliseconds, Integer bytes, Double unitPrice) {
        this.name = name;
        this.album = album;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }
}
