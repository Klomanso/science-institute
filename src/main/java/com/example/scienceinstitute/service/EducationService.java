package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.BadActionException;
import com.example.scienceinstitute.model.Education;
import com.example.scienceinstitute.repository.EducationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EducationService {

        private final EducationRepository educationRepository;

        public List<Education> findAll() {

                List<Education> educationList = educationRepository.findAll();

                if (educationList.isEmpty()) {
                        throw new BadActionException("There isn't any education type");
                } else {
                        return educationList;
                }
        }

        public Education findById(Integer id) {
                return educationRepository.findById(id)
                        .orElseThrow(() -> new BadActionException("There isn't education with such number"));
        }

        @Modifying
        public Education update(Integer id, Education newEducation) {
                Education educationToBeUpdated = findById(id);
                educationToBeUpdated.setType(newEducation.getType());
                return save(educationToBeUpdated);
        }

        public Education save(Education education) {
                return educationRepository.save(education);
        }

        public void deleteById(Integer id) {
                educationRepository.deleteById(id);
        }

        public List<Education> findAllByOrderByType() {
                return educationRepository.findAllByOrderByType();
        }
}
