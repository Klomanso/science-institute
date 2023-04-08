package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "education")
public class Education {

        @Id
        @Column(name = "edu_no")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer number;

        @Column(name = "edu_type", nullable = false, length = 80)
        private String type;
}
