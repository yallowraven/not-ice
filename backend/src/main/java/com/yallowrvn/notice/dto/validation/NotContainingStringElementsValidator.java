package com.yallowrvn.notice.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class NotContainingStringElementsValidator
        implements ConstraintValidator<NotContainingStringElements, Collection<? extends CharSequence>> {
    private String string;

    @Override
    public boolean isValid(Collection<? extends CharSequence> value, ConstraintValidatorContext context) {
        return value == null || value.stream().noneMatch(e -> e.toString().contains(string));
    }

    @Override
    public void initialize(NotContainingStringElements constraintAnnotation) {
        this.string = constraintAnnotation.string();
    }
}