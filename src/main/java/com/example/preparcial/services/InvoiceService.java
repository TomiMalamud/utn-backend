package com.example.preparcial.services;

import com.example.preparcial.model.Invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    Invoice create(
            Integer customerId,
            LocalDateTime invoiceDate,
            String billingAddress,
            String billingCity,
            String billingState,
            String billingCountry,
            String billingPostalCode,
            BigDecimal total
    );

    List<Invoice> findAll();

    Optional<Invoice> findById(Integer id);

    void delete(Integer id);

    void update(
            Integer id,
            Integer costumerId,
            LocalDateTime invoiceDate,
            String billingAddress,
            String billingCity,
            String billingState,
            String billingCountry,
            String billingPostalCode,
            BigDecimal total
    );
}