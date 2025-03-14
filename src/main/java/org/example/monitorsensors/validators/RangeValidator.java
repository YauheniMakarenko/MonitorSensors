package org.example.monitorsensors.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.monitorsensors.dto.Range;

public class RangeValidator implements ConstraintValidator<ValidRange, Range> {

    @Override
    public boolean isValid(Range range, ConstraintValidatorContext context) {
        if (range == null) {
            return true;
        }
        return range.getFrom() < range.getTo();
    }
}
