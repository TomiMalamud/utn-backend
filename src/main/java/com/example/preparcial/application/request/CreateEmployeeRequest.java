package com.example.preparcial.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEmployeeRequest {

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 20, message = "Last name must not exceed 20 characters")
    String lastName;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 20, message = "First name must not exceed 20 characters")
    String firstName;

    @Size(max = 30, message = "Title must not exceed 30 characters")
    String title;

    @NotNull(message = "Reports To ID is mandatory")
    Integer reportsTo;

    @NotNull(message = "Birth Date is mandatory")
    LocalDateTime birthDate;

    @NotNull(message = "Hire Date is mandatory")
    LocalDateTime hireDate;

    @Size(max = 70, message = "Address must not exceed 70 characters")
    String address;

    @Size(max = 40, message = "City must not exceed 40 characters")
    String city;

    @Size(max = 40, message = "State must not exceed 40 characters")
    String state;

    @Size(max = 40, message = "Country must not exceed 40 characters")
    String country;

    @Size(max = 10, message = "Postal code must not exceed 10 characters")
    String postalCode;

    @Size(max = 24, message = "Phone must not exceed 24 characters")
    String phone;

    @Size(max = 24, message = "Fax must not exceed 24 characters")
    String fax;

    @Size(max = 60, message = "Email must not exceed 60 characters")
    String email;
}
