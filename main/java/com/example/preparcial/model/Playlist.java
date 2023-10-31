package com.example.preparcial.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Table(name = Playlist.TABLE_NAME)
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Playlist {

    public static final String TABLE_NAME = "playlists";

    @Id
    @Column(name = "playlistid")
    Integer playlistId;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "playlistid")
    Set<PlaylistTrack> playlistTracks;

    public Playlist() {
        super();
    }

    public Playlist(Integer aPlaylistId, final String aName) {
        this.playlistId = aPlaylistId;
        this.name = aName;
    }

    public void update(String aName) {
        this.name = aName;
    }

}
