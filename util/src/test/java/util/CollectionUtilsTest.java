package util;

import com.ncepu.model.UserBean;
import com.ncepu.util.CollectionUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.util.ArrayList;
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
    public void testListToMap() {
        List<UserBean> list = new ArrayList<>();
        list.add(UserBean.builder().userName("张三").password("123456").build());
        list.add(UserBean.builder().userName("李四").password("654321").build());
        list.stream().map(item -> item.getUserName());
    }

    @Test
    public void testSubList() {
        List<String> list = CollectionUtils.getList("a", "b", "aa", "bb", "cc", "s", "d", "ee");
        int batchCount = list.size() >= 4 ? 4 : list.size();
        int fromIndex = 0;
        int toIndex = batchCount;
        while(true) {
            PrintUtils.println(list.subList(fromIndex, toIndex));
            fromIndex = toIndex;
            toIndex = toIndex + batchCount;
            if (toIndex > list.size()) {
                toIndex = list.size();
            }
            if (fromIndex > list.size() -1) {
                break;
            }
        }

    }

    @Test
    public void testListFilter() {
        List<String> list = CollectionUtils.getList("a", "b", "aa", "bb", "cc");
        List<String> filterList = CollectionUtils.listFilter(list, item -> item.startsWith("b"));
        PrintUtils.println(filterList);
    }

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
