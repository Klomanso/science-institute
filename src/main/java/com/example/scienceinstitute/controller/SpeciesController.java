package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Procedure;
import com.example.scienceinstitute.model.Species;
import com.example.scienceinstitute.service.SpeciesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/species")
public class SpeciesController {

        private final SpeciesService speciesService;

        @GetMapping
        public ModelAndView getSpecies(ModelAndView modelAndView) {
                modelAndView.addObject("species", speciesService.findAll());
                modelAndView.setViewName("species/allSpecies");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public  ModelAndView getSpecies(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                modelAndView.addObject("species", speciesService.findById(id));
                modelAndView.setViewName("species/species");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newSpecies(@ModelAttribute("species") Species species,
                                         ModelAndView modelAndView) {
                modelAndView.setViewName("species/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createSpecies(@ModelAttribute("species") @Valid Species species,
                                            BindingResult bindingResult, ModelAndView modelAndView) {

                if (bindingResult.hasErrors()) {
                        modelAndView.setViewName("species/new");
                        return modelAndView;
                }

                speciesService.save(species);
                modelAndView.setViewName("redirect:/species");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") Integer id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("species", speciesService.findById(id));
                modelAndView.setViewName("species/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editSpecies(@ModelAttribute("species") @Valid Species species,
                                          BindingResult bindingResult, ModelAndView modelAndView,
                                          @PathVariable("id") Integer id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.setViewName("species/edit");
                        return modelAndView;
                }

                speciesService.update(id, species);
                modelAndView.setViewName("redirect:/species");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteSpecies(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                speciesService.deleteById(id);
                modelAndView.setViewName("redirect:/species");
                return modelAndView;
        }
}
