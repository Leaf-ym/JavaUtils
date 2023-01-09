package util;

import com.ncepu.util.IpUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.net.SocketException;
import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc IP工具测试类
 * @date 2022/10/19 16:00
 */
public class IpUtilsTest {
    @Test
    public void testGetLocalIp() throws SocketException {
        String r1 = IpUtils.getLocalIp();
        List<String> r2 = IpUtils.getIpAddress();
        List<String> r3 = IpUtils.getIpAddress1();
        String r4 = IpUtils.getLocalIp1();
        String r5 = IpUtils.getOutIPV4();
        String r6 = IpUtils.getInterIP2();
        String r7 = IpUtils.getCommIpAddr();
        String r8 = IpUtils.getOutIPV41();
        PrintUtils.println(r1);
        PrintUtils.println(r2);
        PrintUtils.println(r3);
        PrintUtils.println(r4);
        PrintUtils.println(r5);
        PrintUtils.println(r6);
        PrintUtils.println(r7);
        PrintUtils.println(r8);
    }
}
