package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "species")
public class Species {

        @Id
        @Column(name = "spec_no")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer number;

        @NotEmpty
        @Length(max = 50)
        @Column(name = "spec_name", nullable = false, length = 50)
        private String name;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @OneToMany(mappedBy = "species", cascade = CascadeType.PERSIST)
        private List<Crop> crops = new ArrayList<>();

        @PreRemove
        private void preRemove() {
                crops.forEach(crops -> crops.setSpecies(null));
        }
}
