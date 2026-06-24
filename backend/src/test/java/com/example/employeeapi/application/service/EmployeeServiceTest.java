package com.example.employeeapi.application.service;

import com.example.employeeapi.domain.model.Employee;
import com.example.employeeapi.domain.repository.EmployeeRepositoryPort;
import com.example.employeeapi.infrastructure.adapter.in.dto.EmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryPort repository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeRequest employeeRequest;

    @BeforeEach
    void setUp() {
        employee = new Employee(
                1L,
                "John",
                "Doe",
                "Smith",
                "Johnson",
                30,
                "Male",
                "01-01-1994",
                "Software Engineer",
                LocalDateTime.now(),
                true
        );

        employeeRequest = new EmployeeRequest(
                "John",
                "Doe",
                "Smith",
                "Johnson",
                30,
                "Male",
                "01-01-1994",
                "Software Engineer",
                true
        );
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(employee);
        when(repository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeById(999L);

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(999L);
    }

    @Test
    void testSaveAll() {
        List<Employee> employees = Arrays.asList(employee);
        when(repository.save(any(Employee.class))).thenReturn(employee);

        List<Employee> result = employeeService.saveAll(employees);

        assertEquals(1, result.size());
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void testSearchByName() {
        List<Employee> employees = Arrays.asList(employee);
        when(repository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.searchByName("John");

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployee() {
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        when(repository.save(any(Employee.class))).thenReturn(employee);

        Optional<Employee> result = employeeService.update(1L, employeeRequest);

        assertTrue(result.isPresent());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(repository).deleteById(1L);

        employeeService.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}

