package com.example.scienceinstitute.validation;

import com.example.scienceinstitute.model.Employee;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.Set;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RequiredTeamMembers.Validator.class})
public @interface RequiredTeamMembers {

        String message() default "Invalid amount of research team members. Required(min, max): ";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        int min() default 0;

        int max() default Integer.MAX_VALUE;


        class Validator implements ConstraintValidator<RequiredTeamMembers, Set<Employee>> {

                private String message;
                private int min;
                private int max;

                @Override
                public void initialize(RequiredTeamMembers requiredTeamMembers) {
                        this.message = requiredTeamMembers.message();
                        this.min = requiredTeamMembers.min();
                        this.max = requiredTeamMembers.max();
                }

                @Override
                public boolean isValid(Set<Employee> values, ConstraintValidatorContext context) {

                        boolean valid = true;
                        if (values != null) {
                                valid = this.min <= values.size() && values.size() <= this.max;
                        }

                        if (!valid) {
                                context.disableDefaultConstraintViolation();
                                context.buildConstraintViolationWithTemplate(message.
                                                concat(String.valueOf(this.min)).concat(", ")
                                                .concat(String.valueOf(this.max)))
                                        .addConstraintViolation();
                        }

                        return valid;
                }
        }
}
