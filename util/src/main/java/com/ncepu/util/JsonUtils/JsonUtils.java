package com.ncepu.util.JsonUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncepu.util.DateUtils;

import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc JSON工具类
 * @date 2021/11/3 11:10
 */
public class JsonUtils {

    /**
     * 处理json字符串中value多余的双引号， 将多余的双引号替换为中文双引号
     *
     * @param jsonStr
     *
     * @author wengym
     *
     * @date 2023/2/15 13:45
     *
     * @return java.lang.String
     */
    public static String toJsonString(String jsonStr) {
        char[] tempArr = jsonStr.toCharArray();
        int tempLength = tempArr.length;
        for (int i = 0; i < tempLength; i++) {
            if (tempArr[i] == ':' && tempArr[i + 1] == '"') {
                for (int j = i + 2; j < tempLength; j++) {
                    if (tempArr[j] == '"') {
                        if (tempArr[j + 1] != ',' && tempArr[j + 1] != '}') {
                            // 将value中的英文双引号替换为中文双引号
                            tempArr[j] = '”';
                        } else if (tempArr[j + 1] == ',' || tempArr[j + 1] == '}') {
                            break;
                        }
                    }
                }
            }
        }
        return new String(tempArr);
    }

    /**
     * 分析字符串以获取JSON数组
     *
     * @param str
     *
     * @author wengym
     *
     * @date 2022/10/13 9:28
     *
     * @return com.alibaba.fastjson.JSONArray
     */
    public static JSONArray getJSONArray(String str) {
        JSONArray jsonArray = new JSONArray();
        if (str == null || str.trim().length() == 0) {
            return new JSONArray();
        }
        Object object = JSON.parse(str);
        if (object instanceof JSONArray) {
            jsonArray = (JSONArray)object;
            return jsonArray;
        }
        if (object instanceof JSONObject) {
            jsonArray.add(object);
            return jsonArray;
        }
        return jsonArray;
    }
    /***
     *
     * @param map 对转换为JSONObject的Map
     *
     * @desc 把Map转换为JSONObject
     *
     * @author wengym
     *
     * @date 2021/11/17 15:28
     *
     * @return JSONObject
     *
     */
    public static JSONObject mapToJSONObject(Map<String, Object> map) {
        String json = JSONObject.toJSONString(map);
        return JSONObject.parseObject(json);
    }

    /**
     * 对单层json进行key字母排序
     * @param json
     * @return
     */
    public static JSONObject getSortJson(JSONObject json){
        Iterator<String> iteratorKeys = json.keySet().iterator();
        SortedMap map = new TreeMap();
        while (iteratorKeys.hasNext()) {
            String key = iteratorKeys.next();
            String value = json.getString(key);
            map.put(key, value);
        }
        JSONObject json2 = JsonUtils.mapToJSONObject(map);
        return json2;
    }

    /**
     * 把json字符串转化为列表
     *
     * @param text
     *
     * @param clazz
     *
     * @author wengym
     *
     * @date 2022/12/7 18:13
     *
     * @return java.util.List<T>
     */
    public static <T> List<T> jsonToList(String text, Class<T> clazz) {
        JSONArray jsonArray = JSONArray.parseArray(text);
        List<T> list = new ArrayList<>();
        for (Object object : jsonArray) {
            list.add(JSONObject.parseObject(JSON.toJSONString(object), clazz));
        }
        return list;
    }

    /***
     *
     * @param
     *
     * @desc
     *
     * @author wengym
     *
     * @date 2021/11/24 10:03
     *
     * @return void
     *
     */
    public static void test() {
        ////////////////////////////////////////
        JSONObject obj = new JSONObject();
        obj.put("7","2021-11-03 10:20:12");
        obj.put("1","2021-11-03 10:21:12");
        obj.put("2","2021-11-03 10:15:12");
        obj.put("3","2021-11-03 10:23:12");
        Map<String, Object> map = obj.getInnerMap();
        List<Map.Entry<String, Object>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> {
            long diff = DateUtils.getDiffSecondOfDateTime(o1.getValue().toString(), o2.getValue().toString(), DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
            return diff > 0 ? 1 : diff < 0 ? -1 : 0;
        });
        JSONObject obj1 = new JSONObject(true);
        obj1.put("7","11");
        obj1.put("1","11");
        obj1.put("2","11");
        obj1.put("3","11");
        ////////////////////////////////////////
        Map<String,Object> map1 = new HashMap<>();
        map1.put("cc", "cc");
        map1.put("bb", "bb");
        map1.put("ee", "ee");
        map1.put("aa", "aa");
        map1.put("ba", "ba");
        map1.put("bd", "bd");
        JSONObject jsonObject = JsonUtils.mapToJSONObject(map1);
        System.out.println(JsonUtils.getSortJson(jsonObject));
        ////////////////////////////////////////
        List<String> list1 = Arrays.asList("123","qew","asd","345");
        String jsonStr = JSONObject.toJSONString(list1);
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        List<String> list2 = new ArrayList<>();
        for (Object obj2 : jsonArray) {
            list2.add((String)obj2);
        }
        System.out.println(jsonStr);
        System.out.println(list2);
        ////////////////////////////////////////
        List<String> list3 = Arrays.asList("123","QWE","ASD","345");
        String jsonStr2 = JSONObject.toJSONString(list3);
        System.out.println(jsonStr2);
        ////////////////////////////////////////
    }
    public static void main(String[] args) {
        JsonUtils.test();
    }
}
