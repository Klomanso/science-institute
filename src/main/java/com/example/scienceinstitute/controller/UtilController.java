package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Employee;
import com.example.scienceinstitute.service.EmployeeService;
import com.example.scienceinstitute.service.ResearchService;
import com.example.scienceinstitute.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


/*
        This controller need only to accomplish tasks
 */

@Controller
@AllArgsConstructor
public class UtilController {

        private final SpringTemplateEngine templateEngine;
        private final ResearchService researchService;
        private final EmployeeService employeeService;
        private final SpeciesService speciesService;

        @GetMapping("/home")
        public ModelAndView getHomePage(ModelAndView modelAndView) {
                modelAndView.setViewName("util/home");
                return modelAndView;
        }

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

        @GetMapping("research/{id}/pdf")
        public ResponseEntity<?> getPDF(ModelAndView modelAndView, Context context, @PathVariable("id") Integer id) {

                modelAndView.addObject("research", researchService.findById(id));
                context.setVariables(modelAndView.getModelMap());

                String html = templateEngine.process("util/researchReportToPDF", context);
                String headerValues = "attachment; filename=research" + id + "Report.pdf";

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(researchService.convertToPDF(html));
        }
}
