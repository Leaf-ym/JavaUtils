package com.example.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.excel.model.CommonExcelDataModel;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * 会员信息导入监听器
 *
 * @author wengym
 *
 * @date 2022/4/27 13:47
 *
 * @return
 *
 */
@Slf4j
public class CommonExcelDataListener extends AnalysisEventListener<CommonExcelDataModel> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    private static int numIndex = 0;
    List<CommonExcelDataModel> list = new ArrayList<>();

    public CommonExcelDataListener() {
    }


    @Override
    public void invoke(CommonExcelDataModel commonExcelDataModel, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(commonExcelDataModel));
        list.add(commonExcelDataModel);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        this.saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("处理数据[{}]", list);
        for (CommonExcelDataModel model : list) {
            String path = "D:\\file\\" + model.getFileName() + "." +getFileSuffix(model.getFileUrl());
            downloadNetworkFile(model.getFileUrl(), path);
        }
    }

    /**
     * 下载远程的网络文件
     *
     * @param url
     * @param savePath
     * @return void
     * @author wengym
     * @date 2022/8/3 10:38
     */
    public static void downloadNetworkFile(String url, String savePath) {
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
}
