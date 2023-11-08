package com.platform.annotations;

import java.lang.annotation.*;

/***
 * 用于标记数据导出合并及合并主键
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CollectCustomMerge {

    /**
     * 是否需要合并单元格
     */
    boolean needMerge() default false;

    /**
     * 是否主键，相同主键的行合并
     */
    boolean isPK() default false;
}
