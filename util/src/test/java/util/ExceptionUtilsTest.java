package util;

import com.ncepu.util.ExceptionUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.io.PrintStream;

/**
 * @author wengym
 * @version 1.0
 * @desc 异常处理工具测试类
 * @date 2023/3/16 18:22
 */
public class ExceptionUtilsTest {

    @Test
    public void testGetExceptionInfo() {
        NullPointerException e = new NullPointerException("参数不能为空");
        String str = ExceptionUtils.getExceptionInfo(e);
        StackTraceElement ste = e.getStackTrace()[1];
        PrintUtils.println(ste.getClassName());
        PrintUtils.println(str);
    }
}
