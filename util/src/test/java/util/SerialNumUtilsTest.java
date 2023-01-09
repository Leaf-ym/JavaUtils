package util;

import com.ncepu.util.PrintUtils;
import com.ncepu.util.SerialNumUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 流水号工具测试类
 * @date 2022/9/26 17:53
 */
public class SerialNumUtilsTest {

    @Test
    public void testGetOrderIdByTime() {
        String r1 = SerialNumUtils.getOrderIdByTime("MEMBER");
        PrintUtils.println(r1);
        String r2 = SerialNumUtils.getOrderIdByTime("MEET");
        PrintUtils.println(r2);
    }

    @Test
    public void testGetChar() {
        String r1 = SerialNumUtils.getChar("helloworld!", 4);
        PrintUtils.println(r1);
    }
}
