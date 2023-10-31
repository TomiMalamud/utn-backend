package com.example.preparcial.application.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateInvoiceRequest {
    @NotNull(message = "Customer ID is mandatory")
    Integer customerId;

    @NotNull(message = "Invoice date is mandatory")
    LocalDateTime invoiceDate;

    @Size(max = 70, message = "Billing address must not exceed 70 characters")
    String billingAddress;

    @Size(max = 40, message = "Billing city must not exceed 40 characters")
    String billingCity;

    @Size(max = 40, message = "Billing state must not exceed 40 characters")
    String billingState;

    @Size(max = 40, message = "Billing country must not exceed 40 characters")
    String billingCountry;

    @Size(max = 10, message = "Billing postal code must not exceed 10 characters")
    String billingPostalCode;

    @NotNull(message = "Total is mandatory")
    BigDecimal total;
}
