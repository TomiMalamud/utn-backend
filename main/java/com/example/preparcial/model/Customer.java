package com.example.preparcial.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = Customer.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    public static final String TABLE_NAME = "customers";

    @Id
    @Column(name = "customerid")
    Integer customerId;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;

    @Column(name = "company")
    String company;

    @Column(name = "address")
    String address;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "country")
    String country;

    @Column(name = "postalcode")
    String postalCode;

    @Column(name = "phone")
    String phone;

    @Column(name = "fax")
    String fax;

    @Column(name = "email")
    String email;

    @ManyToOne
    @JoinColumn(name = "supportrepid", referencedColumnName = "employeeid")
    Employee supportRep;

    public Customer() {
        super();
    }

    public Customer(Integer customerId, String firstName, String lastName, String company, String address, String city, String state, String country, String postalCode, String phone, String fax, String email, Employee supportRep) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.supportRep = supportRep;
    }

    public void update(String firstName, String lastName, String company, String address, String city, String state, String country, String postalCode, String phone, String fax, String email, Employee supportRep) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.supportRep = supportRep;
    }

}
