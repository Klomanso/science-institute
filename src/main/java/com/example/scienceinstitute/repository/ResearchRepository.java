package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResearchRepository extends JpaRepository<Research, Integer> {

        List<Research> findAllByOrderByTitle();
}
