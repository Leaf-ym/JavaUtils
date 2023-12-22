package com.ncepu.util;

import com.ncepu.util.SystemUtils.model.Sys;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author wengym
 * @version 1.0
 * @desc 文件处理工具类
 * @date 2022/8/2 9:05
 */
public class FileUtils {
    /**
     * 自定义主域名
     */
    public static final String MAIN_DOMAIN = "zhhlxh.org.cn";
    /**
     * 系统自带
     */
    public static final String DEFAULT_DOMAIN = "myqcloud.com";
    /**
     * 主域名防盗链
     */
    private static final String RANDOM_KEY_MAIN = "INsCUOhQrBpJpmu0sLHu";
    /**
     * 限制访问IP数量
     */
    private static final int RLIMIT = 3;

    /**
     * 查询符合正则表达式的字符串集合
     *
     * @param regex
     * @return java.util.Set<java.lang.String>
     * @author wengym
     * @date 2022/11/4 10:45
     */
    public static Set<String> getStrSet(String regex, File file) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("filePath必须是文件类型！");
        }
        try {
            FileInputStream in = new FileInputStream(file);
            byte[] arr = new byte[1024];
            int readLength = 0;
            StringBuilder sb = new StringBuilder();
            while ((readLength = in.read(arr)) > -1) {
                sb.append(new String(arr, 0, readLength));
            }
            PrintUtils.println(sb.toString());
            Set<String> resultSet = handleStr(regex, sb.toString());
            return resultSet;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<String>();
    }

    public static Set<String> handleStr(String regex, String str) {
        Set<String> resultSet = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            resultSet.add(str.substring(matcher.start(), matcher.end()));
        }
        return resultSet;
    }

    /**
     * 获取临时路径
     *
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/17 15:55
     */
    public static String getTempDirPath() {
        String projectPath = getProjectPath();
        String tempDirPath = projectPath + File.separator + "temp";
        File file = new File(tempDirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return tempDirPath;
    }

    /**
     * 获取项目路径
     *
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/17 15:55
     */
    public static String getProjectPath() {
        String path = System.getProperty("user.dir");
        return path;
    }

    /**
     * 获取文件信息
     *
     * @param path
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/4 9:15
     */
    public static String getFileInfo(String path) {
        if (path == null || "".equals(path)) {
            return "";
        }
        String result = "";
        File file = new File(path);
        if (file.isFile()) {
            result = getFileTypeInfo(file);
        }
        if (file.isDirectory()) {
            result = getDieTypeInfo(file);
        }
        return result;
    }

    public static String getFileTypeInfo(File file) {
        String result = null;
        try {
            result = "文件名称：" + file.getName() + "\n" +
                    "文件大小：" + file.length() + "字节" + "\n" +
                    "最后更改时间：" + new Date(file.lastModified()) + "\n" +
                    "上一级路径：" + file.getParent() + "\n" +
                    "相对路径：" + file.getPath() + "\n" +
                    "绝对路径：" + file.getAbsolutePath() + "\n" +
                    "规范路径：" + file.getCanonicalPath() + "\n" +
                    "是否绝对路径：" + file.isAbsolute() + "\n" +
                    "空闲空间：" + file.getFreeSpace() + "\n" +
                    "全部空间：" + file.getTotalSpace() + "\n" +
                    "不可用空间：" + file.getUsableSpace() + "\n" +
                    "是否存在：" + file.exists() + "\n" +
                    "是否可读：" + file.canRead() + "\n" +
                    "是否可写：" + file.canExecute() + "\n" +
                    "是否可执行：" + file.canExecute() + "\n" +
                    "哈希值：" + file.hashCode() + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getDieTypeInfo(File file) {
        return null;
    }

    /**
     * 获取文件名的后缀
     *
     * @param fileName
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/3 11:39
     */
    public static String getFileSuffix(String fileName) {
        if (fileName == null || fileName.equals("")) {
            throw new NullPointerException("fileName");
        }
        if (!fileName.contains(".")) {
            return "";
        }
        String[] arr = fileName.split("\\.");
        if (arr != null && arr.length > 1) {
            return arr[arr.length - 1];
        }
        return "";
    }

    /**
     * 通过Http下载本地的word文件
     *
     * @param response
     * @param path
     * @return void
     * @author wengym
     * @date 2022/8/8 13:42
     */
    public static void downloadLocalWordFileByHttp(HttpServletResponse response, String path) {
        // 通过文件流读取到文件，再将文件通过response的输出流，返回给页面下载
        InputStream in = null;
        ServletOutputStream out = null;
        File file = null;
        try {
            file = new File(path);
            in = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(file.getName(), "UTF-8"))));
            out = response.getOutputStream();
            byte[] buffer = new byte[512];
            int readLength;
            // 用响应对象response中的输出流读取生成好的文件
            while ((readLength = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLength);
            }
        } catch (Exception e) {
            System.out.println("导出word出错");
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                    if (file != null) {
                        file.delete(); // 删除临时文件
                    }
                } catch (IOException e) {
                    System.out.println("删除删除临时文件出错");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 基于URL获取其File对象
     *
     * @param url
     * @return java.io.File
     * @author wengym
     * @date 2022/8/17 15:43
     */
    public static File getNetworkFile(String url) {
        InputStream in = null;
        FileOutputStream out = null;

        try {
            URL dataUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) dataUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(1000);
            in = conn.getInputStream();
            //File.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * URL编码
     *
     * @param url
     * @return java.lang.String
     * @author wengym
     * @date 2022/9/7 9:55
     */
    public static String urlEncode(String url) {
        String result = "";
        if (url == null) {
            return result;
        }
        try {
            result = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL解码
     *
     * @param url
     * @return java.lang.String
     * @author wengym
     * @date 2022/9/7 9:55
     */
    public static String urlDecode(String url) {
        String result = "";
        if (url == null) {
            return result;
        }
        try {
            result = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取网络文件的输入流
     *
     * @param url
     * @return java.io.InputStream
     * @author wengym
     * @date 2022/9/19 11:48
     */
    public static InputStream getInputStreamFromUrl(String url) {
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
            System.out.println("获取网络文件出现异常，文件路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载远程的网络文件
     * 不要把输入流中的内容一次性读入缓存，否则会因为输入流内容过多导致内容溢出问题
     * 在 Java 中，使用 file.createNewFile() 方法创建文件时，如果指定的目录不存在，它不会自动创建目录。createNewFile() 方法只会创建文件本身，而不会创建父目录。
     * 如果希望在创建文件时同时创建目录，可以使用 file.mkdirs() 方法。mkdirs() 方法将创建所有不存在的目录，包括父目录和子目录。
     *
     * @param url 文件的网络URL
     * @param savePath 具体的文件路径，如C:\Users\Administrator\Desktop\专业委员会\1.txt
     * @return void
     * @author wengym
     * @date 2022/8/3 10:38
     */
    public static void downloadNetworkFile(String url, String savePath) {
        savePath = FileUtils.urlDecode(savePath);
        File file = new File(savePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            // 创建目录
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            try {
                // 创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL dataUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) dataUrl.openConnection();
            // 请求方法为GET
            conn.setRequestMethod("GET");
            // 连接超时为1000毫秒
            conn.setConnectTimeout(1000);
            // 获取输入流
            in = conn.getInputStream();
            out = new FileOutputStream(savePath);
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLength);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 下载字节数组文件
     *
     * @param bytes
     *
     * @param savePath
     *
     * @author wengym
     *
     * @date 2023/2/21 8:53
     *
     * @return void
     */
    public static void downloadBytesFile(byte[] bytes, String savePath) {
        savePath = FileUtils.urlDecode(savePath);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(savePath);
            out.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据原始链接，返回安全链接
     *
     * @param originUrl 原始视频地址
     * @param minutes   地址有效时间，可以默认俩小时
     * @param exper     0或者空表示不试看
     * @return
     */
    public static String getSafeUrl(String originUrl, int minutes, String exper) {
        // 参数准备
        String dir = getDir(originUrl);
        String t = getEndTime(minutes);
        String us = CommonUtil.randomLOWUUID().substring(0, 10);
        String sign = MD5Encode(RANDOM_KEY_MAIN + dir + t + exper + RLIMIT + us);
        StringBuilder sb = new StringBuilder();
        return sb.append(originUrl)
                .append("?t=")
                .append(t)
                .append("&exper=")
                .append(exper)
                .append("&rlimit=")
                .append(RLIMIT)
                .append("&us=")
                .append(us)
                .append("&sign=")
                .append(sign).toString();
    }

    /**
     * 简单MD5加密
     *
     * @param str
     * @return
     */
    public static String MD5Encode(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取dir
     *
     * @param originUrl
     * @return
     */
    public static String getDir(String originUrl) {
        if (originUrl.contains(MAIN_DOMAIN)) {
            return originUrl.substring(originUrl.indexOf(MAIN_DOMAIN) + MAIN_DOMAIN.length(), originUrl.lastIndexOf("/") + 1);
        } else {
            return originUrl.substring(originUrl.indexOf(DEFAULT_DOMAIN) + DEFAULT_DOMAIN.length(), originUrl.lastIndexOf("/") + 1);
        }
    }

    /**
     * 获取地址失效时间节点时间戳
     *
     * @param minutes
     * @return
     */
    public static String getEndTime(int minutes) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        date = calendar.getTime();
        return Long.toHexString(date.getTime() / 1000);
    }

    private static byte[] toByteArray(InputStream in) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int readLen;
        try {
            while ((readLen = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] result = out.toByteArray();
        return result;
    }

    /**
     * 获取路径对应文件的编码
     *
     * @param path 文件路径
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/2 9:18
     */
    public static String getFileEncode(String path) {
        byte[] b = new byte[3];
        try {
            InputStream in = new FileInputStream(path);
            in.read(b);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "系统找不到指定的文件。";
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
            return "UTF-8";
        } else {
            return "GBK";
        }
    }

    /**
     * 删除某个文件、某个空目录、某个不为空的目录
     * File.delete()只能删除某个文件或者空目录，要想要删除某个目录及其所有子文件和子目录，要使用递归进行删除。
     *
     * @param path 文件路径
     * @return java.lang.Boolean
     * @author wengym
     * @date 2022/8/2 9:31
     */
    public static Boolean deleteFiles(String path) {
        Boolean result;
        File file = new File(path);
        if (file.isDirectory()) {
            // 如果是目录，需要递归删除文件
            File[] childrenFiles = file.listFiles();
            for (File childFile : childrenFiles) {
                result = deleteFiles(childFile.getPath());
                if (!result) {
                    // 如果没有删到文件就退出
                    return result;
                }
            }
        }
        // 实际删除：先删除目录中的文件列表，后删除空目录
        result = delete(path);
        return result;
    }

    /**
     * 删除文件
     *
     * @param path
     * @return java.lang.Boolean
     * @author wengym
     * @date 2022/8/3 17:04
     */
    public static Boolean delete(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        Boolean flag = file.delete();
        if (!flag) {
            System.gc();
            System.runFinalization();
            flag = file.delete();
        }
        return flag;
    }

    public static String getProjectRootDir() {
        // 项目根目录
        String path = System.getProperty("user.dir");
        return path;
    }

    /**
     * 往一个文件中写入字符串内容（该文件如果不存在则创建一个，如果存在则覆盖之）
     *
     * @param content
     * @param path
     * @return void
     * @author wengym
     * @date 2022/8/2 18:39
     */
    public static void writeTo(String content, String path) {
        File file = new File(path);
        FileOutputStream out = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            out = new FileOutputStream(file);
            out.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 往一个文件中写入字符串内容（该文件如果不存在则创建一个，如果存在则追加之）
     *
     * @param content
     * @param path
     * @return void
     * @author wengym
     * @date 2022/8/3 9:11
     */
    public static void appendTo(String content, String path) {
        FileOutputStream out = null;
        ByteArrayInputStream byteIn = null;
        File file = new File(path);
        try {
            // true表示如果存在文件则追加内容
            out = new FileOutputStream(file, true);
            byteIn = new ByteArrayInputStream(content.getBytes());
            int readLength;
            byte[] buffer = new byte[1024];
            while ((readLength = byteIn.read(buffer)) != -1) {
                out.write(buffer, 0, readLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (byteIn != null) {
                    byteIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取某一个文件并返回字符串内容
     *
     * @param path
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/2 18:40
     */
    public static String readFrom(String path) {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            // 如果文件不存在，或并不是一个文件，则返回空串
            return "";
        }
        FileInputStream in = null;
        StringBuilder sb = new StringBuilder();
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            while (in.read(bytes) > 0) {
                sb.append(new String(bytes));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 创建多层目录
     * mkdir只能创建单层目录
     *
     * @param path
     * @return java.lang.Boolean
     * @author wengym
     * @date 2022/8/2 18:31
     */
    public static Boolean mkdirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 按指定格式来压缩文件
     *
     * @param path
     * @param format zip | rar
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/3 9:26
     */
    public static String zipFile(File path, String format) throws Exception {
        String zipFileName;
        ZipOutputStream zipOutputStream = null;
        try {
            if (path.isDirectory()) {
                // 目录
                zipFileName = path.getParent() + File.separator + path.getName() + "."
                        + format;
            } else {
                // 单个文件
                zipFileName = path.getParent()
                        + path.getName().substring(0, path.getName().lastIndexOf("."))
                        + "." + format;
            }
            // 压缩输出流
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            zip(path, zipOutputStream, "");
        } catch (Exception e) {
            zipFileName = "";
        } finally {
            if (null != zipOutputStream) {
                // 关闭流
                try {
                    zipOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return zipFileName;
    }

    /**
     * 递归压缩文件
     *
     * @param file            当前文件
     * @param zipOutputStream 压缩输出流
     * @param relativePath    相对路径
     * @return void
     * @author wengym
     * @date 2022/8/3 9:27
     */
    private static void zip(File file, ZipOutputStream zipOutputStream, String relativePath)
            throws IOException {
        FileInputStream fileInputStream = null;
        try {
            // 当前为文件夹
            if (file.isDirectory()) {
                // 当前文件夹下的所有文件
                File[] list = file.listFiles();
                if (null != list) {
                    // 计算当前的相对路径
                    relativePath += (relativePath.length() == 0 ? "" : "/") + file.getName();
                    // 递归压缩每个文件
                    for (File f : list) {
                        zip(f, zipOutputStream, relativePath);
                    }
                }
            } else {
                // 压缩文件
                // 计算文件的相对路径
                relativePath += (relativePath.length() == 0 ? "" : "/") + file.getName();
                // 写入单个文件
                zipOutputStream.putNextEntry(new ZipEntry(relativePath));
                fileInputStream = new FileInputStream(file);
                int readLen;
                byte[] buffer = new byte[1024];
                while ((readLen = fileInputStream.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, readLen);
                }
                zipOutputStream.closeEntry();
            }
        } finally {
            // 关闭流
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
