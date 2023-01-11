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
        String code = GraphicVerificationCodeUtils.getStrCode(5);
        String r1 = GraphicVerificationCodeUtils.getBase64ImageCode(code);
        PrintUtils.println(r1);
    }

    @Test
    public void testGetStrCode() {
        for (int i = 0; i < 10; i++) {
            String r1 = GraphicVerificationCodeUtils.getStrCode(5);
            PrintUtils.println(r1);
        }
    }
}
