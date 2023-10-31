package com.example.preparcial.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCustomerRequest {
    @NotBlank(message = "First name is mandatory")
    @Size(max = 40, message = "First name must not exceed 40 characters")
    String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 20, message = "Last name must not exceed 20 characters")
    String lastName;

    @Size(max = 80, message = "Company must not exceed 80 characters")
    String company;

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

    @NotBlank(message = "Email is mandatory")
    @Size(max = 60, message = "Email must not exceed 60 characters")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Email is invalid")
    String email;

    @NotNull(message = "Support Representative ID is mandatory")
    Integer supportRepId;
}
