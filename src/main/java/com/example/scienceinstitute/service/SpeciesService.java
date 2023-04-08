package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.SpeciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpeciesService {

        private final SpeciesRepository speciesRepository;
}
