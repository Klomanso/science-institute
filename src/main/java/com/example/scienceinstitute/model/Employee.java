package com.example.scienceinstitute.model;

import com.example.scienceinstitute.validation.RequiredAge;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

        @Id
        @Length(max = 80)
        @Column(name = "contract_no", length = 10)
        @Pattern(regexp = "^\\d{7}\\Z", message = "Invalid Employee.contract_no. It must be 7 digits")
        private String contract;

        @Length(max = 80)
        @Column(name = "first_name", length = 80)
        private String firstName;

        @Length(max = 80)
        @Column(name = "last_name", length = 80)
        private String lastName;

        @NotNull
        @RequiredAge(years = 18)
        @Column(name = "birth_date", nullable = false)
        private Date birthDate;

        @NotNull
        @Column(name = "hire_date", nullable = false)
        private Date hireDate;

        @ManyToOne
        @JoinColumn(name = "title")
        private Title title;

        @ManyToOne
        @JoinColumn(name = "education")
        private Education education;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToMany(mappedBy = "team")
        private Set<Research> research = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @OneToMany(mappedBy = "lead", cascade = CascadeType.PERSIST)
        private List<Research> leadResearch = new ArrayList<>();

        @PreRemove
        private void preRemove() {
                leadResearch.forEach(lead -> lead.setLead(null));
                research.forEach(r -> r.getTeam().remove(this));
        }
}
