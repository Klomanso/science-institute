package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.CropService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CropController {

        private final CropService cropService;
}
