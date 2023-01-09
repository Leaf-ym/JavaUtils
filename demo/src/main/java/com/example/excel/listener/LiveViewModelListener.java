package com.example.excel.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.excel.model.CommonExcelDataModel;
import com.example.excel.model.LiveSignModel;
import com.example.excel.model.LiveViewModel;
import com.ncepu.util.CollectionUtils;
import com.ncepu.util.DateUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.StringUtils;
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
import java.util.regex.Pattern;

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
public class LiveViewModelListener extends AnalysisEventListener<LiveViewModel> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 2000;
    private static int numIndex = 0;
    List<LiveViewModel> list = new ArrayList<>();

    public LiveViewModelListener() {
    }


    @Override
    public void invoke(LiveViewModel model, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(model));
        list.add(model);
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
        List<LiveSignModel> signList = new ArrayList<>();
        for (LiveViewModel model : list) {
            Double signCount = dealTime(model.getTime1(), model.getTime2());
            signList.add(LiveSignModel.builder()
                    .index(model.getIndex())
                    .liveName(model.getLiveName())
                    .userName(model.getUserName())
                    .cellPhone(model.getCellPhone())
                    .signCount(signCount)
                    .build());
        }
        exportFile(signList, "D:/123.xlsx");
    }

    public static <T> void exportFile(List<T> dataList, String outFilePath) {
        EasyExcel.write(outFilePath, dataList.get(0).getClass()).sheet(0).doWrite(dataList);
    }

    public Double dealTime(String time1, String time2) {
        List<String> time1List = CollectionUtils.getList(StringUtils.split(time1, ","));
        List<String> time2List = CollectionUtils.getList(StringUtils.split(time2, ","));
        List<String> rightTime1List = new ArrayList<>();
        for (int i = 0; i < time1List.size(); i++) {
            int compare = DateUtils.compareTwoDateTime(time1List.get(i), "2022-10-22 13:00:00", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
            if (compare == 1) {
                break;
            }
            rightTime1List.add(time1List.get(i));
        }
        if (rightTime1List.size() < 1) {
            // 如果没有一点前的数据，返回0.0
            return 0.0;
        }

        Long totalMinutes = 0L;
        for (int i = 0; i < rightTime1List.size(); i++) {
            totalMinutes += getMinutes(time2List.get(i));
        }
        // 统计第一次进入的时间到下午一点前的总时长，如果小于总的签到时长，则以总时长为准
        long seconds = DateUtils.getDiffSecondOfDateTime("2022-10-22 13:00:00", rightTime1List.get(0), DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
        long minutes = seconds / 60;
        if (minutes < totalMinutes) {
            totalMinutes = minutes;
        }
        // 向上取整
        return Math.ceil(totalMinutes / 120.0);
    }

    public Integer getMinutes(String time) {
        String hour = Pattern.compile("时.*分").matcher(time).replaceAll("");
        String minute = Pattern.compile(".*时").matcher(time).replaceAll("").replace("分", "");
        Integer minutes = (Integer.valueOf(hour) * 60) + Integer.valueOf(minute);
        PrintUtils.println(minutes);
        return minutes;
    }

}
