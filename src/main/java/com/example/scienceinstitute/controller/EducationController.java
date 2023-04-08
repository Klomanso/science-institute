package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class EducationController {

        private final EducationService educationService;
}
