package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Research;
import com.example.scienceinstitute.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/research")
public class ResearchController {

        private final ResearchService researchService;
        private final CustomerService customerService;
        private final EmployeeService employeeService;
        private final ProcedureService procedureService;
        private final CropService cropService;

        @GetMapping
        public ModelAndView getResearchList(ModelAndView modelAndView) {
                modelAndView.addObject("research", researchService.findAll());
                modelAndView.setViewName("research/allResearch");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public ModelAndView getResearch(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                modelAndView.addObject("research", researchService.findById(id));
                modelAndView.setViewName("research/research");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newResearch(@ModelAttribute("research") Research research,
                                        ModelAndView modelAndView) {

                modelAndView.addObject("customers", customerService.findAllByOrderByTitle());
                modelAndView.addObject("employees", employeeService.findAllByOrderByLastName());
                modelAndView.addObject("procedureList", procedureService.findAll());
                modelAndView.addObject("sampleList", cropService.findAll());
                modelAndView.setViewName("research/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createResearch(@ModelAttribute("research") @Valid Research research,
                                           BindingResult bindingResult, ModelAndView modelAndView) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("customers", customerService.findAllByOrderByTitle());
                        modelAndView.addObject("employees", employeeService.findAllByOrderByLastName());
                        modelAndView.addObject("procedureList", procedureService.findAll());
                        modelAndView.addObject("sampleList", cropService.findAll());
                        modelAndView.setViewName("research/new");
                        return modelAndView;
                }

                researchService.save(research);
                modelAndView.setViewName("redirect:/research");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") Integer id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("customers", customerService.findAllByOrderByTitle());
                modelAndView.addObject("employees", employeeService.findAllByOrderByLastName());
                modelAndView.addObject("research", researchService.findById(id));
                modelAndView.addObject("procedureList", procedureService.findAll());
                modelAndView.addObject("sampleList", cropService.findAll());
                modelAndView.setViewName("research/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editResearch(@ModelAttribute("research") @Valid Research research,
                                         BindingResult bindingResult, ModelAndView modelAndView,
                                         @PathVariable("id") Integer id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.addObject("customers", customerService.findAllByOrderByTitle());
                        modelAndView.addObject("employees", employeeService.findAllByOrderByLastName());
                        modelAndView.addObject("procedureList", procedureService.findAll());
                        modelAndView.addObject("sampleList", cropService.findAll());
                        modelAndView.setViewName("research/edit");
                        return modelAndView;
                }

                researchService.update(id, research);
                modelAndView.setViewName("redirect:/research");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteResearch(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                researchService.deleteById(id);
                modelAndView.setViewName("redirect:/research");
                return modelAndView;
        }
}
