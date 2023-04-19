package com.example.scienceinstitute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "customers")
public class Customer {

        @Id
        @Length(max = 20)
        @Column(name = "ogrn", length = 20)
        @Pattern(regexp = "^\\d{13}\\Z", message = "Invalid Customer.OGRN. Must be 13 digits")
        private String ogrn;

        @NotEmpty
        @Length(max = 80)
        @Column(name = "title", length = 80, nullable = false)
        private String title;

        @NotEmpty
        @Length(max = 50)
        @Column(name = "email", length = 50, nullable = false)
        private String email;

        @NotEmpty
        @Length(max = 50)
        @Column(name = "phone_number", length = 50, nullable = false)
        private String phone;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
        private List<Research> researchList = new ArrayList<>();

        @PreRemove
        private void preRemove() {
                researchList.forEach(research -> research.setCustomer(null));
        }
}
