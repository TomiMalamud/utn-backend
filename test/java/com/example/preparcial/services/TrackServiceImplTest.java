package com.example.preparcial.services;
import com.example.preparcial.model.Album;
import com.example.preparcial.model.Genre;
import com.example.preparcial.model.MediaType;
import com.example.preparcial.model.Track;
import com.example.preparcial.repositories.IdentifierRepository;
import com.example.preparcial.repositories.InvoiceRepository;
import com.example.preparcial.repositories.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackServiceImplTest {

    @InjectMocks
    private TrackServiceImpl trackService;

    @Mock
    private TrackRepository trackRepository;

    @Mock
    private IdentifierRepository identifierRepository;

    @Mock
    private AlbumService albumService;

    @Mock
    private MediaTypeService mediaTypeService;

    @Mock
    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {

        when(identifierRepository.nextValue(anyString())).thenReturn(1);
        when(albumService.findById(anyInt())).thenReturn(Optional.of(new Album()));
        when(mediaTypeService.findById(anyInt())).thenReturn(Optional.of(new MediaType()));
        when(genreService.findById(anyInt())).thenReturn(Optional.of(new Genre()));
        when(trackRepository.save(any())).thenReturn(new Track());


        Track result = trackService.create("Song", 1, 1, 1, "Composer", 200, 1000, 1.99);


        assertNotNull(result);
        verify(trackRepository).save(any());
    }

    @Mock
    private CustomerService customerService;
    @Mock
    private InvoiceRepository invoiceRepository;



    @Test
    public void testFindAllByCustomerId_200() {
        when(customerService.existsById(4)).thenReturn(true);
        when(trackRepository.findAllByInvoiceIds(anyList())).thenReturn(Arrays.asList(new Track(), new Track()));
        when(trackRepository.findAllByInvoiceIds(anyList())).thenReturn(Arrays.asList(new Track(), new Track()));

        List<Track> result = trackService.findAllByCustomerId(4);

        assertEquals(2, result.size());
        verify(trackRepository).findAllByInvoiceIds(anyList());
    }

    @Test
    public void testFindAllByCustomerId_204() {
        when(customerService.existsById(1)).thenReturn(true);
        when(trackRepository.findAllByInvoiceIds(anyList())).thenReturn(Collections.emptyList());

        List<Track> result = trackService.findAllByCustomerId(1);

        assertTrue(result.isEmpty());
        verify(trackRepository).findAllByInvoiceIds(anyList());
    }


    @Test
    public void testFindAllByCustomerId_404() {
        when(customerService.existsById(1)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trackService.findAllByCustomerId(1);
        });

        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void testFindAll() {
        when(trackRepository.findAll()).thenReturn(Arrays.asList(new Track(), new Track()));


        var result = trackService.findAll();

        
        assertEquals(2, result.size());
        verify(trackRepository).findAll();
    }

    @Test
    public void testFindById() {
        
        when(trackRepository.findById(1)).thenReturn(Optional.of(new Track()));

        
        Optional<Track> result = trackService.findById(1);

        
        assertTrue(result.isPresent());
        verify(trackRepository).findById(1);
    }

    @Test
    public void testDelete() {
        
        doNothing().when(trackRepository).deleteById(1);

        
        trackService.delete(1);

        
        verify(trackRepository).deleteById(1);
    }

    @Test
    public void testUpdate() {
        
        when(albumService.findById(anyInt())).thenReturn(Optional.of(new Album()));
        when(mediaTypeService.findById(anyInt())).thenReturn(Optional.of(new MediaType()));
        when(genreService.findById(anyInt())).thenReturn(Optional.of(new Genre()));
        when(trackRepository.findById(anyInt())).thenReturn(Optional.of(new Track()));
        when(trackRepository.save(any())).thenReturn(new Track());

        
        trackService.update(1, "Song", 1, 1, 1, "Composer", 200, 1000, 1.99);

        
        verify(trackRepository).findById(1);
        verify(trackRepository).save(any());
    }
}
