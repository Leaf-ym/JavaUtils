package com.ncepu.util;

import com.ncepu.model.BasicTypeEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 打印工具类
 * @date 2022/1/20 9:40
 */
public class PrintUtils {

    /***
     *
     * @param map
     *
     * @desc 打印Map
     *
     * @author wengym
     *
     * @date 2022/6/9 18:16
     *
     * @return void
     *
     */
    public static void println(Map<String, ? super Map> map ) {
        System.out.println(map);
    }

    /***
     *
     * @param list
     *
     * @desc 打印集合
     *
     * @author wengym
     *
     * @date 2022/1/20 9:41
     *
     * @return void
     *
     */
    public static <T> void println(Collection<T> list) {
        // 判空处理
        if (list == null) {
            PrintUtils.println("null");
            return;
        }
        if (list.isEmpty()) {
            PrintUtils.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // 添加左中括号
        sb.append("[");
        // 添加元素
        for (T object : list) {
            sb.append(object);
            sb.append(",");
        }
        // 去掉最后一个元素后面的逗号
        String str = null;
        if (sb.toString().endsWith(",")) {
            str = sb.substring(0, sb.length() - 1);
        }
        // 添加右中括号，并打印
        PrintUtils.println(str + "]");
    }

    /***
     *
     * @param arr
     *
     * @desc 打印数组
     *
     * @author wengym
     *
     * @date 2022/3/29 15:07
     *
     * @return void
     *
     */
    public static <T> void println(T[] arr) {
        // 判空处理
        if (arr == null) {
            PrintUtils.println("null");
            return;
        }
        if (arr.length == 0) {
            PrintUtils.println("[]");
            return;
        }
        PrintUtils.println(Arrays.asList(arr));
    }

    /***
     *
     * 打印Bean
     *
     * @param bean
     *
     * @author wengym
     *
     * @date 2022/3/8 14:37
     *
     * @return void
     *
     */
    public static <T> void println(T bean) {
        if (bean == null) {
            PrintUtils.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Class cls = bean.getClass();
        // 如果是基本数据类型，或是字符串类型，则转化为字符串打印
        Boolean isBasicType = PrintUtils.getBasicTypeList().contains(cls.getSimpleName()) || PrintUtils.getSimpleTypeList().contains(cls.getSimpleName());
        Boolean isStringType = "String".equals(cls.getSimpleName());
        if (isBasicType || isStringType) {
            PrintUtils.println(String.valueOf(bean));
            return;
        }
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method fieldGetMethod = cls.getMethod(fieldGetName);
                Object value = fieldGetMethod.invoke(bean);
                if (value != null) {
                    sb.append(fieldName + ":" + value);
                } else {
                    sb.append(fieldName + ":null");
                }
                sb.append(",");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String str = null;
        if (sb.toString().endsWith(",")) {
            str = sb.substring(0, sb.length() - 1);
        }

        PrintUtils.println(str + "]");
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static void println(Boolean bool) {
        System.out.println(bool);
    }

    public static List<String> getBasicTypeList() {
        String[] basicTypeArr = {
                BasicTypeEnum.BYTE.getBasicType(),
                BasicTypeEnum.SHORT.getBasicType(),
                BasicTypeEnum.INTEGER.getBasicType(),
                BasicTypeEnum.LONG.getBasicType(),
                BasicTypeEnum.FLOAT.getBasicType(),
                BasicTypeEnum.DOUBLE.getBasicType(),
                BasicTypeEnum.CHARACTER.getBasicType(),
                BasicTypeEnum.BOOLEAN.getBasicType()
        };
        return ArrayUtils.toList(basicTypeArr);
    }

    public static List<String> getSimpleTypeList() {
        String[] basicTypeArr = {
                BasicTypeEnum.BYTE.getSimpleTypeName(),
                BasicTypeEnum.SHORT.getSimpleTypeName(),
                BasicTypeEnum.INTEGER.getSimpleTypeName(),
                BasicTypeEnum.LONG.getSimpleTypeName(),
                BasicTypeEnum.FLOAT.getSimpleTypeName(),
                BasicTypeEnum.DOUBLE.getSimpleTypeName(),
                BasicTypeEnum.CHARACTER.getSimpleTypeName(),
                BasicTypeEnum.BOOLEAN.getSimpleTypeName()
        };
        return ArrayUtils.toList(basicTypeArr);
    }
}
