package com.example.preparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Table(name = Employee.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    public static final String TABLE_NAME = "employees";

    @Id
    @Column(name = "employeeid")
    Integer employeeId;

    @Column(name = "lastname")
    String lastName;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "title")
    String title;

    @Column(name = "reportsto")
    Integer reportsTo;

    @Column(name = "birthdate")
    LocalDateTime birthDate;

    @Column(name = "hiredate")
    LocalDateTime hireDate;

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

    public Employee() {
        super();
    }

    public Employee(Integer employeeId, String lastName, String firstName, String title, Integer reportsTo, LocalDateTime birthDate, LocalDateTime hireDate, String address, String city, String state, String country, String postalCode, String phone, String fax, String email) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.reportsTo = reportsTo;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }

    public void update(String lastName, String firstName, String title, Integer reportsTo, LocalDateTime birthDate, LocalDateTime hireDate, String address, String city, String state, String country, String postalCode, String phone, String fax, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.reportsTo = reportsTo;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }
}