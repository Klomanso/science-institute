package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.model.Title;
import com.example.scienceinstitute.service.TitleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/titles")
public class TitleController {

        private final TitleService titleService;

        @GetMapping
        public ModelAndView getTitles(ModelAndView modelAndView) {
                modelAndView.addObject("titles", titleService.findAll());
                modelAndView.setViewName("title/titles");
                return modelAndView;
        }

        @GetMapping("/{id}")
        public ModelAndView getTitle(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                modelAndView.addObject("title", titleService.findById(id));
                modelAndView.setViewName("title/title");
                return modelAndView;
        }

        @GetMapping("/new")
        public ModelAndView newTitle(@ModelAttribute("title") Title title,
                                     ModelAndView modelAndView) {
                modelAndView.setViewName("title/new");
                return modelAndView;
        }

        @PostMapping
        public ModelAndView createTitle(@ModelAttribute("title") @Valid Title title,
                                        BindingResult bindingResult, ModelAndView modelAndView) {

                if (bindingResult.hasErrors()) {
                        modelAndView.setViewName("title/new");
                        return modelAndView;
                }

                titleService.save(title);
                modelAndView.setViewName("redirect:/titles");

                return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") Integer id) {
                modelAndView.addObject("id", id);
                modelAndView.addObject("title", titleService.findById(id));
                modelAndView.setViewName("title/edit");
                return modelAndView;
        }

        @PatchMapping("/{id}")
        public ModelAndView editTitle(@ModelAttribute("title") @Valid Title title,
                                      BindingResult bindingResult, ModelAndView modelAndView,
                                      @PathVariable("id") Integer id) {

                if (bindingResult.hasErrors()) {
                        modelAndView.addObject("id", id);
                        modelAndView.setViewName("title/edit");
                        return modelAndView;
                }

                titleService.update(id, title);
                modelAndView.setViewName("redirect:/titles");

                return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ModelAndView deleteTitle(@PathVariable("id") Integer id, ModelAndView modelAndView) {
                titleService.deleteById(id);
                modelAndView.setViewName("redirect:/titles");
                return modelAndView;
        }
}
