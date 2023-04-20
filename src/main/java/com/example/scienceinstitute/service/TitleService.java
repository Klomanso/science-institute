package com.example.scienceinstitute.service;

import com.example.scienceinstitute.exception.ResourceNotFoundException;
import com.example.scienceinstitute.model.Title;
import com.example.scienceinstitute.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TitleService {

        private final TitleRepository titleRepository;

        public List<Title> findAll() {

                List<Title> titles = titleRepository.findAll();

                if (titles.isEmpty()) {
                        throw new ResourceNotFoundException("There isn't any title");
                } else {
                        return titles;
                }
        }

        public Title findById(Integer id) {
                return titleRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("There isn't title with such number"));
        }

        @Modifying
        public Title update(Integer id, Title newTitle) {
                Title titleToBeUpdated = findById(id);
                titleToBeUpdated.setName(newTitle.getName());
                return save(titleToBeUpdated);
        }

        public Title save(Title title) {
                return titleRepository.save(title);
        }

        public void deleteById(Integer id) {
                titleRepository.deleteById(id);
        }

        public List<Title> findAllByOrderByName() {
                return titleRepository.findAllByOrderByName();
        }
}
