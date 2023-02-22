package util;

import com.ncepu.util.ImageUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.QrCodeUtils;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * @author wengym
 * @version 1.0
 * @desc 二维码工具测试类
 * @date 2023/1/12 17:16
 */
public class QrCodeUtilsTest {
    @Test
    public void testTetQrCodeContent() {
        String filePath = "D:\\测试\\" + "1673575102726.png";
        String r1 = QrCodeUtils.getQrCodeContent((BufferedImage)ImageUtils.getImage(filePath));
        PrintUtils.println(r1);
    }

    @Test
    public void testGetQrCodeImage2() {
        BufferedImage image = QrCodeUtils.getQrCodeImage("翁一茗", 500, 500);
        String filePath = "D:\\测试\\" + System.currentTimeMillis() + ".png";
        ImageUtils.writeTo(image, "png", filePath);
    }
    @Test
    public void testGetQrCodeImage() {
        BufferedImage image = QrCodeUtils.getQrCodeImage("http://www.baidu.com", new HashMap<String, Object>() {{
            put("width", 500);
            put("height", 500);
            put("margin", 50);
            put("bgColor", new Color(0, 0, 255));
        }});
        String filePath = "D:\\测试\\" + System.currentTimeMillis() + ".png";
        ImageUtils.writeTo(image, "png", filePath);
    }
}