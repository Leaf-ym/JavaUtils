package util;

import com.ncepu.model.UserBean;
import com.ncepu.util.CollectionUtils;
import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.taobao.ProductSearchUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wengym
 * @version 1.0
 * @desc 集合工具测试类
 * @date 2022/5/11 11:11
 */
public class CollectionUtilsTest {
    @Test
    public void testSetSub() {
        Set<String> set1 = DatabaseUtils.asSet("内科护理专业委员会","心血管护理专业委会员","呼吸护理专业委员会","传染病护理专业委员会","糖尿病护理专业委员会","血液净化护理专业委员会","老年护理专业委员会","肿瘤护理专业委员会","安宁疗护专业委员会","外科护理专业委员会","骨科护理专业委员会","伤口造口失禁护理专业委员会","肠外肠内营养专业委员会","妇科护理专业委员会","产科护理专业委员会","辅助生殖护理专业委员会","儿科护理专业委员会","重症护理专业委员会","急诊护理专业委员会","灾害护理专业委员会","耳鼻喉护理专业委员会","眼科护理专业委员会","口腔科护理专业委员会","口腔颌面外科护理专业委员会","整形护理专业委员会","手术室护理专业委员会","手术装备专业委员会","日间手术护理专业委员会","麻醉护理专业委员会","门诊护理专业委员会","精神卫生专业委员会","中医中西医结合护理专业委员会","康复护理专业委员会","健康管理专业委员会","社区护理专业委员会","人文护理专业委员会","静脉输液治疗专业委员","疼痛专业委员会","放射介入护理专业委员会","护理管理专业委员会","医院感染管理专业委员会","消毒供应中心专业委员会","高等护理教育专业委员会","护理职业教育专业委员会","护理理论研究专业委员会","护理伦理专业委员会","循证护理专业委员会","男护士工作委员会","非公立医疗机构工作委员会");
        Set<String> set2 = DatabaseUtils.asSet("重症护理专业委员会","安宁疗护专业委员会","骨科护理专业委员会","老年护理专业委员会","急诊护理专业委员会","手术室护理专业委员会","康复护理专业委员会","消毒供应中心专业委员会","血液净化护理专业委员会","医院感染管理专业委员会","精神卫生专业委员会","眼科护理专业委员会","肿瘤护理专业委员会","妇科护理专业委员会","内科护理专业委员会","儿科护理专业委员会","外科护理专业委员会","口腔科护理专业委员会","传染病护理专业委员会","护理管理专业委员会","产科护理专业委员会","灾害护理专业委员会","呼吸护理专业委员会","糖尿病护理专业委员会","手术室护理专业委员会","男护士工作委员会","门诊护理专业委员会","社区护理专业委员会","护理理论研究专业委员会","护理伦理专业委员会","人文护理专业委员会","循证护理专业委员会","肠外肠内营养专业委员会","麻醉护理专业委员会","疼痛专业委员会","整形护理专业委员会","日间手术护理专业委员会","辅助生殖护理专业委员会","放射介入护理专业委员会","健康管理专业委员会","高等护理教育专业委员会","护理职业教育专业委员会","手术装备专业委员会","口腔颌面外科护理专业委员会","非公立医疗机构工作委员会");
        set1.removeAll(set2);
        PrintUtils.println(set1);
    }

    @Test
    public void testListToMap() {
        List<UserBean> list = new ArrayList<>();
        list.add(UserBean.builder().userName("张三").password("123456").build());
        list.add(UserBean.builder().userName("李四").password("654321").build());
        List<String> strList = list.stream().map(item -> item.getUserName()).collect(Collectors.toList());
        PrintUtils.println(strList);
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
