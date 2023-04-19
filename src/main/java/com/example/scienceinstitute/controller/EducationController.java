package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Education;
import com.example.scienceinstitute.service.EducationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/education")
public class EducationController {

        private final EducationService educationService;

        @GetMapping
        public ModelAndView getEducation(ModelAndView modelAndView) {
                modelAndView.addObject("education", educationService.findAll());
                modelAndView.setViewName("education/allEducation");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public ModelAndView getEducation(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                modelAndView.addObject("education", educationService.findById(id));
                modelAndView.setViewName("education/education");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newEducation(@ModelAttribute("education") Education education,
                                         ModelAndView modelAndView) {
                modelAndView.setViewName("education/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createEducation(@ModelAttribute("education") @Valid Education education,
                                            BindingResult bindingResult, ModelAndView modelAndView) {

                if (bindingResult.hasErrors()) {
                        modelAndView.setViewName("education/new");
                        return modelAndView;
                }

                educationService.save(education);
                modelAndView.setViewName("redirect:/education");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") Integer id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("education", educationService.findById(id));
                modelAndView.setViewName("education/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editEducation(@ModelAttribute("education") @Valid Education education,
                                          BindingResult bindingResult, ModelAndView modelAndView,
                                          @PathVariable("id") Integer id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.setViewName("education/edit");
                        return modelAndView;
                }

                educationService.update(id, education);
                modelAndView.setViewName("redirect:/education");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteEducation(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                educationService.deleteById(id);
                modelAndView.setViewName("redirect:/education");
                return modelAndView;
        }
}
