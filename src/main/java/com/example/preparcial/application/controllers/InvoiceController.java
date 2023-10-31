package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateInvoiceRequest;
import com.example.preparcial.application.request.UpdateInvoiceRequest;
import com.example.preparcial.application.response.InvoiceResponse;
import com.example.preparcial.services.InvoiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InvoiceController {
    InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val invoices = invoiceService.findAll()
                    .stream()
                    .map(InvoiceResponse::from)
                    .toList();

            return ResponseHandler.success(invoices);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }


    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateInvoiceRequest aRequest) {
        try {
            val invoice = invoiceService.create(
                    aRequest.getCustomerId(),
                    aRequest.getInvoiceDate(),
                    aRequest.getBillingAddress(),
                    aRequest.getBillingCity(),
                    aRequest.getBillingState(),
                    aRequest.getBillingCountry(),
                    aRequest.getBillingPostalCode(),
                    aRequest.getTotal());

            return ResponseHandler.success(InvoiceResponse.from(invoice));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return invoiceService.findById(id)
                    .map(anInvoice -> ResponseHandler.success(InvoiceResponse.from(anInvoice)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            invoiceService.delete(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateInvoiceRequest aRequest) {
        try {
            invoiceService.update(
                    id,
                    aRequest.getCustomerId(),
                    aRequest.getInvoiceDate(),
                    aRequest.getBillingAddress(),
                    aRequest.getBillingCity(),
                    aRequest.getBillingState(),
                    aRequest.getBillingCountry(),
                    aRequest.getBillingPostalCode(),
                    aRequest.getTotal());
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
