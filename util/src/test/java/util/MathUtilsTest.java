package util;

import com.ncepu.util.MapUtils;
import com.ncepu.util.MathUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 数学工具测试类
 * @date 2022/6/20 11:52
 */
public class MathUtilsTest {
    @Test
    public void testGetRandom() {
        for (int i = 0; i <= 20; i++) {
            double r1 = MathUtils.getRandom();
            PrintUtils.println((int) ((r1 * 9 + 1) * 1000));
        }
    }

    @Test
    public void testToInteger() {
        Integer r1 = MathUtils.toInteger("1970.");
        PrintUtils.println(r1);
    }

    @Test
    public void testToFixed() {
        String r1 = MathUtils.toFixed(".1", 3);
        String r2 = MathUtils.toFixed("0.01", 3);
        // 0.10
        PrintUtils.println(r1);
        // 0.01
        PrintUtils.println(r2);
    }
}
