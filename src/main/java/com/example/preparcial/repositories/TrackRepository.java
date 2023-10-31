package com.example.preparcial.repositories;

import com.example.preparcial.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    @Query("SELECT t FROM Track t JOIN t.invoiceItems ii JOIN ii.invoice i WHERE i.invoiceId IN :invoiceIds")
    List<Track> findAllByInvoiceIds(List<Integer> invoiceIds);

    @Query("SELECT t FROM Track t JOIN t.invoiceItems ii JOIN ii.invoice i WHERE i.invoiceId IN :invoiceIds AND t.composer LIKE %:composerFilter%")
    List<Track> findAllByInvoiceIdsAndComposerFilter(List<Integer> invoiceIds, String composerFilter);
}
