package com.example.preparcial.services;

import com.example.preparcial.model.InvoiceItem;
import com.example.preparcial.repositories.IdentifierRepository;
import com.example.preparcial.repositories.InvoiceItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InvoiceItemServiceImpl implements InvoiceItemService {

    InvoiceItemRepository invoiceItemRepository;
    IdentifierRepository identifierRepository;
    InvoiceService invoiceService;
    TrackService trackService;

    @Override
    @Transactional
    public InvoiceItem create(final Integer invoiceId,
                              final Integer trackId,
                              final BigDecimal unitPrice,
                              final Integer quantity) {

        val invoice = invoiceService.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        val track = trackService.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));

        val invoiceLineId = identifierRepository.nextValue(InvoiceItem.TABLE_NAME);
        val invoiceItem = new InvoiceItem(invoiceLineId, invoice, track, unitPrice, quantity);

        return invoiceItemRepository.save(invoiceItem);
    }

    @Override
    public List<InvoiceItem> findAll() {
        return invoiceItemRepository.findAll();
    }

    @Override
    public Optional<InvoiceItem> findById(final Integer id) {
        return invoiceItemRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(final Integer id) {
        invoiceItemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public InvoiceItem update(final Integer invoiceLineId,
                              final Integer invoiceId,
                              final Integer trackId,
                              final BigDecimal unitPrice,
                              final Integer quantity) {

        val invoice = invoiceService.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        val track = trackService.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));

        val existingInvoiceItem = invoiceItemRepository.findById(invoiceLineId)
                .orElseThrow(() -> new IllegalArgumentException("InvoiceItem not found"));

        existingInvoiceItem.update(invoice, track, unitPrice, quantity);

        return invoiceItemRepository.save(existingInvoiceItem);
    }
}
