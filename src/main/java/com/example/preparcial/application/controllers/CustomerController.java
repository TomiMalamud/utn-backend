package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateCustomerRequest;
import com.example.preparcial.application.request.UpdateCustomerRequest;
import com.example.preparcial.application.response.CustomerResponse;
import com.example.preparcial.services.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerController {
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val customers = customerService.findAll()
                    .stream()
                    .map(CustomerResponse::from)
                    .toList();

            return ResponseHandler.success(customers);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateCustomerRequest aRequest) {
        try {
            val customer = customerService.create(
                    aRequest.getFirstName(),
                    aRequest.getLastName(),
                    aRequest.getCompany(),
                    aRequest.getAddress(),
                    aRequest.getCity(),
                    aRequest.getState(),
                    aRequest.getCountry(),
                    aRequest.getPostalCode(),
                    aRequest.getPhone(),
                    aRequest.getFax(),
                    aRequest.getEmail(),
                    aRequest.getSupportRepId());

            return ResponseHandler.success(CustomerResponse.from(customer));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return customerService.findById(id)
                    .map(aCustomer -> ResponseHandler.success(CustomerResponse.from(aCustomer)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            customerService.delete(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateCustomerRequest aRequest) {
        try {
            customerService.update(
                    id,
                    aRequest.getFirstName(),
                    aRequest.getLastName(),
                    aRequest.getCompany(),
                    aRequest.getAddress(),
                    aRequest.getCity(),
                    aRequest.getState(),
                    aRequest.getCountry(),
                    aRequest.getPostalCode(),
                    aRequest.getPhone(),
                    aRequest.getFax(),
                    aRequest.getEmail(),
                    aRequest.getSupportRepId());
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}