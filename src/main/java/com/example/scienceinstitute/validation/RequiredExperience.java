package com.example.scienceinstitute.validation;

import com.example.scienceinstitute.model.Employee;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.Period;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RequiredExperience.Validator.class})
public @interface RequiredExperience {

        String message() default "Invalid lead experience in research. It must be at least(year(s)): ";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        int years() default 0;

        class Validator implements ConstraintValidator<RequiredExperience, Employee> {

                private String message;
                private int allowable;

                @Override
                public void initialize(RequiredExperience requiredExperience) {
                        this.message = requiredExperience.message();
                        this.allowable = requiredExperience.years();
                }

                @Override
                public boolean isValid(Employee lead, ConstraintValidatorContext context) {

                        boolean valid = true;
                        if (lead != null) {
                                 valid =
                                        this.allowable <=
                                                Period.between(lead.getHireDate().toLocalDate(), LocalDate.now())
                                                        .getYears();
                        }

                        if (!valid) {
                                context.disableDefaultConstraintViolation();
                                context.buildConstraintViolationWithTemplate(message
                                                .concat(String.valueOf(this.allowable)))
                                        .addConstraintViolation();
                        }

                        return valid;
                }
        }
}
