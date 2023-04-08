package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.EducationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EducationService {

        private final EducationRepository educationRepository;
}
