package com.example.employeeapi.application.service;

import com.example.employeeapi.domain.model.Employee;
import com.example.employeeapi.domain.repository.EmployeeRepositoryPort;
import com.example.employeeapi.infrastructure.adapter.in.dto.EmployeeRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepositoryPort repository;

    public EmployeeService(EmployeeRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Employee> saveAll(List<Employee> employees) {
        return employees.stream().map(repository::save).collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    public List<Employee> searchByName(String name) {
        String filter = name.toLowerCase();
        return repository.findAll().stream()
                .filter(e -> (e.getFirstName() != null && e.getFirstName().toLowerCase().contains(filter)) ||
                        (e.getPaternalLastName() != null && e.getPaternalLastName().toLowerCase().contains(filter)) ||
                        (e.getMaternalLastName() != null && e.getMaternalLastName().toLowerCase().contains(filter)))
                .collect(Collectors.toList());
    }

    public Optional<Employee> update(Long id, EmployeeRequest req) {
        return repository.findById(id).map(existingEmployee -> {
            existingEmployee.setFirstName(req.firstName());
            existingEmployee.setMiddleName(req.middleName());
            existingEmployee.setPaternalLastName(req.paternalLastName());
            existingEmployee.setMaternalLastName(req.maternalLastName());
            existingEmployee.setAge(req.age());
            existingEmployee.setGender(req.gender());
            existingEmployee.setBirthDate(req.birthDate());
            existingEmployee.setJobTitle(req.jobTitle());
            existingEmployee.setActive(req.active());
            return repository.save(existingEmployee);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}