package util;

import com.ncepu.util.ImageUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.SystemUtils.SystemUtils;
import org.junit.Test;

import java.awt.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 操作系统工具测试类
 * @date 2023/1/9 10:36
 */
public class SystemUtilsTest {
    @Test
    public void testSpeed() {
        String r4 = SystemUtils.getDownloadSpeed();
        PrintUtils.println(r4);
        String r5 = SystemUtils.getUploadSpeed();
        PrintUtils.println(r5);
    }

    @Test
    public void testGetMemoryInfo() {
        String r1 = SystemUtils.getMemoryInfo();
        PrintUtils.println(r1);
        String r2 = SystemUtils.getJvmInfo();
        PrintUtils.println(r2);
        String r3 = SystemUtils.getOSInfo();
        PrintUtils.println(r3);
    }

    @Test
    public void testSetClipboardStr() {
        String text = "剪贴板内容";
        SystemUtils.setClipboardStr(text);
    }

    @Test
    public void getClipboardStr() {
        String text = SystemUtils.getClipboardStr();
        PrintUtils.println(text);
    }

    @Test
    public void getClipboardImage() {
        Image image = SystemUtils.getClipboardImage();
        //Image image = FileUtils.getImage("D:\\测试\\1673234129652.png");
        //Image image = FileUtils.getImageFromUrl("https://nursevideo.oss-cn-beijing.aliyuncs.com/bjaim/2022/812749e1a307f38fd0a88b54bea55ff21667910813812.png");
        String suffix = "png";
        String filePath = "D:\\测试\\" + System.currentTimeMillis() + "." + suffix;
        ImageUtils.writeTo(image, suffix, filePath);
    }
}
