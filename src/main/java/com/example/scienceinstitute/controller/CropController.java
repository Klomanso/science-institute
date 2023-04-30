package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Crop;
import com.example.scienceinstitute.service.CropService;
import com.example.scienceinstitute.service.ResearchService;
import com.example.scienceinstitute.service.SpeciesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/crops")
public class CropController {

        private final CropService cropService;
        private final SpeciesService speciesService;
        private final ResearchService researchService;

        @GetMapping
        public ModelAndView getCrops(ModelAndView modelAndView) {
                modelAndView.addObject("crops", cropService.findAll());
                modelAndView.setViewName("crop/crops");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public ModelAndView getCrop(@PathVariable("id") String id, ModelAndView modelAndView) {
                modelAndView.addObject("crop", cropService.findById(id));
                modelAndView.setViewName("crop/crop");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newCrop(@ModelAttribute("crop") Crop crop,
                                    ModelAndView modelAndView) {

                modelAndView.addObject("species", speciesService.findAllByOrderByName());
                modelAndView.addObject("researchList", researchService.findAllByOrderByTitle());
                modelAndView.setViewName("crop/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createCrop(@ModelAttribute("crop") @Valid Crop crop,
                                       BindingResult bindingResult, ModelAndView modelAndView) {

                if (cropService.existsById(crop.getBrkNumber())) {
                        bindingResult.addError(new FieldError("crop", "brkNumber",
                                "There is crop with such number, enter another number"));
                }

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("species", speciesService.findAllByOrderByName());
                        modelAndView.addObject("researchList", researchService.findAllByOrderByTitle());
                        modelAndView.setViewName("crop/new");
                        return modelAndView;
                }

                cropService.save(crop);
                modelAndView.setViewName("redirect:/crops");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") String id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("species", speciesService.findAllByOrderByName());
                modelAndView.addObject("researchList", researchService.findAllByOrderByTitle());
                modelAndView.addObject("crop", cropService.findById(id));
                modelAndView.setViewName("crop/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editCrop(@ModelAttribute("crop") @Valid Crop crop,
                                     BindingResult bindingResult, ModelAndView modelAndView,
                                     @PathVariable("id") String id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.addObject("species", speciesService.findAllByOrderByName());
                        modelAndView.addObject("researchList", researchService.findAllByOrderByTitle());
                        modelAndView.setViewName("crop/edit");
                        return modelAndView;
                }

                cropService.update(id, crop);
                modelAndView.setViewName("redirect:/crops");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteCrop(@PathVariable("id") String id, ModelAndView modelAndView) {
                cropService.deleteById(id);
                modelAndView.setViewName("redirect:/crops");
                return modelAndView;
        }
}
