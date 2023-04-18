package com.example.scienceinstitute.model;

import com.example.scienceinstitute.validation.RequiredAge;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
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

        @RequiredAge(years = 18)
        @Column(name = "birth_date", nullable = false)
        private Date birthDate;

        @Column(name = "hire_date", nullable = false)
        private Date hireDate;

        @ManyToOne
        @JoinColumn(name = "title")
        private Title title;

        @ManyToOne
        @JoinColumn(name = "education")
        private Education education;

        @ManyToMany
        @EqualsAndHashCode.Exclude
        @JoinTable(
                name = "res_team",
                joinColumns = { @JoinColumn(name = "contract_no") },
                inverseJoinColumns = { @JoinColumn(name = "res_id") }
        )
        Set<Research> research = new HashSet<>();
}
