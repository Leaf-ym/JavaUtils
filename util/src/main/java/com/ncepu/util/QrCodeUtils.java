package com.ncepu.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

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
            BufferedImage qrCodeTemp = getQrCodeBufferedImage(content, stream);
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

    public static BufferedImage getQrCodeBufferedImage(String content, OutputStream stream) {
        int width = 300;
        int height = 300;
        String format = "png";
        HashMap hits = new HashMap();
        //编码
        hits.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //纠错等级，纠错等级越高存储信息越少
        hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //边距
        hits.put(EncodeHintType.MARGIN, 0);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hits);
            // 防止二维码保存的时候背景全黑问题
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                            : 0xFFFFFFFF);
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
