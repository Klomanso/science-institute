package com.example.scienceinstitute.model;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "research")
public class Research {

        @Id
        @Column(name = "res_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "title", length = 150, nullable = false)
        private String title;

        @ManyToOne
        @JoinColumn(name = "ogrn")
        private Customer customer;

        @Temporal(TemporalType.DATE)
        @Column(name = "from_date", nullable = false)
        private Date fromDate;

        @Type(PostgreSQLIntervalType.class)
        @DurationMin(days = 91)
        @DurationMax(days = 365)
        @Column(name = "duration",
                columnDefinition = "interval check (duration >= '3 mons' and duration <= '12 mons')",
                nullable = false)
        private Duration duration;

        @PositiveOrZero
        @Column(name = "budget", precision = 12, scale = 2,
                columnDefinition = "numeric(12,2) check (budget > 0)")
        private BigDecimal budget;

        @ManyToOne
        @JoinColumn(name = "lead_no", referencedColumnName = "contract_no")
        private Employee lead;

        @EqualsAndHashCode.Exclude
        @ManyToMany(mappedBy = "research")
        private Set<Procedure> procedures = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @ManyToMany(mappedBy = "research")
        private Set<Employee> team = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @ManyToMany(mappedBy = "research")
        private Set<Employee> samples = new HashSet<>();
}