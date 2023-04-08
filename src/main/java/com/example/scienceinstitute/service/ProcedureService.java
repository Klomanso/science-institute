package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.ProcedureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProcedureService {

        private final ProcedureRepository procedureRepository;
}
