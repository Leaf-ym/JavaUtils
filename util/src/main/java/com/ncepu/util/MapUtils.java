package com.ncepu.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wengym
 * @version 1.0
 * @desc 哈希表工具类
 * @date 2022/9/5 15:02
 */
public class MapUtils {

    /**
     * 基于Map获取get形式的参数
     *
     * @param paramMap
     *
     * @author wengym
     *
     * @date 2022/9/13 10:46
     *
     * @return java.lang.String
     */
    public static String getGetParams(Map<String, Object> paramMap) {
        if (paramMap == null || paramMap.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : paramMap.keySet()) {
            sb.append(key + "=" + paramMap.get(key) + "&");
        }
        return sb.toString().substring(0, sb.length() -1);
    }

    /**
     * 获取一个空的Map
     *
     * @author wengym
     *
     * @date 2022/9/5 15:04
     *
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> getHashMap() {
        return new HashMap();
    }

    /**
     * 基于Key对Map进行排序
     *
     * @param paramMap
     *
     * @author wengym
     *
     * @date 2022/9/13 10:07
     *
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> toSortByKey(Map<String, Object> paramMap) {
        if (paramMap == null || paramMap.size() < 1) {
            return paramMap;
        }
        Map<String, Object> treeMap = new TreeMap<>(new MyKeyComparator());
        for (String key : paramMap.keySet()) {
            treeMap.put(key, paramMap.get(key));
        }
        return treeMap;
    }

    /**
     * @author wengym
     * @version 1.0
     * @desc 定义key的比较器
     * key1 < key2：负整数
     * key1 = key2：0
     * key1 < key2：正整数
     * 升序排序，key1比key2小，则key1就排在key2之前
     * @date 2022/9/13 9:51
     */
    static class MyKeyComparator implements Comparator<String> {
        @Override
        public int compare(String key1, String key2) {
            return key1.compareTo(key2);
        }
    }
}
