package com.example.scienceinstitute.controller;


import com.example.scienceinstitute.exception.BadActionException;
import com.example.scienceinstitute.model.Customer;
import com.example.scienceinstitute.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerResourceController {

        private final CustomerService customerService;


        @GetMapping("/{id}")
        public Customer getCustomer(@PathVariable("id") String id) {
                return customerService.findById(id);
        }

        @PostMapping
        public Customer create(@RequestBody @Valid Customer customer, BindingResult bindingResult) {

                if (customerService.existsById(customer.getOgrn())) {
                        throw new BadActionException("There is customer with such ogrn");
                }

                return customerService.save(customer);
        }
}
