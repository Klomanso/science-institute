package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class EmployeeController {

        private final EmployeeService employeeService;
}
