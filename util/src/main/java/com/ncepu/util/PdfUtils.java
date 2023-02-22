package com.ncepu.util;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

/**
 * @author wengym
 * @version 1.0
 * @desc PDF文件工具类
 * @date 2023/2/2 8:37
 */
public class PdfUtils {

    private static void disposeJar(String jarName, String replaceFile) {
        List<String> deletes = new ArrayList<>();
        deletes.add("META-INF/37E3C32D.SF");
        deletes.add("META-INF/37E3C32D.RSA");
        File oriFile = new File(jarName);
        if (!oriFile.exists()) {
            System.out.println("######Not Find File:" + jarName);
            return;
        }
        //将文件名命名成备份文件
        String bakJarName = jarName.substring(0, jarName.length() - 3) + "cracked.jar";
        //   File bakFile=new File(bakJarName);
        try {
            //创建文件（根据备份文件并删除部分）
            JarFile jarFile = new JarFile(jarName);
            JarOutputStream jos = new JarOutputStream(new FileOutputStream(bakJarName));
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = (JarEntry) entries.nextElement();
                if (!deletes.contains(entry.getName())) {
                    if (entry.getName().equals("com/aspose/pdf/ADocument.class")) {
                        System.out.println("Replace:-------" + entry.getName());
                        JarEntry jarEntry = new JarEntry(entry.getName());
                        jos.putNextEntry(jarEntry);
                        FileInputStream fin = new FileInputStream(replaceFile);
                        byte[] bytes = readStream(fin);
                        jos.write(bytes, 0, bytes.length);
                    } else {
                        jos.putNextEntry(entry);
                        byte[] bytes = readStream(jarFile.getInputStream(entry));
                        jos.write(bytes, 0, bytes.length);
                    }
                } else {
                    System.out.println("Delete:-------" + entry.getName());
                }
            }
            jos.flush();
            jos.close();
            jarFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

    /**
     * PDF去除水印
     *
     * @author Hanwenshen
     */

    public static void pdfRemoveWatermark(String filePath) {
        try {
            //PDF转图片
            pdfToImage(filePath);
            List<String> fileNames = getFileNames("D:/pdf/pdfToImage");
            List<File> fileList = new ArrayList<>();
            for (String fileName : fileNames) {
                fileList.add(new File("D:/pdf/pdfToImage/" + fileName));
            }
            //去除PDF水印
            removePdfWatermark(fileList);
            //去除水印后的图片转PDF
            List<Integer> fileNamesInteger = new ArrayList<>();
            List<String> allFiles = new ArrayList<>();
            for (String fileName : fileNames) {
                if (fileName.endsWith(".png")) {
                    String str = fileName.substring(0, fileName.indexOf(".png"));
                    fileNamesInteger.add(Integer.valueOf(str));
                }
            }
            Collections.sort(fileNamesInteger);
            for (Integer fileName : fileNamesInteger) {
                String str = "D:/pdf/pdfToImage/" + fileName + ".png";
                allFiles.add(str);
            }
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            //图片转PDF
            imgOfPdf("D:\\pdf\\pdfToImage\\pdf\\1.pdf", allFiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void pdfToImage(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument doc = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        for (int i = 0; i < pageCount; i++) {
            try {
                BufferedImage image = renderer.renderImageWithDPI(i, 300);
                ImageIO.write(image, "png", new File("D:/pdf/pdfToImage/" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 得到文件名称
     *
     * @param path 路径
     * @return {@link List}<{@link String}>
     */
    private static List<String> getFileNames(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        return getFileNames(file, fileNames);
    }

    /**
     * 得到文件名称
     *
     * @param file      文件
     * @param fileNames 文件名
     * @return {@link List}<{@link String}>
     */
    private static List<String> getFileNames(File file, List<String> fileNames) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFileNames(f, fileNames);
            } else {
                fileNames.add(f.getName());
            }
        }
        return fileNames;
    }

    /**
     * 去除文件列表里图片的水印并替换
     *
     * @Param fileList 文件列表
     */
    public static void removePdfWatermark(List<File> fileList) {
        try {
            for (File file : fileList) {
                if (!file.getName().endsWith("png") && !file.getName().endsWith("jpg")) {
                    continue;
                }
                //用ImageIO流读取像素块
                BufferedImage bi = ImageIO.read(file);
                if (bi != null) {
                    removeWatermark(bi);
                    //生成的图片格式
                    String formatName = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                    //用ImageIO流生成的处理图替换原图片
                    ImageIO.write(bi, formatName, file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 去除水印
     */
    private static void removeWatermark(BufferedImage bi) {
        Color wColor = new Color(254, 254, 254);
        Color hColor = new Color(197, 196, 191);
        Color gColor = new Color(191, 191, 191);
        //白底水印
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int color = bi.getRGB(i, j);
                Color oriColor = new Color(color);
                int red = oriColor.getRed();
                int greed = oriColor.getGreen();
                int blue = oriColor.getBlue();
                if (red == 254 && greed == 254 && blue == 254) {
                    continue;
                }
                if (red > 220 && greed > 180 && blue > 80) {
                    bi.setRGB(i, j, wColor.getRGB());
                }
                if (red <= 240 && greed >= 200 && blue >= 150) {
                    bi.setRGB(i, j, wColor.getRGB());
                }
            }
        }
    }

    public static void imgOfPdf(String filepath, List<String> imgUrl) {

        try {
            //输出pdf文件路径
            String pdfUrl = filepath;
            //生成pdf
            File file = Pdf(imgUrl, pdfUrl);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File Pdf(List<String> imageUrllist, String mOutputPdfFileName) {
        //new一个pdf文档
        Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
        try {
            //pdf写入
            PdfWriter.getInstance(doc, new FileOutputStream(mOutputPdfFileName));
            //打开文档
            doc.open();
            //循环图片List，将图片加入到pdf中
            for (int i = 0; i < imageUrllist.size(); i++) {
                //在pdf创建一页
                doc.newPage();
                //通过文件路径获取image
                com.itextpdf.text.Image png1 = com.itextpdf.text.Image.getInstance(imageUrllist.get(i));
                float height = png1.getHeight();
                float width = png1.getWidth();
                int percent = getPercent2(height, width);
                png1.setAlignment(Image.MIDDLE);
                // 表示是原来图像的比例;
                png1.scalePercent(percent + 3);
                doc.add(png1);
            }
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出流
        File mOutputPdfFile = new File(mOutputPdfFileName);
        if (!mOutputPdfFile.exists()) {
            mOutputPdfFile.deleteOnExit();
            return null;
        }
        //反回文件输出流
        return mOutputPdfFile;
    }

    public static int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 450 / w * 100;
        p = Math.round(p2);
        return p;
    }
}
