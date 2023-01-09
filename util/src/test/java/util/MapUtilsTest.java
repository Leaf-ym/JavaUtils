package util;

import com.ncepu.util.MapUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 哈希表工具测试类
 * @date 2022/9/5 15:05
 */
public class MapUtilsTest {

    @Test
    public void testGetGetParams() {
        Map<String, Object> map = MapUtils.getHashMap();
        map.put("A1", 1);
        map.put("Ds", 1);
        map.put("Eew", 1);
        map.put("B123", 1);
        map.put("Cae", 1);
        map.put("F", 1);
        map.put("1F", 1);
        map.put("3F", 1);
        map.put("2F", 1);
        map.put("bF", 1);
        map.put("aF", 1);
        map.put("acb", 1);
        map.put("aca", 1);
        PrintUtils.println("排序前。。。");
        for (String key : map.keySet()) {
            PrintUtils.println(key + "=" + map.get(key));
        }
        PrintUtils.println("排序后。。。");
        Map<String, Object> treeMap = MapUtils.toSortByKey(map);
        for (String key : treeMap.keySet()) {
            PrintUtils.println(key + "=" + treeMap.get(key));
        }
        String result = MapUtils.getGetParams(treeMap);
        PrintUtils.println(result);
    }

    @Test
    public void testGetHashMap() {
        Map<String, Object> map = MapUtils.getHashMap();
        PrintUtils.println(map);
        map.put("A", 1);
        map.put("D", 1);
        map.put("E", 1);
        map.put("B", 1);
        map.put("C", 1);
        map.put("F", 1);
        PrintUtils.println(map);
    }

    @Test
    public void testToSortByKey() {
        Map<String, Object> map = MapUtils.getHashMap();
        map.put("A1", 1);
        map.put("Ds", 1);
        map.put("Eew", 1);
        map.put("B123", 1);
        map.put("Cae", 1);
        map.put("F", 1);
        map.put("1F", 1);
        map.put("3F", 1);
        map.put("2F", 1);
        map.put("bF", 1);
        map.put("aF", 1);
        map.put("acb", 1);
        map.put("aca", 1);
        PrintUtils.println("排序前。。。");
        for (String key : map.keySet()) {
            PrintUtils.println(key + "=" + map.get(key));
        }
        PrintUtils.println("排序后。。。");
        Map<String, Object> treeMap = MapUtils.toSortByKey(map);
        treeMap.put("aa", 2);
        for (String key : treeMap.keySet()) {
            PrintUtils.println(key + "=" + treeMap.get(key));
        }
    }
}
