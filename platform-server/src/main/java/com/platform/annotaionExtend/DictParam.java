package com.platform.annotaionExtend;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictParam {
    String targetField() default "";

    String field();

    String dictType();
}
