package com.example.preparcial.application.response;

import com.example.preparcial.model.InvoiceItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceItemResponse {
    Integer invoiceLineId;
    Integer invoiceId;
    Integer trackId;
    BigDecimal unitPrice;
    Integer quantity;

    public static InvoiceItemResponse from(InvoiceItem invoiceItem) {
        return InvoiceItemResponse.builder()
                .invoiceLineId(invoiceItem.getInvoiceLineId())
                .invoiceId(invoiceItem.getInvoice().getInvoiceId())
                .trackId(invoiceItem.getTrack().getTrackId())
                .unitPrice(invoiceItem.getUnitPrice())
                .quantity(invoiceItem.getQuantity())
                .build();
    }
}
