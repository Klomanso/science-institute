package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {
        List<Species> findAllByOrderByName();
}
