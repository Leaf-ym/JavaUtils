package util;

import com.ncepu.util.PcUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc PC电脑工具测试类
 * @date 2022/10/19 17:58
 */
public class PcUtilsTest {
    @Test
    public void testGetComputerUniqueIdentification() {
        String r1 = PcUtils.getComputerUniqueIdentificationString();
        PrintUtils.println(r1);
    }
}
