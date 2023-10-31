package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateEmployeeRequest;
import com.example.preparcial.application.request.UpdateEmployeeRequest;
import com.example.preparcial.application.response.EmployeeResponse;
import com.example.preparcial.services.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val employees = employeeService.findAll()
                    .stream()
                    .map(EmployeeResponse::from)
                    .toList();

            return ResponseHandler.success(employees);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateEmployeeRequest aRequest) {
        try {
            val employee = employeeService.create(
                    aRequest.getLastName(),
                    aRequest.getFirstName(),
                    aRequest.getTitle(),
                    aRequest.getReportsTo(),
                    aRequest.getBirthDate(),
                    aRequest.getHireDate(),
                    aRequest.getAddress(),
                    aRequest.getCity(),
                    aRequest.getState(),
                    aRequest.getCountry(),
                    aRequest.getPostalCode(),
                    aRequest.getPhone(),
                    aRequest.getFax(),
                    aRequest.getEmail()
            );
            return ResponseHandler.success(EmployeeResponse.from(employee));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return employeeService.findById(id)
                    .map(aEmployee -> ResponseHandler.success(EmployeeResponse.from(aEmployee)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            employeeService.delete(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateEmployeeRequest aRequest) {
        try {
            employeeService.update(
                    id,
                    aRequest.getLastName(),
                    aRequest.getFirstName(),
                    aRequest.getTitle(),
                    aRequest.getReportsTo(),
                    aRequest.getBirthDate(),
                    aRequest.getHireDate(),
                    aRequest.getAddress(),
                    aRequest.getCity(),
                    aRequest.getState(),
                    aRequest.getCountry(),
                    aRequest.getPostalCode(),
                    aRequest.getPhone(),
                    aRequest.getFax(),
                    aRequest.getEmail()
            );
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
