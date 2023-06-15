package util;

import com.ncepu.util.PrintUtils;
import com.ncepu.util.RandomUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 随机数工具
 * @date 2023/4/26 13:36
 */
public class RandomUtilsTest {

    @Test
    public void testGetRandomOrderIdByTime() {
        String r1 = RandomUtils.getRandomOrderIdByTime("11111111111122123");
        PrintUtils.println(r1);
        PrintUtils.println(r1.length());
    }

}
