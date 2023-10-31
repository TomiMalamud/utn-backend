package com.example.preparcial.services;

import com.example.preparcial.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer create(
            String firstName,
            String lastName,
            String company,
            String address,
            String city,
            String state,
            String country,
            String postalCode,
            String phone,
            String fax,
            String email,
            Integer supportRepId
    );

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    boolean existsById(Integer customerId);

    void delete(Integer id);

    void update(
            Integer id,
            String firstName,
            String lastName,
            String company,
            String address,
            String city,
            String state,
            String country,
            String postalCode,
            String phone,
            String fax,
            String email,
            Integer supportRepId
    );
}
