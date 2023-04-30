package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.BadActionException;
import com.example.scienceinstitute.model.Procedure;
import com.example.scienceinstitute.repository.ProcedureRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProcedureService {

        private final ProcedureRepository procedureRepository;

        public List<Procedure> findAll() {

                List<Procedure> procedures = procedureRepository.findAll();

                if (procedures.isEmpty()) {
                        throw new BadActionException("There isn't any procedure");
                } else {
                        return procedures;
                }
        }

        public Procedure findById(Integer id) {
                return procedureRepository.findById(id)
                        .orElseThrow(() -> new BadActionException("There isn't procedure with such number"));
        }

        @Modifying
        public Procedure update(Integer id, Procedure newProcedure) {
                Procedure procedureToBeUpdated = findById(id);
                procedureToBeUpdated.setName(newProcedure.getName());
                procedureToBeUpdated.setDuration(newProcedure.getDuration());
                procedureToBeUpdated.setDescription(newProcedure.getDescription());
                return save(procedureToBeUpdated);
        }

        public Procedure save(Procedure procedure) {
                return procedureRepository.save(procedure);
        }

        public void deleteById(Integer id) {
                procedureRepository.deleteById(id);
        }

        public List<Procedure> findAllByOrderByName() {
                return procedureRepository.findAllByOrderByName();
        }
}
