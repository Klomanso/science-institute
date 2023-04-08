package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Integer> {
}
