package com.example.scienceinstitute.controller;

import com.example.scienceinstitute.service.ProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ProcedureController {

        private final ProcedureService procedureService;
}
