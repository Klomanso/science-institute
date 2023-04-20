package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.ResourceNotFoundException;
import com.example.scienceinstitute.model.Employee;
import com.example.scienceinstitute.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

        private final EmployeeRepository employeeRepository;

        public List<Employee> findAll() {

                List<Employee> employees = employeeRepository.findAll();

                if (employees.isEmpty()) {
                        throw new ResourceNotFoundException("There isn't any employee");
                } else {
                        return employees;
                }
        }

        public Employee findById(String id) {
                return employeeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("There isn't employee with such number"));
        }

        @Modifying
        public Employee update(String id, Employee newEmployee) {
                Employee employeeToBeUpdated = findById(id);
                employeeToBeUpdated.setFirstName(newEmployee.getFirstName());
                employeeToBeUpdated.setLastName(newEmployee.getLastName());
                employeeToBeUpdated.setBirthDate(newEmployee.getBirthDate());
                employeeToBeUpdated.setHireDate(newEmployee.getHireDate());
                employeeToBeUpdated.setTitle(newEmployee.getTitle());
                employeeToBeUpdated.setEducation(newEmployee.getEducation());
                return save(employeeToBeUpdated);
        }

        public Employee save(Employee employee) {
                return employeeRepository.save(employee);
        }

        public void deleteById(String id) {
                employeeRepository.deleteById(id);
        }
}
