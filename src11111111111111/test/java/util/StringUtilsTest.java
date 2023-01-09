package util;

import com.ncepu.util.ArrayUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 字符串工具测试类
 * @date 2022/4/29 9:29
 */
public class StringUtilsTest {

    @Test
    public void testFormatNum() {
        String result1 = StringUtils.formatNum(5, 200);
        assertEquals("00200", result1);
        String result2 = StringUtils.formatNum(5, 20000);
        assertEquals("20000", result2);
        String result3 = StringUtils.formatNum(5, 200000);
        assertEquals("200000", result3);
        for (int i=9999; i < 190000; i++) {
            PrintUtils.println(String.format("%05d",i));
        }
    }

    @Test
    public void testAddPrefix() {
        String result1 = StringUtils.addPrefix("123456", "MMM");
        assertEquals("MMM123456", result1);
        String result2 = StringUtils.addPrefix("MMM", "123456");
        assertEquals("123456MMM", result2);
        String result3 = StringUtils.addPrefix("023456", "M05");
        assertEquals("M05023456", result3);
        String result4 = StringUtils.addPrefix("翁一茗", "M");
        assertEquals("M翁一茗", result4);
    }

    @Test
    public void testAddSuffix() {
        String result1 = StringUtils.addSuffix("123456", "MMM");
        assertEquals("123456MMM", result1);
        String result2 = StringUtils.addSuffix("MMM", "123456");
        assertEquals("MMM123456", result2);
        String result3 = StringUtils.addSuffix("023456", "M05");
        assertEquals("023456M05", result3);
        String result4 = StringUtils.addSuffix("翁一茗", "M");
        assertEquals("翁一茗M", result4);
    }

    public static void test() {
        String[] strArr = StringUtils.split("2021-11-01 16:55 ~ 2021-11-01 17:30", " ~ ");
        System.out.println("连接字符串：" + ArrayUtils.join(strArr, ","));
        String arr1[] = StringUtils.split("---", "-");
        System.out.println("分割字符串：" + arr1.toString());
        System.out.println("格式化数字：" + StringUtils.formatNum(2, 1111));
        System.out.println("格式化数字：" + StringUtils.formatNum(9, 1111122222)); // 超过10位就会提示整数过大
        System.out.println("连接字符串：" + StringUtils.concat(9, 1111122222, "123", "翁一茗", 110));
        String[] arr = {"1", "2", "3", "4"};
        System.out.println("连接字符串：" + StringUtils.concat(arr));
        List<String> list = Arrays.asList("1", "2", "3", "q", "d");
        System.out.println("连接字符串：" + StringUtils.join(list, ","));
        System.out.println("null=null：" + StringUtils.equals(null, null) + "; null=str：" + StringUtils.equals(null, "str") + "; str=str：" + StringUtils.equals("str", "str"));
        System.out.println("ASCII值：" + StringUtils.getASCIIOfStr("a"));
        String pct = "浙江省-杭州市-余杭区-";
        System.out.println("去掉字符串的最后一个字符：" + pct.substring(0, pct.length() - 1));
        System.out.println("17325302081是否是数字：" + StringUtils.isNumber("17325302081"));
        System.out.println("17325302081是否是整数：" + StringUtils.isInteger("173253020811"));
        System.out.println("是否是纯数字：" + StringUtils.isPureNumber("173253020811"));
        System.out.println("港澳台通行证：" + StringUtils.validateIdCard("港、澳、台通行证", "C13273201"));
        System.out.println("军官证：" + StringUtils.validateIdCard("军官证", "军字第2001988号"));
        System.out.println("会员号：" + StringUtils.allCnaNumberCheck("M05110000000000001H"));
        System.out.println("会员号：" + StringUtils.allCnaNumberCheck("M0511000001S"));
        System.out.println("会员号：" + StringUtils.allCnaNumberCheck("M0511000001M"));
        System.out.println("会员号：" + StringUtils.allCnaNumberCheck("M0511000001A"));
        System.out.println("会员号：" + StringUtils.allCnaNumberCheck("M0511000001F"));
        PrintUtils.println(StringUtils.removeSuffix("进程已结束，推出代码0，", "，"));
        PrintUtils.println(StringUtils.removePrefix("12345678，", "1"));
        String PUB = "30819d300d06092a864886f70d010101050003818b00308187028181008e6544e605f0ff08b6b1510bf0144a03275e97ad26ad6d1e8299cf48a4099a3746aac6ffaf64d4b396dae595ff344dc7be8b02a1266e914d43a76442849f56294715f2ac8df2fb282695856ea09b556f034feb34884fe9be4d90e56a4562bf72f2009f05ca42f71871c9563d929a17ac7e841ed1d93772414d157fdd28de2df5020111";
        PrintUtils.println("截取字符串：" + PUB.substring(PUB.length() - 30));
        PrintUtils.println("去掉多个后缀" + StringUtils.removeSuffixArr("M05190000F", "M", "S", "F"));
        PrintUtils.println("添加前缀" + StringUtils.addPrefix("123456", "MMM"));
    }

    public static void main(String[] args) {
        StringUtilsTest.test();
    }
}
