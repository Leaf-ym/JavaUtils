package com.ncepu.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 二维码工具类
 * @date 2022/9/19 11:37
 */
@Slf4j
public class QrCodeUtils {

    /**
     * 生成二维码
     *
     * @param content
     *
     * @author wengym
     *
     * @date 2022/9/19 11:50
     *
     * @return void
     */
    public static void getQrCode(String content, OutputStream stream) {
        int width = 300;
        int height = 300;
        String format = "png";
        HashMap hits = new HashMap();
        //编码
        hits.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //纠错等级，纠错等级越高存储信息越少
        hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //边距
        hits.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hits);
            //如果做网页版输出可以用输出到流
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
            //将二维码写入图片
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //将图片转为字节数组
            ImageIO.write(bufferedImage, "png", stream);
        } catch (Exception e) {
            log.error("二维码生成异常！");
            e.printStackTrace();
        }
    }

    /**
     * 调整图片大小
     *
     * @param x   宽度
     * @param y   高度
     * @param bfi 图片流
     * @return
     */
    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }

    /**
     * 获取存在指定背景的二维码
     *
     * @param content
     *
     * @author wengym
     *
     * @date 2022/9/19 11:43
     *
     * @return void
     */
    public static void getQrCodeWithBackground(String content, OutputStream stream, String unit, String bgUrl) {
        try {
            // OSS获取背景图片
            BufferedImage backgroundImg = ImageIO.read(getSteamFromUrl(bgUrl));
            int backWidth = backgroundImg.getWidth();
            int backHeight = backgroundImg.getHeight();
            // 根据信息获取二维码
            BufferedImage qrCodeTemp = getQrCodeImage(content, 300, 300);
            int codeWidth = qrCodeTemp.getWidth();
            int codeHeight = qrCodeTemp.getHeight();
            Graphics2D g = backgroundImg.createGraphics();
            g.setColor(Color.black);
            g.setFont(new Font("黑体", Font.BOLD, 30));
            g.drawString(unit, (backWidth - unit.length() * 30) / 2, (backWidth - codeHeight - 30) + 20);
            //在背景图片上添加二维码图片(居中位置)
            g.drawImage(qrCodeTemp, (backWidth - codeWidth) / 2, (backHeight - codeHeight) / 2 - 10, codeWidth, codeHeight, null);
            g.dispose();
            log.info("二维码图片成功合成，浏览器即将开始下载");
            ImageIO.write(backgroundImg, "png", stream);
            stream.close();
        } catch (IOException e) {
            log.error("成功合成二维码失败，错误信息{}", e);
            e.printStackTrace();
        }
    }

    /**
     * 生成指定宽度和高度的带有特定颜色边距的Base64格式的二维码
     *
     * @param content
     *
     * @param configMap - margin
     *                  - bgColor Color
     *                  - width
     *                  - height
     *
     * @author wengym
     *
     * @date 2023/2/20 17:01
     *
     * @return java.awt.image.BufferedImage
     */
    public static String getQrCodeOfBase64(String content, Map<String, Object> configMap) {
        BufferedImage image = getQrCodeImage(content, configMap);
        String base64 = getBase64Data(image);
        return base64;
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
     * 生成指定宽度和高度的带有特定颜色边距的中间有logo的二维码
     *
     * @param content
     *
     * @param configMap - margin
     *                  - bgColor Color
     *                  - width
     *                  - height
     *                  - logoPath
     *
     * @author wengym
     *
     * @date 2023/2/20 17:01
     *
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage getQrCodeOfLogo(String content, Map<String, Object> configMap) {
        BufferedImage source = getQrCodeImage(content, configMap);
        String imagePath = (String)configMap.get("logoPath");
        source = insertImage(source, imagePath, true);
        return source;
    }

    /**
     * 在源图片中插入中间图片
     *
     * @param source
     *
     * @param imagePath
     *
     * @param needCompress
     *
     * @author wengym
     *
     * @date 2023/2/22 8:55
     *
     * @return BufferedImage
     */
    public static BufferedImage insertImage(BufferedImage source, String imagePath, boolean needCompress) {
        File file = new File(imagePath);
        if (!file.exists()) {
            System.err.println("" + imagePath + "   该文件不存在！");
            return null;
        }
        // LOGO的默认宽高
        final int WIDTH = 60;
        final int HEIGHT = 60;
        Image src = null;
        try {
            src = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) {
            // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (source.getWidth() - width) / 2;
        int y = (source.getHeight() - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, height, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
        return source;
    }

    /**
     * 生成指定宽度和高度的带有特定颜色边距的二维码
     * 如果不传configMap，默认生成长宽都是300，没有外边距，背景颜色是白色的二维码
     *
     * @param content
     *
     * @param configMap - margin
     *                  - bgColor Color
     *                  - width
     *                  - height
     *
     * @author wengym
     *
     * @date 2023/1/12 17:45
     *
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage getQrCodeImage(String content, Map<String, Object> configMap) {
        if (configMap == null) {
            configMap = new HashMap<>();
        }
        int width = 300;
        if (configMap.containsKey("width")) {
            width = (int)configMap.get("width");
        }
        int height = 300;
        if (configMap.containsKey("height")) {
            height = (int)configMap.get("height");
        }
        int margin = 0;
        if (configMap.containsKey("margin")) {
            margin = (int)configMap.get("margin");
        }
        Color bgColor = new Color(255, 255, 255);
        if (configMap.containsKey("bgColor")) {
            bgColor = (Color)configMap.get("bgColor");
        }
        int qrWidth = width - margin * 2;
        int qrHeight = height - margin * 2;
        BufferedImage qrCode = getQrCodeImage(content, qrWidth, qrHeight);
        // 背景图
        BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)qrCodeImage.getGraphics();
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, width, height);
        g2d.drawImage(qrCode, margin, margin, qrWidth, qrHeight, null);
        return qrCodeImage;
    }

    public static String getQrCodeContent(BufferedImage image) {
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        Result result = null;
        try {
            result = new MultiFormatReader().decode(bitmap, hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (result == null) {
            return "";
        }
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 生成指定宽度和高度的没有边距的二维码
     *
     * @param content
     *
     * @param width
     *
     * @param height
     *
     * @author wengym
     *
     * @date 2023/1/12 17:56
     *
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage getQrCodeImage(String content, int width, int height) {
        if (content == null || content.trim().equals("")) {
            return null;
        }
        content = content.trim();
        HashMap hits = new HashMap<EncodeHintType, Object>();
        // 设置字符编码类型
        hits.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 设置纠错等级，纠错等级越高存储的信息就越少
        hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置二维码边距，单位是像素，值越小，二维码距离四周越近，为0，表示没有边距
        hits.put(EncodeHintType.MARGIN, 0);
        try {
            // 把内容编码成矩阵
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hits);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            return bufferedImage;
        } catch (Exception e) {
            log.error("二维码生成异常！");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取网络文件流
     *
     * @param url
     * @return
     */
    public static InputStream getSteamFromUrl(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }
}
