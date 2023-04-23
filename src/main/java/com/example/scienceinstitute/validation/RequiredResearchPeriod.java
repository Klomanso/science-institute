package com.example.scienceinstitute.validation;

import com.example.scienceinstitute.model.Research;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.Period;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RequiredResearchPeriod.Validator.class})
public @interface RequiredResearchPeriod {

        String message() default "Invalid research period. Required(min, max): ";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        int monthsMin() default 0;

        int monthsMax() default Integer.MAX_VALUE;

        class Validator implements ConstraintValidator<RequiredResearchPeriod, Research> {

                private String message;
                private int min;

                private int max;

                @Override
                public void initialize(RequiredResearchPeriod period) {
                        this.message = period.message();
                        this.min = period.monthsMin();
                        this.max = period.monthsMax();
                }

                @Override
                public boolean isValid(Research research, ConstraintValidatorContext context) {

                        boolean valid = true;
                        if (research != null && research.getFromDate() != null && research.getFinishDate() != null) {
                                valid =
                                        (min <= Period.between(research.getFromDate().toLocalDate(),
                                                research.getFinishDate().toLocalDate()).getMonths())
                                                &&
                                                (max >= Period.between(research.getFromDate().toLocalDate(),
                                                        research.getFinishDate().toLocalDate()).getMonths());
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
