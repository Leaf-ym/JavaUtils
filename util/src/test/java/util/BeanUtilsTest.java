package util;

import com.ncepu.model.NewUserBean;
import com.ncepu.model.UserDTO;
import com.ncepu.model.UserRegisterModel;
import com.ncepu.util.BeanUtils;
import com.ncepu.util.CollectionUtils;
import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc bean工具测试类
 * @date 2022/5/11 14:40
 */
public class BeanUtilsTest {
    @Test
    public void testRemoveUnnecessaryFields() {
        NewUserBean model = NewUserBean.builder()
                .id(11)
                .mlId(22)
                .password("mia")
                .fillDate("2011-11-11")
                .build();
        Map<String, Object> map = BeanUtils.removeUnnecessaryFields(model, DatabaseUtils.asSet("id", "mlId"));
        PrintUtils.println(map);
        PrintUtils.println(model);
    }

    @Test
    public void testBeanToMap() {
        NewUserBean model = NewUserBean.builder()
                .id(11)
                .mlId(22)
                .password("mia")
                .fillDate("2011-11-11")
                .build();
        Map<String, Object> map = BeanUtils.beanToMap(model);
        PrintUtils.println(map);
    }

    @Test
    public void testGetAllFieldValue() {
        NewUserBean model = NewUserBean.builder()
                .id(11)
                .mlId(22)
                .password("mia")
                .fillDate("2011-11-11")
                .build();
        model.setPageIndex(1000);
        model.setWengym("翁一茗");
        Object value1 = BeanUtils.getAllFieldValue("pageIndex", model);
        Object value2 = BeanUtils.getAllFieldValue("wengym", model);
        Object value3 = BeanUtils.getAllFieldValue("wengym1", model);
        Class cls = Object.class.getSuperclass();
        PrintUtils.println(value1);
        PrintUtils.println(value2);
        PrintUtils.println(value3);
    }

    @Test
    public void testGetAllFields() {
        Class cls = NewUserBean.class;
        Field[] fields = BeanUtils.getAllFields(cls);
        PrintUtils.println(fields);
    }

    @Test
    public void testModelToDTO() {
        UserRegisterModel model = UserRegisterModel.builder()
                .id("1")
                .userId("1234567")
                .password("mia")
                .fillDate("2011-11-11")
                .updateDate("2022-12-22")
                .build();
        UserDTO userDTO = BeanUtils.modelToDTO(model, UserDTO.class);
        PrintUtils.println(userDTO);
    }

    @Test
    public void testIsExistField() {
        Boolean result1 = BeanUtils.isExistField("id", NewUserBean.class);
        Boolean result2 = BeanUtils.isExistField("iidd", NewUserBean.class);
        PrintUtils.println(result1);
        PrintUtils.println(result2);
    }

    @Test
    public void testGetObjectByClassName() {
        String className = "com.ncepu.model.NewUserBean";
        Object o = BeanUtils.getObjectByClassName(className);
        PrintUtils.println(o);
    }

    @Test
    public void testTrimBean() {
        NewUserBean newUserBean = new NewUserBean();
        newUserBean.setUserName("  翁一茗     ");
        newUserBean.setPassword("   123456     ");
        PrintUtils.println(newUserBean);
        //BeanUtils.trimBean(newUserBean);
        //PrintUtils.println(newUserBean);
        List<NewUserBean> list = CollectionUtils.getList(newUserBean);
        BeanUtils.trimCollection(list);
        PrintUtils.println(list.get(0));
    }

    @Test
    public void testMapToBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "翁一茗");
        map.put("password", "123456");
        map.put("updateDate", new Date());
        map.put("fillDate", new Date());
        map.put("mlId", 123);
        NewUserBean model = BeanUtils.mapToBean(map, NewUserBean.class);
        PrintUtils.println(model);
    }
}
