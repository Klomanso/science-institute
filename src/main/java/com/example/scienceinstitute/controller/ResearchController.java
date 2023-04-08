package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.ResearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ResearchController {

        private final ResearchService researchService;
}
