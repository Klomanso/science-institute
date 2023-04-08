package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class SpeciesController {

        private final SpeciesService speciesService;
}
