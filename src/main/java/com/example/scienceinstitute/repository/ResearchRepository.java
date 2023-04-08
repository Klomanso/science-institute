package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearchRepository extends JpaRepository<Research, Integer> {
}
