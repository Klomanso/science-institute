package com.example.scienceinstitute.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RequiredAge.Validator.class})
public @interface RequiredAge {

        String message() default "Invalid employee age. It must be at least(year(s)): ";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        int years() default 0;

        class Validator implements ConstraintValidator<RequiredAge, Date> {

                private String message;
                private int allowable;

                @Override
                public void initialize(RequiredAge requiredAge) {
                        this.message = requiredAge.message();
                        this.allowable = requiredAge.years();
                }

                @Override
                public boolean isValid(Date birthDate, ConstraintValidatorContext context) {

                        boolean valid =
                                this.allowable <=
                                        Period.between(birthDate.toLocalDate(), LocalDate.now()).getYears();

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
