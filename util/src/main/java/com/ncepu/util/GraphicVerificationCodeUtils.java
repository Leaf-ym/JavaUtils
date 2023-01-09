package com.ncepu.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wengym
 * @version 1.0
 * @desc 图形验证码工具类
 * @date 2022/10/17 9:57
 */
public class GraphicVerificationCodeUtils {
    /**
     * 获取base64格式的图形编码字符串
     *
     * @return java.lang.String
     * @author wengym
     * @date 2022/10/17 10:41
     */
    public static String getBase64ImageCode() {
        BufferedImage image = getBufferedImage(new HashMap<>());
        String base64 = getBase64Data(image);
        return base64;
    }

    /**
     * 获取缓存图像
     *
     * @param paramsMap
     * @return java.awt.image.BufferedImage
     * @author wengym
     * @date 2022/10/17 10:41
     */
    public static BufferedImage getBufferedImage(Map<String, Object> paramsMap) {
        System.out.println("绘图参数为：" + paramsMap);
        // 创建一个图像，宽60，高30
        int width = 60, height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        // 设置图像的背景颜色
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 绘制指定数目的干扰线
        g.setColor(getRandomColor(160, 200));
        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(20);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 绘制指定的验证码
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        String strCode = getStrCode(4);
        for (int i = 0; i < strCode.length(); i++) {
            String strNumber = strCode.substring(i, i + 1);
            // 设置数字的颜色
            g.drawString(strNumber, 13 * i + 6, 20);
        }
        g.dispose();
        return image;
    }

    /**
     * 把缓存图像编码成base64格式的字符串
     *
     * @param image
     * @return java.lang.String
     * @author wengym
     * @date 2022/10/17 10:39
     */
    public static String getBase64Data(BufferedImage image) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            return null;
        }
        String base64 = "data:image/png;base64," + Base64.encode(out.toByteArray());
        return base64;
    }

    /**
     * 获取指定位数的随机数字字符串
     *
     * @param num
     * @return java.lang.String
     * @author wengym
     * @date 2022/10/17 10:40
     */
    public static String getStrCode(Integer num) {
        if (num == null) {
            throw new NullPointerException("num不能为null");
        }
        if (num <= 0) {
            throw new IllegalArgumentException("num需要大于0");
        }
        // 1.生成len位整数的最大值999...
        StringBuilder max = new StringBuilder();
        for (int i = 0; i < num; i++) {
            max.append("9");
        }
        // 2.生成[0,max]区间中的伪随机整数
        Random rand = new Random();
        // 返回[0,max]之间的整数
        String str = String.valueOf(rand.nextInt(Integer.valueOf(max.toString())));
        StringBuilder result = new StringBuilder();
        // 3.如果伪随机整数的长度小于len，前置补0
        for (int i = 0; i < num - str.length(); i++) {
            result.append('0');
        }
        // 4.返回指定长度的伪随机整数字符串
        return result.toString() + str;
    }


    /**
     * 随机获取颜色的方法
     *
     * @return
     */
    public static Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        Color randomColor = null;
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        randomColor = new Color(r, g, b);
        return randomColor;
    }
}
