package com.example.preparcial.application.response;

import com.example.preparcial.model.Customer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    Integer id;
    String firstName;
    String lastName;
    String company;
    String address;
    String city;
    String state;
    String country;
    String postalCode;
    String phone;
    String fax;
    String email;
    EmployeeResponse supportRep;

    public static CustomerResponse from(Customer aCustomer) {
        return CustomerResponse.builder()
                .id(aCustomer.getCustomerId())
                .firstName(aCustomer.getFirstName())
                .lastName(aCustomer.getLastName())
                .company(aCustomer.getCompany())
                .address(aCustomer.getAddress())
                .city(aCustomer.getCity())
                .state(aCustomer.getState())
                .country(aCustomer.getCountry())
                .postalCode(aCustomer.getPostalCode())
                .phone(aCustomer.getPhone())
                .fax(aCustomer.getFax())
                .email(aCustomer.getEmail())
                .supportRep(EmployeeResponse.from(aCustomer.getSupportRep()))
                .build();
    }
}
