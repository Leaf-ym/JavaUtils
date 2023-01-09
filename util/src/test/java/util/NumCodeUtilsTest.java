package util;

import com.ncepu.util.NumCodeUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 数字测试工具类
 * @date 2022/10/24 15:34
 */
public class NumCodeUtilsTest {
    @Test
    public void testToHexString() {
        String r1 = NumCodeUtils.toHexString(1000);
        PrintUtils.println(r1);
    }
    @Test
    public void testToBinaryString() {
        String r1 = NumCodeUtils.toBinaryString(10);
        PrintUtils.println(r1);
    }
}
