package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {
        List<Species> findAllByOrderByName();


        @Query("select s.name as speciesName, count(cr.id) as totalResearch from Species s left join s.crops c " +
                "left join c.research cr group by s.name")
        List<SpeciesRepository.IResearchSamplesCount> finalQuery();

        interface IResearchSamplesCount {
                String getSpeciesName();

                Integer getTotalResearch();
        }
}
