package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Integer> {

        List<Title> findAllByOrderByName();
}
