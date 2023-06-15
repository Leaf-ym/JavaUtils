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
    public void testGetImage() {
        //String url = "http://nursevideo.oss-cn-beijing.aliyuncs.com/meet/ueditor_1686625518860_dtvu891g6se.jpg";
        String url = "http://nursevideo.oss-cn-beijing.aliyuncs.com/meet/ueditor_1686638066822_tum3je3pe6s.webp";
        BufferedImage image = (BufferedImage)ImageUtils.getImageFromUrl(url);
        PrintUtils.println("宽度：" + image.getWidth());
        PrintUtils.println("高度：" + image.getHeight());
        PrintUtils.println("高度：" + getWidthScale(image.getWidth()));
    }

    private static double getWidthScale(int width) {
        if (width <= 500) {
            return 1;
        }
        return 500.0 / width;
    }

    @Test
    public void testGetQrCodeOfLogo() {
        String logo = "D:\\测试\\logo\\1.png";
        BufferedImage image = QrCodeUtils.getQrCodeOfLogo("http://www.baidu.com", new HashMap<String, Object>() {{
            put("width", 300);
            put("height", 300);
            put("margin", 10);
            put("bgColor", new Color(255, 255, 255));
            put("logoPath", logo);
        }});
        String filePath = "D:\\测试\\" + System.currentTimeMillis() + ".png";
        ImageUtils.writeTo(image, "png", filePath);
    }

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
        /*BufferedImage image = QrCodeUtils.getQrCodeImage("http://www.baidu.com", new HashMap<String, Object>() {{
            put("width", 300);
            put("height", 300);
            put("margin", 10);
            put("bgColor", new Color(255, 255, 255));
        }});*/
        BufferedImage image = QrCodeUtils.getQrCodeImage("http://www.baidu.com", new HashMap<>());
        String filePath = "D:\\测试\\" + System.currentTimeMillis() + ".png";
        ImageUtils.writeTo(image, "png", filePath);
    }
}
