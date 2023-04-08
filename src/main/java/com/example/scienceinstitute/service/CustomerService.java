package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

        private final CustomerRepository customerRepository;
}
