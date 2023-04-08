package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

        private final EmployeeRepository employeeRepository;
}
