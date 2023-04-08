package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "titles")
public class Title {

        @Id
        @Column(name = "title_no")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer number;

        @Column(name = "title_name", nullable = false, length = 80)
        private String name;
}
