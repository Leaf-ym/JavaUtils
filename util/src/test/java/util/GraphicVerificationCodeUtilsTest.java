package util;

import com.ncepu.util.GraphicVerificationCodeUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 图形验证码工具测试类
 * @date 2022/10/17 10:23
 */
public class GraphicVerificationCodeUtilsTest {
    @Test
    public void testGetImageCode() {
        String r1 = GraphicVerificationCodeUtils.getBase64ImageCode();
        PrintUtils.println(r1);
    }
}
