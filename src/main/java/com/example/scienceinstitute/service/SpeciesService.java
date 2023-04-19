package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.ResourceNotFoundException;
import com.example.scienceinstitute.model.Species;
import com.example.scienceinstitute.repository.SpeciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpeciesService {

        private final SpeciesRepository speciesRepository;

        public List<Species> findAll() {

                List<Species> species = speciesRepository.findAll();

                if (species.isEmpty()) {
                        throw new ResourceNotFoundException("There isn't any species");
                } else {
                        return species;
                }
        }

        public Species findById(Integer id) {
                return speciesRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("There isn't species with such number"));
        }

        @Modifying
        public Species update(Integer id, Species newSpecies) {
                Species speciesToBeUpdated = findById(id);
                speciesToBeUpdated.setName(newSpecies.getName());
                return save(speciesToBeUpdated);
        }

        public Species save(Species species) {
                return speciesRepository.save(species);
        }

        public void deleteById(Integer id) {
                speciesRepository.deleteById(id);
        }

        public List<Species> findAllByOrderByName() {
                return speciesRepository.findAllByOrderByName();
        }
}
