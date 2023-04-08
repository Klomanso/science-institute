package com.example.scienceinstitute.service;

import com.example.scienceinstitute.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TitleService {

        private final TitleRepository titleRepository;
}
