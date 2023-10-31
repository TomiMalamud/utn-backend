package com.example.preparcial.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Table(name = InvoiceItem.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceItem {

    public static final String TABLE_NAME = "invoice_items";

    @Id
    @Column(name = "invoicelineid")
    Integer invoiceLineId;

    @ManyToOne
    @JoinColumn(name = "invoiceid")
    Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "trackid")
    Track track;

    @Column(name = "unitprice", precision = 10, scale = 2)
    BigDecimal unitPrice;

    @Column(name = "quantity")
    Integer quantity;

    public InvoiceItem() {
        super();
    }

    public InvoiceItem(Integer invoiceLineId, Invoice invoice, Track track, BigDecimal unitPrice, Integer quantity) {
        this.invoiceLineId = invoiceLineId;
        this.invoice = invoice;
        this.track = track;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public void update(Invoice invoice, Track track, BigDecimal unitPrice, Integer quantity) {
        this.invoice = invoice;
        this.track = track;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
