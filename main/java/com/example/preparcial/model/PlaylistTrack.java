package com.example.preparcial.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = PlaylistTrack.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaylistTrack {

    public static final String TABLE_NAME = "playlist_track";

    @EmbeddedId
    PlaylistTrackId id;

    @ManyToOne
    @MapsId("playlistId")
    @JoinColumn(name = "playlistid")
    Playlist playlist;

    @ManyToOne
    @MapsId("trackId")
    @JoinColumn(name = "trackid")
    Track track;

    public PlaylistTrack() {
        super();
    }

    public PlaylistTrack(PlaylistTrackId id, Playlist playlist, Track track) {
        this.id = id;
        this.playlist = playlist;
        this.track = track;
    }

    public PlaylistTrack(PlaylistTrackId newId) {
    }
}
