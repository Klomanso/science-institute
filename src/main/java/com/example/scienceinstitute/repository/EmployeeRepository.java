package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
