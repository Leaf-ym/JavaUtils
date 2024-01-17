package util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncepu.util.CommonUtil;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author wengym
 * @version 1.0
 * @desc easyexcel工具测试类
 * @date 2022/10/21 14:03
 */
public class EasyExcelUtilsTest1 {
    public static final String FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 视频：videoUrl，辅助材料地址：otherStuffUrl，发明照片：innovatePhoto，护士执业证图片：nurseNumberPhoto，证件照：userPicUrl
     *
     * @return void
     * @author wengym
     * @date 2024/1/15 14:36
     */
    @Test
    public void handleContributePapers() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\contribute_papers.xlsx";
        JSONObject jsonObject = readFile(path);
        JSONArray data = jsonObject.getJSONArray("data");
        ExecutorService threadPool = createThreadPool();
        for (Object m : data) {
            threadPool.execute(() -> {
                LinkedHashMap<Integer, String> single = (LinkedHashMap) m;
                String paperTitle = single.get(8);
                paperTitle = paperTitle.replace("/", "-");
                paperTitle = paperTitle.replace("+", "-");
                paperTitle = paperTitle.replace(" ", "");
                String author = single.get(4);
                String gender = "1".equals(single.get(23)) ? "男" : "女";
                String authorCell = single.get(21);
                String year = formatDate(single.get(16), FORMAT_YYYY_MM_DD_HH_MI_SS, "YYYY");
                // 附件
                String fileKey = single.get(11);
                // 视频
                String videoUrl = single.get(19);
                // 辅助材料
                JSONArray otherStuffUrl = JSONArray.parseArray(single.get(55));
                // 用户头像
                String userPicUrl = single.get(50);
                // 省份推荐意见附件
                String provinceOpinionFileUrl = single.get(60);
                // 身份证图片正面
                String idCardImgFront = single.get(63);
                // 身份证图片反面
                String idCardImgBack = single.get(64);
                // 护士证
                JSONArray nurseCertificate = JSONArray.parseArray(single.get(65));
                // 盖章的省市推荐意见文件
                String recommendationFileUrl = single.get(67);

                System.out.println(paperTitle + "-" + author + "-" + gender + "-" + authorCell + "-" + year);
                String baseName = "-" + author + "-" + paperTitle;

                String baseDir = "C:\\Users\\Administrator\\Desktop\\杰出护理\\" + year + "\\" + author + "-" + authorCell + "-" + gender + "-" + paperTitle + "";
                if (isExist(baseDir + "【完毕】")) {
                    return;
                }
                deleteFiles(baseDir);
                try {
                    if (!isNullStr(fileKey)) {
                        String suffix = getFileSuffix(fileKey);
                        String outPath = baseDir + "\\附件" + baseName + "." + suffix;
                        downloadNetworkFile(fileKey, outPath);
                    }
                    if (!isNullStr(videoUrl)) {
                        String name = "视频";
                        String url = videoUrl;
                        String suffix = getFileSuffix(url);
                        String safeVidelUrl = getSafeUrl(url, 120, "");
                        String dir = baseDir + "\\视频\\";
                        String outPath = dir + name + baseName + "." + suffix;
                        downloadNetworkFile(safeVidelUrl, outPath);
                    }
                    if (otherStuffUrl != null && !otherStuffUrl.isEmpty()) {
                        for (Object o : otherStuffUrl) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            String suffix = getFileSuffix(url);
                            String dir = baseDir + "\\辅助材料\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!isNullStr(userPicUrl)) {
                        String suffix = getFileSuffix(userPicUrl);
                        String outPath = baseDir + "\\用户头像" + baseName + "." + suffix;
                        downloadNetworkFile(userPicUrl, outPath);
                    }
                    if (!isNullStr(provinceOpinionFileUrl)) {
                        String suffix = getFileSuffix(provinceOpinionFileUrl);
                        String outPath = baseDir + "\\省份推荐意见附件" + baseName + "." + suffix;
                        downloadNetworkFile(provinceOpinionFileUrl, outPath);
                    }
                    if (!isNullStr(idCardImgFront)) {
                        String suffix = getFileSuffix(idCardImgFront);
                        String outPath = baseDir + "\\身份证图片正面" + baseName + "." + suffix;
                        downloadNetworkFile(idCardImgFront, outPath);
                    }
                    if (!isNullStr(idCardImgBack)) {
                        String suffix = getFileSuffix(idCardImgBack);
                        String outPath = baseDir + "\\身份证图片反面" + baseName + "." + suffix;
                        downloadNetworkFile(idCardImgBack, outPath);
                    }
                    if (nurseCertificate != null && !nurseCertificate.isEmpty()) {
                        for (Object o : nurseCertificate) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            String suffix = getFileSuffix(url);
                            String dir = baseDir + "\\护士证\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!isNullStr(recommendationFileUrl)) {
                        String suffix = getFileSuffix(recommendationFileUrl);
                        String outPath = baseDir + "\\盖章的省市推荐意见文件" + baseName + "." + suffix;
                        downloadNetworkFile(recommendationFileUrl, outPath);
                    }

                    /////////////////////////////////
                    if (isExist(baseDir)) {
                        renameTo(baseDir, baseDir + "【完毕】");
                        System.out.println("处理完毕");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    deleteFiles(baseDir);
                }
            });
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                break;
            }
        }
    }

    public static Boolean isNullStr(Object str) {
        return str == null || "".equals(String.valueOf(str)) || "null".equals(String.valueOf(str).trim()) || "''".equals(String.valueOf(str).trim());
    }

    public static Boolean renameTo(String oldName, String newName) {
        if (oldName == null || "".equals(oldName.trim())) {
            return false;
        }
        if (newName == null || "".equals(newName.trim())) {
            return false;
        }
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        Boolean result = oldFile.renameTo(newFile);
        return result;
    }

    public static Boolean isExist(String path) {
        if (path == null || "".equals(path.trim())) {
            return false;
        }
        File file = new File(path);
        Boolean isExist = file.exists();
        return isExist;
    }

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

    public static void downloadNetworkFile(String url, String savePath) throws IOException {
        savePath = urlDecode(savePath);
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
                throw e;
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
            // 默认情况下，FileOutputStream 的构造函数会将文件以覆盖模式打开，即如果文件已经存在，则会先删除原有文件，然后创建新的文件进行写入操作。
            // 如果你希望在文件已经存在时追加内容而不是重新下载，可以使用 FileOutputStream 的另一个构造函数，并将第二个参数设置为 true，表示以追加模式打开文件。
            // 这样，如果文件已经存在，新的内容将会追加到文件末尾，而不是覆盖原有文件。
            out = new FileOutputStream(savePath);
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLength);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            throw e;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
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

    public static String formatDate(String date, String fromFormat, String toFormat) {
        SimpleDateFormat fromSdf = new SimpleDateFormat(fromFormat);
        SimpleDateFormat toSdf = new SimpleDateFormat(toFormat);
        try {
            String result = toSdf.format(fromSdf.parse(date));
            return result;
        } catch (ParseException e) {
        }
        return "";
    }

    public static ExecutorService createThreadPool() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                getNumOfProcessors() / 2,
                getNumOfProcessors(),
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        return threadPool;
    }

    public static Integer getNumOfProcessors() {
        Integer processors = Runtime.getRuntime().availableProcessors();
        return processors;
    }

    public static JSONObject readFile(String filePath) {
        JSONObject result = new JSONObject();
        List<Map<Integer, Object>> list = new ArrayList<>();
        EasyExcel.read(filePath, new AnalysisEventListener<Map<Integer, Object>>() {
            @Override
            public void invoke(Map<Integer, Object> map, AnalysisContext context) {
                // map是有序的LinkedHashMap
                list.add(map);
            }

            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                result.put("head", headMap);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
            }
        }).sheet().doRead();
        result.put("data", list);
        return result;
    }


    // 自定义主域名
    public static final String MAIN_DOMAIN = "zhhlxh.org.cn";
    // 系统自带
    public static final String DEFAULT_DOMAIN = "myqcloud.com";
    // 主域名防盗链
    private static final String RANDOM_KEY_MAIN = "INsCUOhQrBpJpmu0sLHu";
    // 限制访问IP数量
    private static final int RLIMIT = 3;
    private static final String SecretId = "AKIDDHB0icD3d4MYaVFv74qocIpBJHOMojD5";
    private static final String SecretKey = "xjL9nGl4CWegCXleMsEOTIvb7h7bua4Q";
    // 地域节点
    private static final String ENDPOINT = "vod.tencentcloudapi.com";

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
        String sign = CommonUtil.MD5Encode(RANDOM_KEY_MAIN + dir + t + exper + RLIMIT + us);
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
}
