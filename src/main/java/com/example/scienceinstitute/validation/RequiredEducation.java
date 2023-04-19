package com.example.scienceinstitute.validation;

import com.example.scienceinstitute.model.Employee;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.List;

@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RequiredEducation.Validator.class})
public @interface RequiredEducation {

        String message() default "Invalid employee education in research team. It must be in: ";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String[] values();

        class Validator implements ConstraintValidator<RequiredEducation, Employee> {

                private String message;
                private List<String> allowable;

                @Override
                public void initialize(RequiredEducation requiredEducation) {
                        this.message = requiredEducation.message();
                        this.allowable = Arrays.asList(requiredEducation.values());
                }

                @Override
                public boolean isValid(Employee employee, ConstraintValidatorContext context) {

                        boolean valid = this.allowable.contains(employee.getEducation().getType());

                        if (!valid) {
                                context.disableDefaultConstraintViolation();
                                context.buildConstraintViolationWithTemplate(message.concat(this.allowable.toString()))
                                        .addConstraintViolation();
                        }

                        return valid;
                }
        }
}
