package util;

import com.ncepu.model.NewUserBean;
import com.ncepu.util.BeanUtils;
import com.ncepu.util.CollectionUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc bean工具测试类
 * @date 2022/5/11 14:40
 */
public class BeanUtilsTest {
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
}
