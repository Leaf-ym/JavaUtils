package com.ncepu.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.ibatis.jdbc.Null;

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
     * @param code
     * @return java.lang.String
     * @author wengym
     * @date 2022/10/17 10:41
     */
    public static String getBase64ImageCode(String code) {
        BufferedImage image = getBufferedImage(new HashMap<String, Object>() {{
            put("width", 200);
            put("height", 50);
            put("code", code);
        }});
        String base64 = getBase64Data(image);
        return base64;
    }

    /**
     * 获取缓存图像
     *
     * @param paramsMap - width
     *                  - height
     *                  - code
     * @return java.awt.image.BufferedImage
     * @author wengym
     * @date 2022/10/17 10:41
     */
    public static BufferedImage getBufferedImage(Map<String, Object> paramsMap) {
        System.out.println("绘图参数为：" + paramsMap);
        if (paramsMap.get("width") == null || paramsMap.get("height") == null || paramsMap.get("code") == null) {
            throw new NullPointerException("参数width、height、code都不能为空");
        }
        // 颜色数组
        int[][] colors = {{0, 48, 242}, {242, 0, 48}, {203, 0, 255}, {113, 82, 59}, {113, 165, 52}, {128, 189, 162}, {156, 71, 43}, {122, 219, 121}, {255, 202, 0}, {0, 255, 203}};
        // 创建一个图像，宽60，高30
        int width = (Integer) paramsMap.get("width"), height = (Integer) paramsMap.get("height");
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        Random random = new Random();
        // 设置图像的背景颜色，白色
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);
        // 消除线条和文字的锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        // 绘制干扰线，BasicStroke控制线宽
        g.setStroke(new BasicStroke(1.5f));
        for (int i = 0; i < 15; i++) {
            int[] lineColor = colors[random.nextInt(10)];
            g.setColor(new Color(lineColor[0], lineColor[1], lineColor[2]));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(width);
            int yl = random.nextInt(height);
            g.drawLine(x, y, xl, yl);
        }
        // 绘制干扰圈，BasicStroke控制线宽
        g.setStroke(new BasicStroke(1.2f));
        for (int i = 0; i < 15; i++) {
            int[] lineColor = colors[random.nextInt(10)];
            g.setColor(new Color(lineColor[0], lineColor[1], lineColor[2]));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int w = random.nextInt(15);
            int h = random.nextInt(15);
            g.drawArc(x, y, w, h, 0, 360);
        }
        // 绘制指定的验证码
        String strCode = (String) paramsMap.get("code");
        g.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 42));
        for (int i = 0; i < strCode.length(); i++) {
            String str = strCode.substring(i, i + 1);
            // 绘制字符
            int[] lineColor = colors[random.nextInt(10)];
            g.setColor(new Color(lineColor[0], lineColor[1], lineColor[2]));
            g.drawString(str, (width - 10 * 2) / strCode.length() * i + 5 + random.nextInt(5), (height / 2 + 10) + random.nextInt(5));
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
     * 获取指定位数的随机字符串(0-9a-zA-Z)
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
        // 10个数字
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        // 小写字母的ASCII编码减去32为大写字母的ASCII编码，a为97
        String[] lowercase = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        // 字符类型：0数字，1小写字母，2大写字母
        Integer type;
        // 单个字符
        String str;
        // 随机数生成器
        Random rand = new Random();
        // 随机字符串结果
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            // 生成随机整数0，1，2，不包括边界3
            type = rand.nextInt(3);
            switch (type) {
                // 数字
                case 0:
                    str = nums[rand.nextInt(10)];
                    break;
                // 小写字母
                case 1:
                    str = lowercase[rand.nextInt(26)];
                    break;
                // 大写字母
                case 2:
                    str = String.valueOf((char) (lowercase[rand.nextInt(26)].charAt(0) - (char) 32));
                    break;
                default:
                    str = String.valueOf(rand.nextInt(10));
                    break;
            }
            result.append(str);
        }
        return result.toString();
    }
}
