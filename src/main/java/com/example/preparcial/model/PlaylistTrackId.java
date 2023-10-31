package com.example.preparcial.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlaylistTrackId implements Serializable {
    Integer playlistId;
    Integer trackId;

    public PlaylistTrackId() {
        super();
    }

    public PlaylistTrackId(Integer playlistId, Integer trackId) {
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistTrackId that = (PlaylistTrackId) o;
        return Objects.equals(playlistId, that.playlistId) && Objects.equals(trackId, that.trackId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, trackId);
    }
}
