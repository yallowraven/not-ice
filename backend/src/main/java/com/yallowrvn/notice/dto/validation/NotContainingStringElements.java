package com.yallowrvn.notice.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotContainingStringElementsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotContainingStringElements {
    String message() default "must not contain elements that contain '{string}'";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    /**
     * @return the string the elements must not contain
     */
    String string();
}