package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateInvoiceItemRequest;
import com.example.preparcial.application.request.UpdateInvoiceItemRequest;
import com.example.preparcial.application.response.InvoiceItemResponse;
import com.example.preparcial.services.InvoiceItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoiceitems")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InvoiceItemController {
    InvoiceItemService invoiceItemService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val invoiceItems = invoiceItemService.findAll()
                    .stream()
                    .map(InvoiceItemResponse::from)
                    .toList();
            return ResponseHandler.success(invoiceItems);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateInvoiceItemRequest request) {
        try {
            val invoiceItem = invoiceItemService.create(
                    request.getInvoiceId(),
                    request.getTrackId(),
                    request.getUnitPrice(),
                    request.getQuantity());
            return ResponseHandler.success(InvoiceItemResponse.from(invoiceItem));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return invoiceItemService.findById(id)
                    .map(invoiceItem -> ResponseHandler.success(InvoiceItemResponse.from(invoiceItem)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            invoiceItemService.deleteById(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateInvoiceItemRequest request) {
        try {
            val updatedInvoiceItem = invoiceItemService.update(
                    id,
                    request.getInvoiceId(),
                    request.getTrackId(),
                    request.getUnitPrice(),
                    request.getQuantity());
            return ResponseHandler.success(InvoiceItemResponse.from(updatedInvoiceItem));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
