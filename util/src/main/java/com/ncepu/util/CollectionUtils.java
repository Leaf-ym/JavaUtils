package com.ncepu.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author wengym
 * @version 1.0
 * @desc 集合工具类
 * @date 2022/5/11 10:32
 */
public class CollectionUtils {

    /***
     *
     * 过滤列表
     *
     * @param list
     *
     * @param predicate 过滤的谓语，即lambda表达式，作为过滤的条件
     *
     * @author wengym
     *
     * @date 2022/5/25 11:45
     *
     * @return java.util.List<T>
     *
     */
    public static <T> List<T> listFilter(List<T> list, Predicate<T> predicate) {
        List<T> filterList = list.stream().filter(predicate).collect(Collectors.toList());
        return filterList;
    }

    /***
     *
     * 获取两个列表的交集（元素类型可为字符串和基本数据类型）
     *
     * @param list1
     * @param list2
     *
     * @author wengym
     *
     * @date 2022/5/11 11:09
     *
     * @return java.util.List<T>
     *
     */
    public static <T> List<T> getIntersectList(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();
        if (list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty()) {
            return list;
        }
        for (T item : list1) {
            if (list2.contains(item)) {
                list.add(item);
            }
        }
        return list;
    }

    /***
     *
     * 初始化列表
     *
     * @param args
     *
     * @author wengym
     *
     * @date 2022/5/11 11:20
     *
     * @return java.util.List<T>
     *
     */
    public static <T> List<T> getList(T... args) {
        List<T> list = new ArrayList<>();
        if (args == null || args.length == 0) {
            return list;
        }
        for (T item : args) {
            list.add(item);
        }
        return list;
    }

    /***
     *
     * 初始化集合
     *
     * @param args
     *
     * @author wengym
     *
     * @date 2022/5/11 11:20
     *
     * @return java.util.List<T>
     *
     */
    public static <T> Set<T> getSet(T... args) {
        Set<T> set = new HashSet<>();
        if (args == null || args.length == 0) {
            return set;
        }
        for (T item : args) {
            set.add(item);
        }
        return set;
    }

    public static <T> List<T> toList(Set<T> set) {
        List<T> list = new ArrayList<>();
        if (set == null || set.size() == 0) {
            return list;
        }
        for (T item : set) {
            list.add(item);
        }
        return list;
    }
}
