package com.ncepu.util.ValidateUtils;

import com.ncepu.util.ValidateUtils.annotation.JavaField;
import com.ncepu.util.ValidateUtils.annotation.JavaFields;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wengym
 * @version 1.0
 * @desc Java字段检验工具类
 * @date 2022/12/20 11:17
 */
public class ValidateUtils {
    /**
     * 检验model中的字段值是否符合规则
     *
     * @param model
     * @return java.lang.String
     * @author wengym
     * @date 2022/12/20 11:18
     */
    public static <T> String validate(T model) {
        if (model == null) {
            throw new NullPointerException("参数不能为null");
        }
        Class cls = model.getClass();
        Field[] fields = cls.getDeclaredFields();
        String validateResult;
        for (Field field : fields) {
            Annotation[] ans = field.getAnnotations();
            if (ans.length < 1) {
                continue;
            }
            for (Annotation an : ans) {
                if (an instanceof JavaFields) {
                    JavaField[] values = ((JavaFields)an).value();
                    for (JavaField javaField : values) {
                        validateResult = handleJavaField(javaField, model, field);
                        if (validateResult != null) {
                            return validateResult;
                        }
                    }
                }
                if (an instanceof JavaField) {
                    JavaField value = (JavaField)an;
                    validateResult = handleJavaField(value, model, field);
                    if (validateResult != null) {
                        return validateResult;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 处理单个字段校验注解
     *
     * @param javaField
     *
     * @param model
     *
     * @param field
     *
     * @author wengym
     *
     * @date 2022/12/27 11:24
     *
     * @return java.lang.String
     */
    private static <T> String handleJavaField(JavaField javaField, T model, Field field) {
        try {
            // 校验器的Class
            Class validatorCls = javaField.validator();
            // 检验方法
            Method validateMethod = validatorCls.getMethod("validateField", Object.class);
            // 调用方法
            field.setAccessible(true);
            Object fieldValue = field.get(model);
            field.setAccessible(false);
            Object result = validateMethod.invoke(validatorCls.newInstance(), fieldValue);
            // 结果处理
            Boolean isPass = (Boolean) result;
            if (!isPass) {
                // 没有通过，返回错误提示信息
                return javaField.message();
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
