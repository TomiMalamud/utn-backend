package com.example.preparcial.services;

import com.example.preparcial.model.PlaylistTrack;
import com.example.preparcial.model.PlaylistTrackId;

import java.util.List;

public interface PlaylistTrackService {
    PlaylistTrack addTrackToPlaylist(Integer playlistId, Integer trackId);

    List<PlaylistTrack> findAll();

    void removeTrackFromPlaylist(PlaylistTrackId playlistTrackId);
}
