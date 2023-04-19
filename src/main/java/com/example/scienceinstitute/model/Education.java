package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "education")
public class Education {

        @Id
        @Column(name = "edu_no")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer number;

        @NotEmpty
        @Length(max = 80)
        @Column(name = "edu_type", nullable = false, length = 80)
        private String type;

        @EqualsAndHashCode.Exclude
        @OneToMany(mappedBy = "education", cascade = CascadeType.PERSIST)
        private List<Employee> employees = new ArrayList<>();

        @PreRemove
        private void preRemove() {
                employees.forEach(employee -> employee.setEducation(null));
        }
}
