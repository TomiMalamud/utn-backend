package com.example.preparcial.application.response;

import com.example.preparcial.model.Invoice;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceResponse {
    Integer id;
    CustomerResponse customer;
    LocalDateTime invoiceDate;
    String billingAddress;
    String billingCity;
    String billingState;
    String billingCountry;
    String billingPostalCode;
    BigDecimal total;

    public static InvoiceResponse from(Invoice anInvoice) {
        return InvoiceResponse.builder()
                .id(anInvoice.getInvoiceId())
                .customer(anInvoice.getCustomer() != null ? CustomerResponse.from(anInvoice.getCustomer()) : null)
                .invoiceDate(anInvoice.getInvoiceDate())
                .billingAddress(anInvoice.getBillingAddress())
                .billingCity(anInvoice.getBillingCity())
                .billingState(anInvoice.getBillingState())
                .billingCountry(anInvoice.getBillingCountry())
                .billingPostalCode(anInvoice.getBillingPostalCode())
                .total(anInvoice.getTotal())
                .build();
    }
}
