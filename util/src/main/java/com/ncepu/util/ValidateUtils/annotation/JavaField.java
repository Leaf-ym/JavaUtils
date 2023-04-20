package com.ncepu.util.ValidateUtils.annotation;

import java.lang.annotation.*;

/**
 * @author wengym
 * @version 1.0
 * @desc Java字段校验注解
 * @date 2022/12/20 11:05
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JavaFields.class)
public @interface JavaField {
    /**
     * 校验不通过的提示信息
     */
    String message();
    /**
     * 规则校验器
     */
    Class validator();
    /**
     * 校验器参数，可以是普通字符串，也可以是JSON字符串
     */
    String param() default "";
}
