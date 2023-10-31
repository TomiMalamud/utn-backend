package com.example.preparcial.services;
import com.example.preparcial.model.Employee;
import com.example.preparcial.repositories.EmployeeRepository;
import com.example.preparcial.repositories.IdentifierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private IdentifierRepository identifierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        when(identifierRepository.nextValue(anyString())).thenReturn(1);
        when(employeeRepository.save(any())).thenReturn(new Employee());

        Employee result = employeeService.create("Doe", "John", "Engineer", 1, LocalDateTime.now(), LocalDateTime.now(), "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email");

        assertNotNull(result);
        verify(employeeRepository).save(any());
    }

    @Test
    public void testFindAll() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee(), new Employee()));

        var result = employeeService.findAll();

        assertEquals(2, result.size());
        verify(employeeRepository).findAll();
    }

    @Test
    public void testFindById() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(new Employee()));

        Optional<Employee> result = employeeService.findById(1);

        assertTrue(result.isPresent());
        verify(employeeRepository).findById(1);
    }

    @Test
    public void testDelete() {
        doNothing().when(employeeRepository).deleteById(1);

        employeeService.delete(1);

        verify(employeeRepository).deleteById(1);
    }

    @Test
    public void testUpdate() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(new Employee()));
        when(employeeRepository.save(any())).thenReturn(new Employee());

        employeeService.update(1, "Doe", "John", "Engineer", 1, LocalDateTime.now(), LocalDateTime.now(), "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email");

        verify(employeeRepository).findById(1);
        verify(employeeRepository).save(any());
    }
}
