package util;

import com.ncepu.model.TeacherNoSettingExportVo;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;
import org.junit.Test;

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
