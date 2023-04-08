package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.ResearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResearchService {

        private final ResearchRepository researchRepository;
}
