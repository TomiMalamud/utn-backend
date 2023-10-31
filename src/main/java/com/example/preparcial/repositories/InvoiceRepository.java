package com.example.preparcial.repositories;

import com.example.preparcial.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT i.invoiceId FROM Invoice i WHERE i.customer.customerId = ?1")
    List<Integer> findInvoiceIdsByCustomerId(Integer customerId);

}
