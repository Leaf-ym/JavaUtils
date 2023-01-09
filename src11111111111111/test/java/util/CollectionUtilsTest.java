package util;

import com.ncepu.util.CollectionUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 集合工具测试类
 * @date 2022/5/11 11:11
 */
public class CollectionUtilsTest {

    @Test
    public void testGetIntersectList() {
        List<String> list1 = CollectionUtils.getList("a");
        List<String> list2 = CollectionUtils.getList("a", "b");
        List<String> list = CollectionUtils.getIntersectList(list1, list2);
        PrintUtils.println(list);
    }

    @Test
    public void testGetSet() {
        Set<String> set = CollectionUtils.getSet("a", "a", "b", "c", "d");
        PrintUtils.println(set);
    }
}
