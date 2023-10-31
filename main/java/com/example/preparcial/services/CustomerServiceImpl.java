package com.example.preparcial.services;

import com.example.preparcial.model.Customer;
import com.example.preparcial.repositories.CustomerRepository;
import com.example.preparcial.repositories.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    IdentifierRepository identifierRepository;
    EmployeeService employeeService;

    @Override
    @Transactional
    public Customer create(final String firstName,
                           final String lastName,
                           final String company,
                           final String address,
                           final String city,
                           final String state,
                           final String country,
                           final String postalCode,
                           final String phone,
                           final String fax,
                           final String email,
                           final Integer supportRepId) {

        val supportRep = employeeService.findById(supportRepId)
                .orElseThrow(() -> new IllegalArgumentException("Support Representative not found"));
        val customerId = identifierRepository.nextValue(Customer.TABLE_NAME);
        val customer = new Customer(customerId, firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email, supportRep);

        return customerRepository.save(customer);
    }

    @Override
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(final Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(final Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(final Integer id,
                       final String firstName,
                       final String lastName,
                       final String company,
                       final String address,
                       final String city,
                       final String state,
                       final String country,
                       final String postalCode,
                       final String phone,
                       final String fax,
                       final String email,
                       final Integer supportRepId) {
        val supportRep = employeeService.findById(supportRepId)
                .orElseThrow(() -> new IllegalArgumentException("Support Representative not found"));
        val customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.update(firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email, supportRep);

        customerRepository.save(customer);
    }
}
