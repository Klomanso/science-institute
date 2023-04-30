package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Employee;
import com.example.scienceinstitute.service.EmployeeService;
import com.example.scienceinstitute.service.ResearchService;
import com.example.scienceinstitute.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


/*
        This controller need only to accomplish tasks
 */

@Controller
@AllArgsConstructor
public class UtilController {

        private ResearchService researchService;
        private EmployeeService employeeService;
        private SpeciesService speciesService;

        @GetMapping("/joinQuery")
        public ModelAndView getJoinQuery(ModelAndView modelAndView) {
                modelAndView.addObject("research", researchService.joinQuery());
                modelAndView.setViewName("util/joinQuery");
                return modelAndView;
        }

        @GetMapping("/conditionalQuery")
        public ModelAndView getConditionalQuery(ModelAndView modelAndView) {
                modelAndView.addObject("employees", employeeService.conditionalQuery());
                modelAndView.setViewName("util/conditionalQuery");
                return modelAndView;
        }

        @GetMapping("/parameterizedQuery")
        public ModelAndView getParameterizedQuery(@ModelAttribute("empResearch") Employee employee,
                                                  ModelAndView modelAndView) {
                modelAndView.addObject("research", researchService.findAllByOrderByTitle());
                modelAndView.addObject("employees", employeeService
                        .parameterizedQuery(employee.getLeadResearch()));
                modelAndView.setViewName("util/parameterizedQuery");
                return modelAndView;
        }

        @PostMapping("/parameterizedQuery")
        public ModelAndView getParameterizedPQuery(@ModelAttribute("empResearch") Employee employee,
                                                   ModelAndView modelAndView) {
                modelAndView.addObject("research", researchService.findAllByOrderByTitle());
                modelAndView.addObject("employees", employeeService
                        .parameterizedQuery(employee.getLeadResearch()));
                modelAndView.setViewName("util/parameterizedQuery");
                return modelAndView;
        }

        @GetMapping("/finalQuery")
        public ModelAndView getFinalQuery(ModelAndView modelAndView) {
                modelAndView.addObject("researchList", speciesService.finalQuery());
                modelAndView.setViewName("util/finalQuery");
                return modelAndView;
        }

        @GetMapping("/crossQuery")
        public ModelAndView getCrossQuery(ModelAndView modelAndView) {
                modelAndView.addObject("researchList", researchService.crossQuery());
                modelAndView.setViewName("util/crossQuery");
                return modelAndView;
        }

        @GetMapping("/researchView")
        public ModelAndView createResearchView(ModelAndView modelAndView) {
                researchService.createViewOfResearchReport();
                modelAndView.setViewName("util/home");
                return modelAndView;
        }
}
