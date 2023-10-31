package com.example.preparcial.services;

import com.example.preparcial.model.Employee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(
            String lastName,
            String firstName,
            String title,
            Integer reportsTo,
            LocalDateTime birthDate,
            LocalDateTime hireDate,
            String address,
            String city,
            String state,
            String country,
            String postalCode,
            String phone,
            String fax,
            String email
    );

    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    void delete(Integer id);

    void update(
            Integer id,
            String lastName,
            String firstName,
            String title,
            Integer reportsTo,
            LocalDateTime birthDate,
            LocalDateTime hireDate,
            String address,
            String city,
            String state,
            String country,
            String postalCode,
            String phone,
            String fax,
            String email
    );
}
