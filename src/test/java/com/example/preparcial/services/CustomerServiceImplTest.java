package com.example.preparcial.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.preparcial.model.Customer;
import com.example.preparcial.model.Employee;
import com.example.preparcial.repositories.CustomerRepository;
import com.example.preparcial.repositories.IdentifierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import java.util.Optional;

public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private IdentifierRepository identifierRepository;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        when(identifierRepository.nextValue(anyString())).thenReturn(1);
        when(employeeService.findById(anyInt())).thenReturn(Optional.of(new Employee()));
        when(customerRepository.save(any())).thenReturn(new Customer());

        Customer result = customerService.create("John", "Doe", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", 1);

        assertNotNull(result);
        verify(customerRepository).save(any());
    }

    @Test
    public void testFindAll() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));

        var result = customerService.findAll();

        assertEquals(2, result.size());
        verify(customerRepository).findAll();
    }

    @Test
    public void testFindById() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(new Customer()));

        Optional<Customer> result = customerService.findById(1);

        assertTrue(result.isPresent());
        verify(customerRepository).findById(1);
    }

    @Test
    public void testDelete() {
        doNothing().when(customerRepository).deleteById(1);

        customerService.delete(1);

        verify(customerRepository).deleteById(1);
    }

    @Test
    public void testUpdate() {
        when(employeeService.findById(anyInt())).thenReturn(Optional.of(new Employee()));
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(new Customer()));
        when(customerRepository.save(any())).thenReturn(new Customer());

        customerService.update(1, "John", "Doe", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", 1);

        verify(customerRepository).findById(1);
        verify(customerRepository).save(any());
    }
}

