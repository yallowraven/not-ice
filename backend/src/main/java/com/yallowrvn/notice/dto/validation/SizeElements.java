package com.yallowrvn.notice.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SizeElementsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeElements {
    String message() default "must contain elements with size between {min} and {max}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    /**
     * @return size the elements must be higher or equal to
     */
    int min() default 0;

    /**
     * @return size the elements must be lower or equal to
     */
    int max() default Integer.MAX_VALUE;
}
