package com.example.scienceinstitute.model;

import com.example.scienceinstitute.validation.RequiredEducation;
import com.example.scienceinstitute.validation.RequiredExperience;
import com.example.scienceinstitute.validation.RequiredResearchPeriod;
import com.example.scienceinstitute.validation.RequiredTeamMembers;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@RequiredResearchPeriod(monthsMin = 3, monthsMax = 12)
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

        @Column(name = "from_date", nullable = false)
        private Date fromDate;

        @Column(name = "finish_date", nullable = false)
        private Date finishDate;

        @PositiveOrZero
        @Column(name = "budget", precision = 12, scale = 2)
        private BigDecimal budget;

        @ManyToOne
        @JoinColumn(name = "lead_no", referencedColumnName = "contract_no")
        @RequiredExperience(years = 2)
        private Employee lead;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToMany(mappedBy = "research")
        private Set<Procedure> procedures = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToMany(mappedBy = "research")
        @RequiredTeamMembers(min = 3, max = 7)
        private Set<@RequiredEducation(values = {"высшее", "бакалавр", "магистр",
                "кандидат наук", "доктор наук"}) Employee> team = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToMany(mappedBy = "research")
        private Set<Crop> samples = new HashSet<>();
}
