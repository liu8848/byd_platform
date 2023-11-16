package com.platform.aspects.validators;

import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(FactoryContactCreateDTOValidate.List.class)
public @interface FactoryContactCreateDTOValidate {
    String message() default "体系接口人信息错误";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List{
        FactoryContactCreateDTOValidate[] value();
    }
}
