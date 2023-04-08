package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "species")
public class Species {

        @Id
        @Column(name = "spec_no")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer number;

        @Column(name = "spec_name", nullable = false, length = 50)
        private String name;
}
