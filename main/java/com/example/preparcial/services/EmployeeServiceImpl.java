package com.example.preparcial.services;

import com.example.preparcial.model.Employee;
import com.example.preparcial.repositories.EmployeeRepository;
import com.example.preparcial.repositories.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    IdentifierRepository identifierRepository;

    @Override
    @Transactional
    public Employee create(
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
            String email) {

        val employeeId = identifierRepository.nextValue(Employee.TABLE_NAME);
        val employee = new Employee(employeeId, lastName, firstName, title, reportsTo, birthDate, hireDate, address, city, state, country, postalCode, phone, fax, email);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(
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
            String email) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        employee.update(lastName, firstName, title, reportsTo, birthDate, hireDate, address, city, state, country, postalCode, phone, fax, email);

        employeeRepository.save(employee);
    }

}
