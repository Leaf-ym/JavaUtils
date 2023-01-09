package util;

import com.ncepu.enums.RegexEnum;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.RegexUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 正则表达式工具测试类
 * @date 2022/7/15 9:13
 */
public class RegexUtilsTest {
    @Test
    public void isMatchTest() {
        Boolean result1 = RegexUtils.isMatch("   ", RegexEnum.MULTIPLE_SPACE.getValue());
        PrintUtils.println(result1);
        Boolean result2 = RegexUtils.isMatch("123@126.COM", RegexEnum.EMAIL.getValue());
        PrintUtils.println(result2);
    }
}
