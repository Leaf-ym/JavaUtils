package com.ncepu.util;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

    /**
     * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
     * @author temdy
     * @Date 2015-01-26
     * @param imgStr 图片Base64数据
     * @param path 图片路径
     * @return
     */
    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //左转90度
    public static BufferedImage rotateImage90(BufferedImage bufferedImage) throws Exception
    {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d =
                (img = new BufferedImage(h, w, type) ).createGraphics()
        ).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(270), w / 2, h / 2 + (w-h)/2);
        graphics2d.drawImage(bufferedImage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

    /**
     * 缩放图像的尺寸
     *
     * @param bufferedImage 目标图像
     * @param widthOfScale 宽的缩放比例 > 0
     * @param heightOfScale 高的缩放比例 > 0
     * @return
     */
    public static BufferedImage zoomImage(BufferedImage bufferedImage,float widthOfScale,float heightOfScale){
        if(widthOfScale<=0||heightOfScale<=0||(widthOfScale==1&&heightOfScale==1)) return bufferedImage;
        // 原图像的宽与高
        int w=bufferedImage.getWidth();
        int h=bufferedImage.getHeight();
        // 按比例缩小或放大图像的宽高，将浮点型转为整型
        int widthImg=(int)(w*widthOfScale);
        int heightImg=(int)(h*heightOfScale);
        // 构造一个指定宽高和类型为原图像类型的BufferedImage
        BufferedImage img=new BufferedImage(widthImg,heightImg,bufferedImage.getType());
        // 绘制图像
        // getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
        // Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
        img.getGraphics().drawImage(bufferedImage.getScaledInstance(widthImg,heightImg,Image.SCALE_SMOOTH),0,0,null);
        return img;
    }
}
