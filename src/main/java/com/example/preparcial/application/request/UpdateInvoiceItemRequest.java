package com.example.preparcial.application.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateInvoiceItemRequest {
    @NotNull(message = "Invoice ID is mandatory")
    Integer invoiceId;

    @NotNull(message = "Track ID is mandatory")
    Integer trackId;

    @NotNull(message = "Unit Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit Price must be greater than 0")
    BigDecimal unitPrice;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity must be at least 1")
    Integer quantity;
}
