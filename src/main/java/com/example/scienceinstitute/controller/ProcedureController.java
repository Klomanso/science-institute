package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Procedure;
import com.example.scienceinstitute.service.ProcedureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/procedures")
public class ProcedureController {

        private final ProcedureService procedureService;

        @GetMapping
        public ModelAndView getProcedures(ModelAndView modelAndView) {
                modelAndView.addObject("procedures", procedureService.findAll());
                modelAndView.setViewName("procedure/procedures");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public ModelAndView getProcedure(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                modelAndView.addObject("procedure", procedureService.findById(id));
                modelAndView.setViewName("procedure/procedure");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newProcedure(@ModelAttribute("procedure") Procedure procedure,
                                         ModelAndView modelAndView) {
                modelAndView.setViewName("procedure/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createProcedure(@ModelAttribute("procedure") @Valid Procedure procedure,
                                            BindingResult bindingResult, ModelAndView modelAndView) {

                if (bindingResult.hasErrors()) {
                        modelAndView.setViewName("procedure/new");
                        return modelAndView;
                }

                procedureService.save(procedure);
                modelAndView.setViewName("redirect:/procedures");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") Integer id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("procedure", procedureService.findById(id));
                modelAndView.setViewName("procedure/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editProcedure(@ModelAttribute("procedure") @Valid Procedure procedure,
                                          BindingResult bindingResult, ModelAndView modelAndView,
                                          @PathVariable("id") Integer id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.setViewName("procedure/edit");
                        return modelAndView;
                }

                procedureService.update(id, procedure);
                modelAndView.setViewName("redirect:/procedures");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteProcedure(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                procedureService.deleteById(id);
                modelAndView.setViewName("redirect:/procedures");
                return modelAndView;
        }
}
