package com.example.preparcial.services;

import com.example.preparcial.model.InvoiceItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InvoiceItemService {
    List<InvoiceItem> findAll();

    Optional<InvoiceItem> findById(Integer invoiceLineId);

    InvoiceItem create(Integer invoiceId, Integer trackId, BigDecimal unitPrice, Integer quantity);

    void deleteById(Integer invoiceLineId);

    InvoiceItem update(Integer invoiceLineId, Integer invoiceId, Integer trackId, BigDecimal unitPrice, Integer quantity);
}
