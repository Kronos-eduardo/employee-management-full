package com.example.employeeapi.infrastructure.adapter.out.persistence;

import com.example.employeeapi.domain.model.Employee;
import com.example.employeeapi.domain.repository.EmployeeRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeePersistenceAdapter implements EmployeeRepositoryPort {

    private final SpringDataEmployeeRepository repository;

    public EmployeePersistenceAdapter(SpringDataEmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(employee.getId());
        entity.setFirstName(employee.getFirstName());
        entity.setMiddleName(employee.getMiddleName());
        entity.setPaternalLastName(employee.getPaternalLastName());
        entity.setMaternalLastName(employee.getMaternalLastName());
        entity.setAge(employee.getAge());
        entity.setGender(employee.getGender());
        entity.setBirthDate(employee.getBirthDate());
        entity.setJobTitle(employee.getJobTitle());
        entity.setActive(employee.getActive());

        EmployeeEntity savedEntity = repository.save(entity);

        return new Employee(savedEntity.getId(), savedEntity.getFirstName(), savedEntity.getMiddleName(),
                savedEntity.getPaternalLastName(), savedEntity.getMaternalLastName(),
                savedEntity.getAge(), savedEntity.getGender(), savedEntity.getBirthDate(),
                savedEntity.getJobTitle(), savedEntity.getHireDate(), savedEntity.getActive());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return repository.findById(id).map(e -> new Employee(
                e.getId(), e.getFirstName(), e.getMiddleName(), e.getPaternalLastName(),
                e.getMaternalLastName(), e.getAge(), e.getGender(), e.getBirthDate(),
                e.getJobTitle(), e.getHireDate(), e.getActive()));
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll().stream().map(e -> new Employee(
                        e.getId(), e.getFirstName(), e.getMiddleName(), e.getPaternalLastName(),
                        e.getMaternalLastName(), e.getAge(), e.getGender(), e.getBirthDate(),
                        e.getJobTitle(), e.getHireDate(), e.getActive()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}