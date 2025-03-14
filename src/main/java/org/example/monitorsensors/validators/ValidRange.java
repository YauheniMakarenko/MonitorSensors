package org.example.monitorsensors.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRange {
    String message() default "Range 'from' must be less than 'to'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
