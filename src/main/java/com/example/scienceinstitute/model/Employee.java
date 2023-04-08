package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

        @Id
        @Column(name = "contract_no", length = 10)
        @Pattern(regexp = "^\\d{7}\\Z", message = "Invalid Employee.contract_no!")
        private String contract;

        @Column(name = "first_name", length = 80)
        private String firstName;

        @Column(name = "last_name", length = 80)
        private String lastName;

        @Temporal(TemporalType.DATE)
        @Column(name = "birth_date", nullable = false,
                columnDefinition = "DATE CHECK (age(now(), (birth_date)) >= '18 years')")
        private Date birthDate;

        @Temporal(TemporalType.DATE)
        @Column(name = "hire_date", nullable = false)
        private Date hireDate;

        @ManyToOne
        @JoinColumn(name = "title")
        private Title title;

        @ManyToOne
        @JoinColumn(name = "education")
        private Education education;

        @ManyToMany(cascade = { CascadeType.ALL })
        @EqualsAndHashCode.Exclude
        @JoinTable(
                name = "res_team",
                joinColumns = { @JoinColumn(name = "contract_no") },
                inverseJoinColumns = { @JoinColumn(name = "res_id") }
        )
        Set<Research> research = new HashSet<>();
}