package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class TitleController {

        private final TitleService titleService;
}
