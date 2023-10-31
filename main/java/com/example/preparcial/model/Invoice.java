package com.example.preparcial.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = Invoice.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice {

    public static final String TABLE_NAME = "invoices";

    @Id
    @Column(name = "invoiceid")
    Integer invoiceId;

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "customerid")
    Customer customer;

    @Column(name = "invoicedate")
    LocalDateTime invoiceDate;

    @Column(name = "billingaddress")
    String billingAddress;

    @Column(name = "billingcity")
    String billingCity;

    @Column(name = "billingstate")
    String billingState;

    @Column(name = "billingcountry")
    String billingCountry;

    @Column(name = "billingpostalcode")
    String billingPostalCode;

    @Column(name = "total")
    BigDecimal total;

    public Invoice() {
        super();
    }

    public Invoice(Integer invoiceId, Customer customer, LocalDateTime invoiceDate, String billingAddress, String billingCity, String billingState, String billingCountry, String billingPostalCode, BigDecimal total) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingPostalCode = billingPostalCode;
        this.total = total;
    }

    public void update(Customer customer, LocalDateTime invoiceDate, String billingAddress, String billingCity, String billingState, String billingCountry, String billingPostalCode, BigDecimal total) {
        this.customer = customer;
        this.invoiceDate = invoiceDate;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingPostalCode = billingPostalCode;
        this.total = total;
    }

}
