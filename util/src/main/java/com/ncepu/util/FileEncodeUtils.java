package com.ncepu.util;

import info.monitorenter.cpdetector.io.*;

import java.io.File;

/**
 * @author wengym
 * @version 1.0
 * @desc 文件编码处理工具
 * @date 2023/1/5 9:22
 */
public class FileEncodeUtils {
    /**
     * 获取文件的编码字符集
     *
     * @param filePath
     * @return java.lang.String
     * @author wengym
     * @date 2023/1/5 10:04
     */
    public static String getFileEncode(String filePath) {
        if (filePath == null || filePath.trim().equals("")) {
            throw new NullPointerException("文件路径不能为空！");
        }
        String fileEncoding = null;
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
         * 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        File f = new File(filePath);
        try {
            charset = detector.detectCodepage(f.toURI().toURL());
            if (charset != null) {
                fileEncoding = charset.name();
            } else {
                fileEncoding = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileEncoding;
    }
}
