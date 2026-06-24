package com.example.employeeapi.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
