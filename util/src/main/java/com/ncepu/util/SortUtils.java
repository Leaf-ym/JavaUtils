package com.ncepu.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wengym
 * @version 1.0
 * @desc 排序工具类
 * @date 2022/10/13 10:31
 */
public class SortUtils {

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    /**
     * 获取降序的字符串比较器
     *
     * @param orderBy DESC降序，ASC升序
     *
     * @author wengym
     *
     * @date 2022/10/13 10:33
     *
     * @return java.util.Comparator
     */
    public static Comparator getStringComparator(String orderBy) {
        Comparator<String> keyComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (DESC.equals(orderBy)) {
                    // 降序
                    return o2.compareTo(o1);
                }
                // 默认升序
                return o1.compareTo(o2);
            }
        };
        return keyComparator;
    }

    /**
     * 对Map进行指定排序
     *
     * @param params 待排序的Map
     *
     * @param orderBy DESC降序，ASC升序
     *
     * @author wengym
     *
     * @date 2022/10/13 10:32
     *
     * @return java.util.TreeMap<java.lang.String,java.lang.Object>
     */
    public static <T> TreeMap<String, Object> getTreeMap(Map<String, T> params, String orderBy) {
        if (params == null) {
            throw new NullPointerException("params");
        }
        TreeMap<String, Object> sortedMap = new TreeMap<>(SortUtils.getStringComparator(orderBy));
        sortedMap.putAll(params);
        return sortedMap;
    }
}
