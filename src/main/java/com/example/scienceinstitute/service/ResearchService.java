package com.example.scienceinstitute.service;

import com.example.scienceinstitute.model.Research;
import com.example.scienceinstitute.repository.ResearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResearchService {

        private final ResearchRepository researchRepository;


        public List<Research> findAllByOrderByTitle() {
                return researchRepository.findAllByOrderByTitle();
        }
}
