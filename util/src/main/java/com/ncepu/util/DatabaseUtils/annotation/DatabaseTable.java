package com.ncepu.util.DatabaseUtils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 *
 * 数据库表注解
 *
 * @author wengym
 *
 * @date 2022/5/13 15:42
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseTable {
    String tableName();
    String primaryKey();
}
