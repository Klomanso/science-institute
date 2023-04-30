package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

        List<Customer> findAllByOrderByTitle();
}
