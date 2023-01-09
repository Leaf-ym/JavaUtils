package util;

import com.ncepu.util.PrintUtils;
import com.ncepu.util.ValidateUtils;

/**
 * @author wengym
 * @version 1.0
 * @desc 校验工具类测试
 * @date 2022/4/27 16:34
 */
public class ValidateUtilsTest {
    public static void main(String[] args) {
        PrintUtils.println("邮政编码测试：" + ValidateUtils.checkPostCode("321001"));
    }
}
