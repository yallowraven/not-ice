package com.yallowrvn.notice.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class NotBlankElementsValidator implements ConstraintValidator<NotBlankElements, Collection<? extends CharSequence>> {
    @Override
    public boolean isValid(Collection<? extends CharSequence> value, ConstraintValidatorContext context) {
        return value.stream().allMatch(e -> e != null && e.toString().trim().length() > 0);
    }
}
