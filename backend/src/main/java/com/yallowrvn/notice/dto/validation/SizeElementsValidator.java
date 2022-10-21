package com.yallowrvn.notice.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class SizeElementsValidator implements ConstraintValidator<SizeElements, Collection<? extends CharSequence>> {

    private int min, max;

    @Override
    public boolean isValid(Collection<? extends CharSequence> value, ConstraintValidatorContext context) {
        return value.stream().allMatch(e -> e.length() >= min && e.length() <= max);
    }

    @Override
    public void initialize(SizeElements constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
}
