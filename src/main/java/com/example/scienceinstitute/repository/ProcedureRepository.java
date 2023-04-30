package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {

        List<Procedure> findAllByOrderByName();
}
