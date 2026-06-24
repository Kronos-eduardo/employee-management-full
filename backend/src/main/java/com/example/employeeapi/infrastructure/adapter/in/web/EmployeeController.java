package com.example.employeeapi.infrastructure.adapter.in.web;

import com.example.employeeapi.application.service.EmployeeService;
import com.example.employeeapi.domain.model.Employee;
import com.example.employeeapi.infrastructure.adapter.in.dto.EmployeeRequest;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<List<Employee>> createEmployees(@Valid @RequestBody List<EmployeeRequest> requests) {
        List<Employee> employees = requests.stream().map(req ->
                new Employee(
                        null,
                        req.firstName(),
                        req.middleName(),
                        req.paternalLastName(),
                        req.maternalLastName(),
                        req.age(),
                        req.gender(),
                        req.birthDate(),
                        req.jobTitle(),
                        LocalDateTime.now(),
                        req.active()
                )
        ).collect(Collectors.toList());

        return ResponseEntity.status(201).body(employeeService.saveAll(employees));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest req) {
        return employeeService.update(id, req)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}