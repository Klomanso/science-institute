package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "crops")
public class Crop {

        @Id
        @Column(name = "brk_no", length = 10)
        @Pattern(regexp = "^\\d{4}\\Z", message = "Invalid Crop.brkNumber!")
        private String brkNumber;

        @Column(name = "name", nullable = false, length = 80)
        private String name;

        @ManyToOne
        @JoinColumn(name = "spec_no")
        private Species species;

        @Column(name = "winter_hardiness")
        private Boolean winterHardiness;

        @Column(name = "pd_resistance")
        private Boolean pdResistance;

        @Column(name = "yields")
        private Boolean yields;

        @ManyToOne
        @JoinColumn(name = "rsr_result")
        private Research researchResult;

        @Column(name = "notes", columnDefinition = "text")
        private String notes;

        @ManyToMany(cascade = { CascadeType.ALL })
        @EqualsAndHashCode.Exclude
        @JoinTable(
                name = "res_samples",
                joinColumns = { @JoinColumn(name = "brk_no") },
                inverseJoinColumns = { @JoinColumn(name = "res_id") }
        )
        Set<Research> research = new HashSet<>();
}
