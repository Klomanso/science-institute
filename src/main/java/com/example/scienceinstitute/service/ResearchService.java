package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.ResourceNotFoundException;
import com.example.scienceinstitute.model.Customer;
import com.example.scienceinstitute.model.Research;
import com.example.scienceinstitute.repository.ResearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResearchService {

        private final ResearchRepository researchRepository;


        public List<Research> findAllByOrderByTitle() {
                return researchRepository.findAllByOrderByTitle();
        }

        public List<Research> findAll() {

                List<Research> researchList = researchRepository.findAll();

                if (researchList.isEmpty()) {
                        throw new ResourceNotFoundException("There isn't any research");
                } else {
                        return researchList;
                }
        }

        public Research findById(Integer id) {
                return researchRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("There isn't research with such number"));
        }

        @Modifying
        public Research update(Integer id, Research newResearch) {
                Research researchToBeUpdated = findById(id);
                researchToBeUpdated.setTitle(newResearch.getTitle());
                researchToBeUpdated.setCustomer(newResearch.getCustomer());
                researchToBeUpdated.setFromDate(newResearch.getFromDate());
                researchToBeUpdated.setFinishDate(newResearch.getFinishDate());
                researchToBeUpdated.setBudget(newResearch.getBudget());
                researchToBeUpdated.setLead(newResearch.getLead());
                researchToBeUpdated.setTeam(newResearch.getTeam());
                researchToBeUpdated.setProcedures(newResearch.getProcedures());
                researchToBeUpdated.setSamples(newResearch.getSamples());
                return save(researchToBeUpdated);
        }

        public Research save(Research research) {
                return researchRepository.save(research);
        }

        public void deleteById(Integer id) {
                researchRepository.deleteById(id);
        }
}
