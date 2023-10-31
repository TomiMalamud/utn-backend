package com.example.preparcial.repositories;

import com.example.preparcial.model.PlaylistTrack;
import com.example.preparcial.model.PlaylistTrackId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, PlaylistTrackId> {
    Optional<PlaylistTrack> findById(PlaylistTrackId playlistTrackId);

    void deleteById(PlaylistTrackId playlistTrackId);
}
