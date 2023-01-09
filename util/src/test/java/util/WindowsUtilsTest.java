package util;

import com.ncepu.util.WindowsUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc Windows测试工具类
 * @date 2022/9/19 9:25
 */
public class WindowsUtilsTest {
    @Test
    public void openProgramTest() {
        // 打开新文件
        //WinUtils.openProgram(WinUtils.ProgramName.NOTEPAD.getValue());
        // 打开已有文件
        //WinUtils.openProgram(WinUtils.ProgramName.NOTEPAD.getValue().concat(" C:\\Users\\Administrator\\Desktop\\北京中西医结合相关账号.txt"));
        WindowsUtils.openProgram(WindowsUtils.ProgramName.MSPAINT.getValue());
    }
}
