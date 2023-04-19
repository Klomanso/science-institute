package com.example.scienceinstitute.model;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.time.DurationMax;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "procedures")
public class Procedure {

        @Id
        @Column(name = "proc_no")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer number;

        @NotEmpty
        @Length(max = 80)
        @Column(name = "proc_name", nullable = false, length = 80)
        private String name;

        @Column(name = "description", columnDefinition = "text")
        private String description;

        @NotNull
        @DurationMax(days = 7)
        @Type(PostgreSQLIntervalType.class)
        @Column(name = "duration", columnDefinition = "interval", nullable = false)
        private Duration duration;

        @ManyToMany
        @EqualsAndHashCode.Exclude
        @JoinTable(
                name = "res_procedures",
                joinColumns = {@JoinColumn(name = "proc_no")},
                inverseJoinColumns = {@JoinColumn(name = "res_id")}
        )
        Set<Research> research = new HashSet<>();
}
