package com.example.scienceinstitute.model;

import com.example.scienceinstitute.validation.RequiredEducation;
import com.example.scienceinstitute.validation.RequiredExperience;
import com.example.scienceinstitute.validation.RequiredResearchPeriod;
import com.example.scienceinstitute.validation.RequiredTeamMembers;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        @NotEmpty
        @Length(max = 150)
        @Column(name = "title", length = 150, nullable = false)
        private String title;

        @ManyToOne
        @JoinColumn(name = "ogrn")
        private Customer customer;

        @NotNull
        @Column(name = "from_date", nullable = false)
        private Date fromDate;

        @NotNull
        @Column(name = "finish_date", nullable = false)
        private Date finishDate;

        @NotNull
        @PositiveOrZero(message = "Budget must be >= 0")
        @Column(name = "budget", precision = 12, scale = 2)
        private BigDecimal budget;

        @ManyToOne
        @JoinColumn(name = "lead_no", referencedColumnName = "contract_no")
        @RequiredExperience(years = 2)
        private Employee lead;

        @ManyToMany
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JoinTable(
                name = "res_team",
                joinColumns = {@JoinColumn(name = "res_id")},
                inverseJoinColumns = {@JoinColumn(name = "contract_no")}
        )
        @RequiredEducation(values = {"высшее", "бакалавр", "магистр", "кандидат наук", "доктор наук"})
        @RequiredTeamMembers(min = 3, max = 7)
        Set<Employee> team = new HashSet<>();

        @ManyToMany
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JoinTable(
                name = "res_samples",
                joinColumns = {@JoinColumn(name = "res_id")},
                inverseJoinColumns = {@JoinColumn(name = "brk_no")}
        )
        Set<Crop> samples = new HashSet<>();

        @ManyToMany
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JoinTable(
                name = "res_procedures",
                joinColumns = {@JoinColumn(name = "res_id")},
                inverseJoinColumns = {@JoinColumn(name = "proc_no")}
        )
        Set<Procedure> procedures = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @OneToMany(mappedBy = "researchResult", cascade = CascadeType.PERSIST)
        private List<Crop> cropsResultList = new ArrayList<>();

        @PreRemove
        private void preRemove() {
                cropsResultList.forEach(crop -> crop.setResearchResult(null));
        }
}
