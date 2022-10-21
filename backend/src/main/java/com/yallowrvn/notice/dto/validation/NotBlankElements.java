package com.yallowrvn.notice.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotBlankElementsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankElements {
    String message() default "must not contain blank elements";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
