package com.ncepu.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author wengym
 * @version 1.0
 * @desc 图像处理工具类
 * @date 2023/1/12 17:22
 */
public class ImageUtils {

    /**
     * 根据本地文件路径获取Image对象
     *
     * @param filePath
     *
     * @author wengym
     *
     * @date 2023/1/9 10:56
     *
     * @return java.awt.Image
     */
    public static Image getImage(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            return null;
        }
        Image image;
        try {
            image = ImageIO.read(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return image;
    }

    /**
     * 根据文件URL获取Image对象
     *
     * @param url
     *
     * @author wengym
     *
     * @date 2023/1/9 10:56
     *
     * @return java.awt.Image
     */
    public static Image getImageFromUrl(String url) {
        Image image;
        try {
            image = ImageIO.read(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return image;
    }

    /**
     * 把image对象存在到本地系统中
     *
     * @param image
     *
     * @param format 后缀，png，jpg
     *
     * @param filePath
     *
     * @author wengym
     *
     * @date 2023/1/9 11:12
     *
     * @return void
     */
    public static void writeTo(Image image, String format, String filePath) {
        if (image == null) {
            System.out.println("image == null!");
            return;
        }
        if (format == null || format.trim().equals("")) {
            format = "png";
        }
        File file = new File(filePath);
        try {
            if (file.exists()) {
                file.delete();
            }
            BufferedImage bufferedImage = (BufferedImage)image;
            ImageIO.write(bufferedImage, format, file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
