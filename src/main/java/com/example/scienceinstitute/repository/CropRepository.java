package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, String> {
}
