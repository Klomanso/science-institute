package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.CropRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CropService {

        private final CropRepository cropRepository;
}
