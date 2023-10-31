package com.example.preparcial.services;

import com.example.preparcial.model.Invoice;
import com.example.preparcial.repositories.IdentifierRepository;
import com.example.preparcial.repositories.InvoiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    InvoiceRepository invoiceRepository;
    IdentifierRepository identifierRepository;
    CustomerService customerService;

    @Override
    @Transactional
    public Invoice create(final Integer customerId,
                          final LocalDateTime invoiceDate,
                          final String billingAddress,
                          final String billingCity,
                          final String billingState,
                          final String billingCountry,
                          final String billingPostalCode,
                          final BigDecimal total) {

        val customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        val invoiceId = identifierRepository.nextValue(Invoice.TABLE_NAME);
        val invoice = new Invoice(invoiceId, customer, invoiceDate, billingAddress, billingCity, billingState, billingCountry, billingPostalCode, total);

        return invoiceRepository.save(invoice);
    }


    @Override
    public List<Invoice> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        invoices.forEach(invoice -> {
            invoice.getCustomer();
        });
        return invoices;
    }

    @Override
    public Optional<Invoice> findById(final Integer id) {
        return invoiceRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(final Integer id) {
        invoiceRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void update(final Integer id,
                       final Integer customerId,
                       final LocalDateTime invoiceDate,
                       final String billingAddress,
                       final String billingCity,
                       final String billingState,
                       final String billingCountry,
                       final String billingPostalCode,
                       final BigDecimal total) {
        val customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        val invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        invoice.update(customer, invoiceDate, billingAddress, billingCity, billingState, billingCountry, billingPostalCode, total);

        invoiceRepository.save(invoice);
    }
}
