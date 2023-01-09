package com.example.excel.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.excel.model.IdCardModel;
import com.example.excel.model.LiveSignModel;
import com.example.excel.model.LiveViewModel;
import com.ncepu.util.CollectionUtils;
import com.ncepu.util.DateUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

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
public class IdCardModelListener extends AnalysisEventListener<IdCardModel> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5000;
    private static int numIndex = 0;
    List<LiveViewModel> list = new ArrayList<>();

    public IdCardModelListener() {
    }


    @Override
    public void invoke(IdCardModel model, AnalysisContext analysisContext) {
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

}
