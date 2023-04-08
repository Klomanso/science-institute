package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
}
