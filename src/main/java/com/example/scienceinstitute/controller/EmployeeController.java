package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Employee;
import com.example.scienceinstitute.service.EducationService;
import com.example.scienceinstitute.service.EmployeeService;
import com.example.scienceinstitute.service.TitleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

        private final EmployeeService employeeService;
        private final EducationService educationService;
        private final TitleService titleService;


        @GetMapping
        public ModelAndView getEmployees(ModelAndView modelAndView) {
                modelAndView.addObject("employees", employeeService.findAll());
                modelAndView.setViewName("employee/employees");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public ModelAndView getEmployee(@PathVariable("id") String id, ModelAndView modelAndView) {
                modelAndView.addObject("employee", employeeService.findById(id));
                modelAndView.setViewName("employee/employee");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newEmployee(@ModelAttribute("employee") Employee employee,
                                        ModelAndView modelAndView) {

                modelAndView.addObject("titles", titleService.findAllByOrderByName());
                modelAndView.addObject("education", educationService.findAllByOrderByType());
                modelAndView.setViewName("employee/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createEmployee(@ModelAttribute("employee") @Valid Employee employee,
                                           BindingResult bindingResult, ModelAndView modelAndView) {

                if (employeeService.existsById(employee.getContract())) {
                        bindingResult.addError(new FieldError("employee", "contract",
                                "There is employee with such number, enter another number"));
                }

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("titles", titleService.findAllByOrderByName());
                        modelAndView.addObject("education", educationService.findAllByOrderByType());
                        modelAndView.setViewName("employee/new");
                        return modelAndView;
                }

                employeeService.save(employee);
                modelAndView.setViewName("redirect:/employees");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") String id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("titles", titleService.findAllByOrderByName());
                modelAndView.addObject("education", educationService.findAllByOrderByType());
                modelAndView.addObject("employee", employeeService.findById(id));
                modelAndView.setViewName("employee/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editEmployee(@ModelAttribute("employee") @Valid Employee employee,
                                         BindingResult bindingResult, ModelAndView modelAndView,
                                         @PathVariable("id") String id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.addObject("titles", titleService.findAllByOrderByName());
                        modelAndView.addObject("education", educationService.findAllByOrderByType());
                        modelAndView.setViewName("employee/edit");
                        return modelAndView;
                }

                employeeService.update(id, employee);
                modelAndView.setViewName("redirect:/employees");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteEmployee(@PathVariable("id") String id, ModelAndView modelAndView) {
                employeeService.deleteById(id);
                modelAndView.setViewName("redirect:/employees");
                return modelAndView;
        }
}
