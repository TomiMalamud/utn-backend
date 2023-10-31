package com.example.preparcial.services;
import com.example.preparcial.model.Playlist;
import com.example.preparcial.repositories.IdentifierRepository;
import com.example.preparcial.repositories.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlaylistServiceImplTest {

    @InjectMocks
    private PlaylistServiceImpl playlistService;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private IdentifierRepository identifierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        when(identifierRepository.nextValue(anyString())).thenReturn(1);
        when(playlistRepository.save(any())).thenReturn(new Playlist());

        Playlist result = playlistService.create("My Playlist");

        assertNotNull(result);
        verify(playlistRepository).save(any());
    }

    @Test
    public void testFindAll() {
        when(playlistRepository.findAll()).thenReturn(Arrays.asList(new Playlist(), new Playlist()));

        var result = playlistService.findAll();

        assertEquals(2, result.size());
        verify(playlistRepository).findAll();
    }

    @Test
    public void testFindById() {
        when(playlistRepository.findById(1)).thenReturn(Optional.of(new Playlist()));

        Optional<Playlist> result = playlistService.findById(1);

        assertTrue(result.isPresent());
        verify(playlistRepository).findById(1);
    }

    @Test
    public void testDelete() {
        doNothing().when(playlistRepository).deleteById(1);

        playlistService.delete(1);

        verify(playlistRepository).deleteById(1);
    }

    @Test
    public void testUpdate() {
        when(playlistRepository.findById(anyInt())).thenReturn(Optional.of(new Playlist()));
        when(playlistRepository.save(any())).thenReturn(new Playlist());

        playlistService.update(1, "Updated Playlist");

        verify(playlistRepository).findById(1);
        verify(playlistRepository).save(any());
    }
}
