package com.example.preparcial.application.response;

import com.example.preparcial.model.Employee;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    Integer id;
    String lastName;
    String firstName;
    String title;
    Integer reportsTo;
    LocalDateTime birthDate;
    LocalDateTime hireDate;
    String address;
    String city;
    String state;
    String country;
    String postalCode;
    String phone;
    String fax;
    String email;

    public static EmployeeResponse from(Employee anEmployee) {
        return EmployeeResponse.builder()
                .id(anEmployee.getEmployeeId())
                .lastName(anEmployee.getLastName())
                .firstName(anEmployee.getFirstName())
                .title(anEmployee.getTitle())
                .reportsTo(anEmployee.getReportsTo())
                .birthDate(anEmployee.getBirthDate())
                .hireDate(anEmployee.getHireDate())
                .address(anEmployee.getAddress())
                .city(anEmployee.getCity())
                .state(anEmployee.getState())
                .country(anEmployee.getCountry())
                .postalCode(anEmployee.getPostalCode())
                .phone(anEmployee.getPhone())
                .fax(anEmployee.getFax())
                .email(anEmployee.getEmail())
                .build();
    }
}
