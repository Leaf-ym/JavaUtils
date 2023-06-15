package com.ncepu.util.WordUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WordUtils {
    public static final String FOLDER_END = "/";
    private static final String TEMPLATE_WORD = "/home/vip/M05S.docx";
    private static final String TMP_FILE_FOLDER = "/home/vip/tmp";

    /**
     * @Author: Create By Chen Yong
     * @Description: 根据指定模板生成新的word文档
     * @Version: 1.0.0
     * @Date: 2019/12/23 9:29
     *//*
    public static void fillWordTemplate(String templatePath, String outPath, Map<String, Object> data) {
        log.info(">>> 读取word模板，并完成模板内容生成新的word文件");
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(data);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(">>> 模板填写完成，输出到桌面");
    }

    public static void fillWord(String templatePath, String outPath, Map<String, Object> data) {
        log.info(">>> 读取word模板，并完成模板内容生成新的word文件1");
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("work", policy).bind("hornor", policy)
                .bind("intellectualProperty", policy)
                .bind("paperCatalog", policy)
                .bind("otherSource", policy).build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(data);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(">>> 模板填写完成，输出到桌面");
    }

    *//**
     * 基于模板生成word文档
     *
     * @param templatePath
     * @param outPath
     * @param data
     * @param config
     * @return void
     * @author wengym
     * @date 2023/5/18 11:15
     *//*
    public static void fillWord(String templatePath, String outPath, Map<String, Object> data, Configure config) {
        log.info(">>> 读取word模板，并完成模板内容生成新的word文件1");
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(data);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(">>> 模板填写完成，输出到桌面");
    }

    *//**
     * 定义数组字段
     *
     * @param arr
     * @return void
     * @author wengym
     * @date 2023/5/18 11:17
     *//*
    public static Configure getConfig(String... arr) {
        if (arr == null || arr.length == 0) {
            return Configure.createDefault();
        }
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        ConfigureBuilder builder = Configure.newBuilder();
        for (String str : arr) {
            builder.bind(str, policy);
        }
        Configure config = builder.build();
        return config;
    }

    public static void fillRecommendWord(String templatePath, String outPath, Map<String, Object> data) {
        log.info(">>> 读取word模板，并完成模板内容生成新的word文件1");
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("allRecommend", policy).build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(data);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(">>> 模板填写完成，输出到桌面");
    }

    public static void fillScienceWord(String templatePath, String outPath, Map<String, Object> data) {
        log.info(">>> 读取word模板，并完成模板内容生成新的word文件");
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("otherMajorAuthors", policy).bind("otherMajorWorkUnits", policy)
                .bind("awards", policy)
                .bind("intellectualProperty", policy)
                .bind("paperCatalog", policy)
                .bind("otherSource", policy)
                .build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(data);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(">>> 模板填写完成，输出到桌面");
    }

    public static void fillInnovateWordTemplate(String templatePath, String outPath, Map<String, Object> data) {
        log.info(">>> 读取word模板，并完成模板内容生成新的word文件");
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("otherMajorAuthors", policy).bind("otherMajorWorkUnits", policy)
                .bind("awards", policy)
                .bind("intellectualProperty", policy)
                .bind("paperCatalog", policy)
                .bind("otherSource", policy)
                .build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(data);
        FileOutputStream out = null;
        try {
            ZipSecureFile.setMinInflateRatio(0.001);
            out = new FileOutputStream(outPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(">>> 模板填写完成，输出到桌面");
    }

    *//**
     * 省推荐名次
     *
     * @param templatePath
     * @param outPath
     * @param data
     *//*
    public static void recommendedWordTemplate(String templatePath, String outPath, Map<String, Object> data) {
        log.info(">>> 读取word模板，并完成模板内容生成新的word文件");
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("data", policy).build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(data);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(">>> 模板填写完成，输出到桌面");
    }


    *//**
     * 本地生成临时文件
     *
     * @param fileName
     * @param data
     * @return
     *//*
    public static String getNewFile(String fileName, Map<String, Object> data) {
        fillWordTemplate(TEMPLATE_WORD, TMP_FILE_FOLDER + FOLDER_END + fileName, data);
        return TMP_FILE_FOLDER + FOLDER_END + fileName;
    }

    *//**
     * 输出word并且删除临时文件
     *
     * @param response
     * @param fileName
     * @param data
     *//*
    public static void outPutFile(HttpServletResponse response, String fileName, Map<String, Object> data) {
        String filePath = getNewFile(fileName, data);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            log.info("输出完，删除临时文件");
            deleteFile(filePath);
        }
    }

    *//**
     * 输出word并且删除临时文件
     *
     * @param fileName
     * @param data
     *//*
    public static String getOssFilePath(String fileName, Map<String, Object> data) {
        String filePath = getNewFile(fileName, data);
        String wordFilePath = MConstants.COMMON_STR_EMPTY;
        try {
            // 存储到OSS
            InputStream inputStream = new FileInputStream(filePath);
            // 上传文件根目录，增加年份
            String baseFilePath = UploadOssUtil.COMMON_VIP + DateUtil.getNowYear() + FOLDER_END;
            UploadOssUtil.uploadFileStream(inputStream, fileName, baseFilePath, fileName);
            // 获取上传文件具体路径
            wordFilePath = AlOssHelper.getOssFileUrl(baseFilePath + fileName, UploadOssUtil.COMMON_ROOT);

            log.info("生成证书结束");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("生成证书异常，文件操作异常", e);
        } finally {
            deleteFile(filePath);
        }
        return wordFilePath;
    }

    *//**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     *//*
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                log.info("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            log.info("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Map data = new HashMap();
        data.put("userName", "章三");
        data.put("gender", "女");
        data.put("nationLabel", "汉");
        data.put("educationLabel", "小学");
        data.put("job", "扫地僧");
        data.put("positionTitle", "无");
        data.put("birthday", "1980.6.6");
        data.put("idCard", "99999999999");
        data.put("workUnit", "阿里巴巴");
        data.put("birthday", "1980.6.6");
        data.put("fullAddress", "杭州蚂蚁金服");
        data.put("cellPhone", "1383838438");
        data.put("email", "123@alibaba.com");
        data.put("year", "2019");
        data.put("imageUrl", new PictureRenderData(160, 240, ".png", BytePictureUtils.getUrlByteArray("http://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/64380cd7912397ddc69baf1a5b82b2b7d1a287c9.jpg")));
        fillWordTemplate("D:\\template.docx", "D:\\fill_template.docx", data);
    }*/
}
