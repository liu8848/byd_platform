package com.platform.annotations;

import com.platform.validators.GradeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(GradeValidate.List.class)
@Constraint(validatedBy = {GradeValidator.class})
public @interface GradeValidate {
    String message() default "员工级别参数错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR,ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List{
        GradeValidate[] value();
    }
}
