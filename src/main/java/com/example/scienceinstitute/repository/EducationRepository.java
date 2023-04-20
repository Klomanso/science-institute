package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Education;
import com.example.scienceinstitute.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Integer> {

        List<Education> findAllByOrderByType();
}
