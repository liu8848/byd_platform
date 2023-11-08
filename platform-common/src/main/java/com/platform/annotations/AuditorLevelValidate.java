package com.platform.annotations;

import com.platform.validators.AuditorLevelValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(AuditorLevelValidate.List.class)
@Constraint(validatedBy = {AuditorLevelValidator.class})
public @interface AuditorLevelValidate {
    String message() default "审核员等级参数错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR,ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List{
        AuditorLevelValidate[] value();
    }
}
