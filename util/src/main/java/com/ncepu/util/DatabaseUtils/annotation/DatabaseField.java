package com.ncepu.util.DatabaseUtils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 *
 * 数据库字段标识注解
 * 只有当定义注解时使用了 @Retention(RetentionPolicy.RUNTIME) 修饰，该注解才会在运行时可见。
 *
 * @author wengym
 *
 * @date 2022/3/10 15:15
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseField {
    /**
     * 数据类型
     */
    String dataType() default "VARCHAR(255)";

    /**
     * 约束
     */
    String constraint() default "";

    /**
     * 默认值
     */
    String defaultValue() default "";

    /**
     * 注释
     */
    String comment() default "";
}
