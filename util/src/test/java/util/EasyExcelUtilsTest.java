package util;

import com.ncepu.model.TeacherNoSettingExportVo;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc easyexcel工具测试类
 * @date 2022/10/21 14:03
 */
public class EasyExcelUtilsTest {
    @Test
    public void testExportMultiSheetFile() {
        // 列表头
        List<List<List<String>>> headLists = new ArrayList<>();
        List<List<String>> headList1 = new ArrayList<List<String>>() {{
            add(new ArrayList<String>() {{
                add("列一");
            }});
            add(new ArrayList<String>() {{
                add("列二");
            }});
        }};
        List<List<String>> headList2 = new ArrayList<List<String>>() {{
            add(new ArrayList<String>() {{
                add("列一点一");
            }});
            add(new ArrayList<String>() {{
                add("列二点二");
            }});
        }};
        headLists.add(headList1);
        headLists.add(headList2);
        // 列表数据
        List<List<List<String>>> dataLists = new ArrayList<>();
        List<List<String>> dataList1 = new ArrayList<>();
        List<String> data1 = new ArrayList<String>() {{
            add("1");
            add("1");
        }};
        List<String> data11 = new ArrayList<String>() {{
            add("11");
            add("11");
        }};
        dataList1.add(data1);
        dataList1.add(data11);
        List<List<String>> dataList2 = new ArrayList<>();
        List<String> data2 = new ArrayList<String>() {{
            add("2");
            add("2");
        }};
        List<String> data22 = new ArrayList<String>() {{
            add("22");
            add("2211111111111111111111111114444444444444444444444111111111111111111");
        }};
        dataList2.add(data2);
        dataList2.add(data22);
        dataLists.add(dataList1);
        dataLists.add(dataList2);
        String outFilePath = "D:\\template\\easyexcel\\multiSheetFile.xlsx";
        EasyExcelUtils.exportMultiSheetFile(dataLists, headLists, outFilePath);
    }

    @Test
    public void testMultipleList() {
        String templateFilePath = "D:\\template\\easyexcel\\病例样例_v1.xlsx";
        String outFilePath = "D:\\template\\easyexcel\\病例样例_v1_Out.xlsx";
        Map<String, Object> dataMap = EasyExcelUtils.getDataMap();
        EasyExcelUtils.exportFileFromTemplate(dataMap, templateFilePath, outFilePath);
    }

    @Test
    public void testExportFileFromTemplate() {
        String templateFilePath = "D:\\template\\easyexcel\\productTemplate.xlsx";
        String outFilePath = "D:\\template\\easyexcel\\productOut.xlsx";
        List<TeacherNoSettingExportVo> dataList = EasyExcelUtils.getDataList();
        EasyExcelUtils.exportFileFromTemplate(dataList, templateFilePath, outFilePath);
        String templateFilePath2 = "D:\\template\\easyexcel\\productTemplate2.xlsx";
        String outFilePath2 = "D:\\template\\easyexcel\\productOut2.xlsx";
        Map<String, Object> dataMap = EasyExcelUtils.getDataMap();
        EasyExcelUtils.exportFileFromTemplate(dataMap, templateFilePath2, outFilePath2);
    }
}
