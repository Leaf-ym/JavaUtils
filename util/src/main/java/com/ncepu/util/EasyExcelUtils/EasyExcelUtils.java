package com.ncepu.util.EasyExcelUtils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.alibaba.fastjson.JSONObject;
import com.ncepu.model.TeacherNoSettingExportVo;
import com.ncepu.util.EasyExcelUtils.strategy.CustomCellWriteHeightConfig;
import com.ncepu.util.EasyExcelUtils.strategy.CustomCellWriteWidthConfig;
import com.ncepu.util.EasyExcelUtils.strategy.StrategyUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc EasyExcel工具类
 * @date 2022/10/21 11:59
 */
public class EasyExcelUtils {
    /**
     * 读取Excel表到model列表中
     *
     * @param filePath
     * @param cls
     * @return java.util.List<T>
     * @author wengym
     * @date 2023/3/25 19:11
     */
    public static <T> List<T> readFile(String filePath, Class<T> cls) {
        List<T> list = new ArrayList<>();
        EasyExcel.read(filePath, cls, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T model, AnalysisContext context) {
                list.add(model);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
            }
        }).sheet().doRead();
        return list;
    }

    /**
     * 读取Excel表到map列表中
     *
     * @param filePath
     *
     * @author wengym
     *
     * @date 2023/9/1 15:51
     *
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
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

    /**
     * 基于列表导出数据到指定的多Sheet的excel表文件中
     * excel表文件的每一张Sheet都在headLists中
     *
     * @param dataLists 数据列表 第一层list的元素是sheet数据，第二层list的元素是行数据，第三层list的元素是单元格数据
     *
     * @param headLists
     *
     * @param outFilePath
     *
     * @author wengym
     *
     * @date 2023/7/18 11:33
     *
     * @return void
     */
    public static <T> void exportMultiSheetFile(List<List<List<String>>> dataLists, List<List<List<String>>> headLists, String outFilePath) {
        ExcelWriter excelWriter = EasyExcel.write(outFilePath).build();
        for (int index = 1; index <= dataLists.size(); index++) {
            WriteSheet sheet = EasyExcel.writerSheet(index - 1, "Sheet" + index)
                    .head(headLists.get(index - 1))
                    .registerWriteHandler(new CustomCellWriteWidthConfig())
                    //.registerWriteHandler(new CustomCellWriteHeightConfig())
                    //.registerWriteHandler(StrategyUtils.getStyleStrategy())
                    .build();
            excelWriter.write(dataLists.get(index - 1), sheet);
        }
        excelWriter.finish();
    }

    /**
     * 基于Map导出数据到指定excel表文件
     * excel表文件的头部在列表的model中
     *
     * @param dataList    数据列表
     * @param outFilePath D:\example.xlsx
     * @return void
     * @author wengym
     * @date 2022/10/25 9:14
     */
    public static <T> void exportFile(List<List<String>> dataList, List<List<String>> headList, String outFilePath) {
        EasyExcel.write(outFilePath).head(headList).sheet(0).doWrite(dataList);
    }

    /**
     * 基于model导出数据到指定excel表文件
     * excel表文件的头部在列表的model中
     *
     * @param dataList    数据列表
     * @param outFilePath D:\example.xlsx
     * @return void
     * @author wengym
     * @date 2022/10/25 9:14
     */
    public static <T> void exportFile(List<T> dataList, String outFilePath) {
        EasyExcel.write(outFilePath, dataList.get(0).getClass()).sheet(0).doWrite(dataList);
    }

    /**
     * 基于模板导出数据到指定excel表文件
     *
     * @param dataList
     * @param templateFilePath
     * @param outFilePath
     * @return void
     * @author wengym
     * @date 2022/10/21 16:12
     */
    public static <T> void exportFileFromTemplate(List<T> dataList, String templateFilePath, String outFilePath) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量 {前缀.} 前缀可以区分不同的list
        ExcelWriter writer = EasyExcel.write(outFilePath, dataList.get(0).getClass()).withTemplate(templateFilePath).build();
        WriteSheet sheet0 = EasyExcel.writerSheet(0).build();
        writer.fill(dataList, sheet0);
        writer.finish();
    }

    /**
     * 导出基于对象和列表的excel文件
     *
     * @param dataMap
     * @param templateFilePath
     * @param outFilePath
     * @return void
     * @author wengym
     * @date 2022/10/21 16:12
     */
    public static void exportFileFromTemplate(Map<String, Object> dataMap, String templateFilePath, String outFilePath) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量 {前缀.} 前缀可以区分不同的list
        Map<String, Object> objectMap = new HashMap<>();
        Map<String, List> listMap = new HashMap<>();
        for (String key : dataMap.keySet()) {
            if (dataMap.get(key) instanceof List) {
                listMap.put(key, (List) dataMap.get(key));
            } else {
                objectMap.put(key, dataMap.get(key));
            }
        }
        ExcelWriter excelWriter = EasyExcel.write(outFilePath).withTemplate(templateFilePath).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 需要强制创建新行，否则模板中的给定位置的数据会被列表替换掉
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        // 如果有多个list 模板上必须有{前缀.} 这里的前缀就是 data1，然后多个list必须用 FillWrapper包裹
        for (String key : listMap.keySet()) {
            // 填充列表
            excelWriter.fill(new FillWrapper(key, listMap.get(key)), fillConfig, writeSheet);
        }
        // 填充给定位置的数据
        excelWriter.fill(objectMap, writeSheet);
        excelWriter.finish();
    }

    public static List<TeacherNoSettingExportVo> getDataList() {
        List<TeacherNoSettingExportVo> list = new ArrayList<>();
        TeacherNoSettingExportVo model1 = TeacherNoSettingExportVo.builder().serialNum(1).productType("课程").productName("护理学1").originFrom("云南1").phone("77956101").build();
        TeacherNoSettingExportVo model2 = TeacherNoSettingExportVo.builder().serialNum(2).productType("课程").productName("护理学2").originFrom("云南2").phone("77956102").build();
        TeacherNoSettingExportVo model3 = TeacherNoSettingExportVo.builder().serialNum(3).productType("课程").productName("护理学3").originFrom("云南3").phone("77956103").build();
        list.add(model1);
        list.add(model2);
        list.add(model3);
        return list;
    }

    public static Map<String, Object> getDataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("list", getDataList());
        map.put("concatPerson", "翁一茗");
        map.put("concatPhone", "17325302081");
        return map;
    }

    /**
     * 获取多列表数据
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author wengym
     * @date 2023/5/31 17:24
     */
    public static Map<String, Object> getMultiListDataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("list", getDataList());
        map.put("userId", "31-003");
        map.put("userNo", "0001");
        map.put("userName", "QSY");

        return map;
    }
}
