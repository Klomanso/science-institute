package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Customer;
import com.example.scienceinstitute.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

        private final CustomerService customerService;

        @GetMapping
        public ModelAndView getCustomers(ModelAndView modelAndView) {
                modelAndView.addObject("customers", customerService.findAll());
                modelAndView.setViewName("customer/customers");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public ModelAndView getCustomer(@PathVariable("id") String id, ModelAndView modelAndView) {
                modelAndView.addObject("customer", customerService.findById(id));
                modelAndView.setViewName("customer/customer");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newCustomer(@ModelAttribute("customer") Customer customer,
                                        ModelAndView modelAndView) {
                modelAndView.setViewName("customer/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createCustomer(@ModelAttribute("customer") @Valid Customer customer,
                                           BindingResult bindingResult, ModelAndView modelAndView) {

                if (bindingResult.hasErrors()) {
                        modelAndView.setViewName("customer/new");
                        return modelAndView;
                }

                customerService.save(customer);
                modelAndView.setViewName("redirect:/customers");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") String id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("customer", customerService.findById(id));
                modelAndView.setViewName("customer/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editCustomer(@ModelAttribute("customer") @Valid Customer customer,
                                         BindingResult bindingResult, ModelAndView modelAndView,
                                         @PathVariable("id") String id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.setViewName("customer/edit");
                        return modelAndView;
                }

                customerService.update(id, customer);
                modelAndView.setViewName("redirect:/customers");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteCustomer(@PathVariable("id") String id, ModelAndView modelAndView) {
                customerService.deleteById(id);
                modelAndView.setViewName("redirect:/customers");
                return modelAndView;
        }
}
