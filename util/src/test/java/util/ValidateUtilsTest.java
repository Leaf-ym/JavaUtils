package util;

import com.ncepu.model.UserBean;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.ValidateUtils.ValidateUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc model字段校验工具测试类
 * @date 2023/4/19 11:07
 */
public class ValidateUtilsTest {

    @Test
    public void testValidate() {
        UserBean userBean = UserBean.builder().userName("茗茗茗茗").password("").build();
        Object result = ValidateUtils.validate(userBean);
        PrintUtils.println(result);
    }
}
