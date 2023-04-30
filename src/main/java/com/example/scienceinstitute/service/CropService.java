package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.BadActionException;
import com.example.scienceinstitute.model.Crop;
import com.example.scienceinstitute.repository.CropRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CropService {

        private final CropRepository cropRepository;

        public List<Crop> findAll() {

                List<Crop> cropList = cropRepository.findAll();

                if (cropList.isEmpty()) {
                        throw new BadActionException("There isn't any crop");
                } else {
                        return cropList;
                }
        }

        public Crop findById(String id) {
                return cropRepository.findById(id)
                        .orElseThrow(() -> new BadActionException("There isn't crop with such number"));
        }

        @Modifying
        public Crop update(String id, Crop newCrop) {
                Crop cropToBeUpdated = findById(id);
                cropToBeUpdated.setName(newCrop.getName());
                cropToBeUpdated.setSpecies(newCrop.getSpecies());
                cropToBeUpdated.setWinterHardiness(newCrop.getWinterHardiness());
                cropToBeUpdated.setPdResistance(newCrop.getPdResistance());
                cropToBeUpdated.setYields(newCrop.getYields());
                cropToBeUpdated.setResearchResult(newCrop.getResearchResult());
                cropToBeUpdated.setNotes(newCrop.getNotes());
                return save(cropToBeUpdated);
        }

        public Crop save(Crop crop) {
                return cropRepository.save(crop);
        }

        public void deleteById(String id) {
                cropRepository.deleteById(id);
        }

        public boolean existsById(String id) {
                return cropRepository.existsById(id);
        }

        public List<Crop> findAllByOrderByName() {
                return cropRepository.findAllByOrderByName();
        }
}
