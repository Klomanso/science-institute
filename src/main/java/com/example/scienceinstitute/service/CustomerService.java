package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.BadActionException;
import com.example.scienceinstitute.model.Customer;
import com.example.scienceinstitute.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

        private final CustomerRepository customerRepository;

        public List<Customer> findAll() {

                List<Customer> customers = customerRepository.findAll();

                if (customers.isEmpty()) {
                        throw new BadActionException("There isn't any customer");
                } else {
                        return customers;
                }
        }

        public Customer findById(String id) {
                return customerRepository.findById(id)
                        .orElseThrow(() -> new BadActionException("There isn't customer with such number"));
        }

        @Modifying
        public Customer update(String id, Customer newCustomer) {
                Customer customerToBeUpdated = findById(id);
                customerToBeUpdated.setTitle(newCustomer.getTitle());
                customerToBeUpdated.setEmail(newCustomer.getEmail());
                customerToBeUpdated.setPhone(newCustomer.getPhone());
                return save(customerToBeUpdated);
        }

        public Customer save(Customer customer) {
                return customerRepository.save(customer);
        }

        public void deleteById(String id) {
                customerRepository.deleteById(id);
        }

        public List<Customer> findAllByOrderByTitle() {
                return customerRepository.findAllByOrderByTitle();
        }

        public boolean existsById(String id) {
                return customerRepository.existsById(id);
        }
}
