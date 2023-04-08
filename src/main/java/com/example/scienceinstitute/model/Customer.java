package com.example.scienceinstitute.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

        @Id
        @Column(name = "ogrn", length = 20)
        @Pattern(regexp = "^\\d{13}\\Z", message = "Invalid Customer.OGRN!")
        private String ogrn;

        @Column(name = "title", length = 80, nullable = false)
        private String title;

        @Column(name = "email", length = 50, nullable = false)
        private String email;

        @Column(name = "phone_number", length = 50, nullable = false)
        private String phone;
}
