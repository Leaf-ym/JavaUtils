package util;

import com.ncepu.util.PrintUtils;
import com.ncepu.util.ValidateRegUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 校验工具类测试
 * @date 2022/4/27 16:34
 */
public class ValidateRegUtilsTest {
    @Test
    public void testValid() {}
    public static void main(String[] args) {
        PrintUtils.println("邮政编码测试：" + ValidateRegUtils.checkPostCode("321001"));
    }
}
