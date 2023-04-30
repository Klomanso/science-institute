package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, String> {

        List<Crop> findAllByOrderByName();
}
