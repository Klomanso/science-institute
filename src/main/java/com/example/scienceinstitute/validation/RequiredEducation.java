package com.example.scienceinstitute.validation;

import com.example.scienceinstitute.model.Employee;
import com.example.scienceinstitute.model.Research;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RequiredEducation.Validator.class})
public @interface RequiredEducation {

        String message() default "Invalid employee education in research team. It must be in: ";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String[] values();

        class Validator implements ConstraintValidator<RequiredEducation, Set<Employee>> {

                private String message;
                private List<String> allowable;

                @Override
                public void initialize(RequiredEducation requiredEducation) {
                        this.message = requiredEducation.message();
                        this.allowable = Arrays.asList(requiredEducation.values());
                }

                @Override
                public boolean isValid(Set<Employee> values, ConstraintValidatorContext context) {

                        boolean valid = true;
                        if (values != null) {
                                for (Employee e : values) {
                                        if (!allowable.contains(e.getEducation().getType())) {
                                                valid = false; break;
                                        }
                                }
                        }

                        if (!valid) {
                                context.disableDefaultConstraintViolation();
                                context.buildConstraintViolationWithTemplate(message.concat(this.allowable.toString()))
                                        .addConstraintViolation();
                        }

                        return valid;
                }
        }
}
