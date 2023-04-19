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
@Table(name = "titles")
public class Title {

        @Id
        @Column(name = "title_no")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer number;

        @NotEmpty
        @Length(max = 80)
        @Column(name = "title_name", nullable = false, length = 80)
        private String name;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @OneToMany(mappedBy = "title", cascade = CascadeType.PERSIST)
        private List<Employee> employees = new ArrayList<>();

        @PreRemove
        private void preRemove() {
                employees.forEach(employee -> employee.setTitle(null));
        }
}
