package com.ncepu.util;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * 数组工具类
 *
 * @author wengym
 * @date 2021-11-2
 */
public class ArrayUtils {

    /***
     *
     * 基于参数数组生成List集合
     *
     * @param args
     *
     * @author wengym
     *
     * @date 2022/3/31 8:36
     *
     * @return java.util.List<T>
     *
     */
    public static <T> List<T> toList(T... args) {
        if (args == null || args.length == 0) {
            return new ArrayList<T>(0);
        }
        List<T> list = new ArrayList<>(args.length);
        for (T item : args) {
            list.add(item);
        }
        return list;
    }

    /**
     * 把一个字符串数组以特定分隔符拼接成字符串
     *
     * @param arr
     * @param separator
     * @return
     */
    public static String join(String[] arr, String separator) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        for (String ele : arr) {
            sb.append(ele);
            sb.append(separator);
        }
        if (sb.length() > 0) {
            int index = sb.lastIndexOf(separator);
            result = sb.substring(0, index); // 包含索引0，不包含索引index
        }
        return result;
    }

    /**
     * 把一个Integer列表以特定分隔符拼接成字符串
     *
     * @param arr
     * @param separator
     * @return
     */
    public static String join(List<Integer> arr, String separator) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        for (Integer ele : arr) {
            sb.append(ele);
            sb.append(separator);
        }
        if (sb.length() > 0) {
            int index = sb.lastIndexOf(separator);
            result = sb.substring(0, index); // 包含索引0，不包含索引index
        }
        return result;
    }

    /***
     *
     * 给字符串列表添加上前缀
     *
     * @param list
     * @param prefix
     *
     * @author wengym
     *
     * @date 2022/3/29 14:57
     *
     * @return java.util.List<java.lang.String>
     *
     */
    public static List<String> addPrefixForStrList(List<String> list, String prefix) {
        List<String> newList = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return newList;
        }
        for(String str : list) {
            newList.add(prefix.concat(str));
        }
        return newList;
    }

    /***
     *
     * 给字符串数组添加上前缀
     *
     * @param arr
     * @param prefix
     *
     * @author wengym
     *
     * @date 2022/3/29 14:57
     *
     * @return java.util.List<java.lang.String>
     *
     */
    public static String[] addPrefixForStrArr(String[] arr, String prefix) {
        if (arr == null || arr.length== 0) {
            return new String[]{};
        }
        String[] newArr = new String[arr.length];
        for(int i = 0; i< arr.length; i++) {
            newArr[i] = prefix.concat(arr[i]);
        }
        return newArr;
    }

    /***
     *
     * 判断arr数组中是否包含item
     *
     * @param arr
     * @param item
     *
     * @author wengym
     *
     * @date 2022/3/11 14:08
     *
     * @return boolean
     *
     */
    public static <T> boolean contains(T[] arr, T item) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (T ele : arr) {
            if (item == ele || item.equals(ele)) {
                return true;
            }
        }
        return false;
    }

    public static void testRemoveAll() {
        // 测试删除集合中的一部分元素
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        PrintUtils.println(list1);
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        PrintUtils.println(list2);
        // 去掉list2集合中的元素
        list1.removeAll(list2);
        PrintUtils.println(list1);
    }

    public static void sort() {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("label", "苹果");
        jsonObject1.put("value", 1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("label", "雪梨");
        jsonObject2.put("value", 2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("label", "芒果");
        jsonObject3.put("value", 3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("label", "石榴");
        jsonObject4.put("value", 2);
        List<JSONObject> list = new ArrayList<>();
        list.add(jsonObject1);
        list.add(jsonObject2);
        list.add(jsonObject3);
        list.add(jsonObject4);
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                // 倒叙：3，2，2，1
                boolean flag = o1.getInteger("value") > o2.getInteger("value");
                if (o1.getInteger("value") > o2.getInteger("value")) {
                    return -1;
                } else if (o1.getInteger("value") < o2.getInteger("value")){
                    return 1;
                } else if (o1.getInteger("value") == o2.getInteger("value")) {
                    return 0; // 要有相等的判断，否则可能会抛异常：java.lang.IllegalArgumentException: Comparison method violates its general contract!
                }
                return 0;
            }
        });
        System.out.println(list.toString());
    }

    public static void main(String[] args) {
        String[] strArr = StringUtils.split("2021-11-01 16:55 ~ 2021-11-01 17:30", " ~ ");
        System.out.println("拼接字符串：" + ArrayUtils.join(strArr, ","));
        List<Integer> intArr = Arrays.asList(1,2,3);
        System.out.println("拼接字符串：" + ArrayUtils.join(intArr, ","));
        ArrayUtils.sort();
        ArrayUtils.testRemoveAll();
        Integer[] intAr2r = {1,2,3,4};
        PrintUtils.println(ArrayUtils.contains(intAr2r, 5));
        String[] strAr2r = {"a","b","c","d"};
        PrintUtils.println(ArrayUtils.contains(strAr2r, "f"));
        PrintUtils.println(ArrayUtils.addPrefixForStrArr(strAr2r, "t1."));
        PrintUtils.println(ArrayUtils.toList("12", "34", "56").toArray(new String[0]));
        PrintUtils.println(ArrayUtils.toList(null));
        PrintUtils.println(ArrayUtils.toList(1,2,3,4).toArray(new Integer[0]));
    }
}
