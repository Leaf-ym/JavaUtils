package com.ncepu.util.ValidateUtils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wengym
 * @version 1.0
 * @desc Java字段校验注解
 * @date 2022/12/27 11:05
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JavaFields {
    JavaField[] value();
}
