package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CustomerController {

        private final CustomerService customerService;
}
