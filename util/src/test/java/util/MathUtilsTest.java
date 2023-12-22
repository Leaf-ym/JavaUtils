package util;

import com.ncepu.util.MathUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 数学工具测试类
 * @date 2022/6/20 11:52
 */
public class MathUtilsTest {
    @Test
    public void testGetRoundInt() {
        double current = 1.4;
        double last = 0.5;
        Integer r1 = MathUtils.getRoundInt(current);
        Integer r2 = MathUtils.getRoundInt(last);
        Integer r3 = MathUtils.getFloorInt(current);
        Integer r4 = MathUtils.getFloorInt(last);
        Integer r5 = MathUtils.getCeilInt(current);
        Integer r6 = MathUtils.getCeilInt(last);
        PrintUtils.println(r1);
        PrintUtils.println(r2);
        PrintUtils.println(r3);
        PrintUtils.println(r4);
        PrintUtils.println(r5);
        PrintUtils.println(r6);
    }

    @Test
    public void testGetGrowthRatioOfPercent() {
        // 获取百分比形式的增长比：将这个月减去上个月然后除以上个月所得就是增长比，如(100-40)/40=150%
        double current = 1;
        double last = 0.0;
        String percent = MathUtils.getGrowthRatioOfPercent(current, last);
        PrintUtils.println(percent);
    }

    @Test
    public void testIntegerCompare() {
        Integer[] arr = {-128, 127, -129, 128};
        Integer[] arr1 = {-128, 127, -129, 128};
        PrintUtils.println(arr1[0] == arr[0]);
        PrintUtils.println(arr1[1] == arr[1]);
        PrintUtils.println(arr1[2] == arr[2]);
        PrintUtils.println(arr1[3] == arr[3]);
        PrintUtils.println(arr1[2].equals(arr[2]));
        PrintUtils.println(arr1[3].equals(arr[3]));
        Integer v1 = -128;
        Integer v2 = 127;
        Integer v3 = 366;
        PrintUtils.println(v1 > v2);
        PrintUtils.println(v2.equals(v3));
        PrintUtils.println(v1 < v2);
    }

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
