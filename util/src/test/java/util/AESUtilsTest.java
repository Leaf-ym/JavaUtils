package util;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.util.*;
import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc AES加解密工具类测试
 * @date 2022/6/8 11:12
 */
public class AESUtilsTest {

    @Test
    public void batchEncryptTest() {
        // 批量加密
        List<String> list = ArrayUtils.toList("18089262910", "110221198312205027");
        List<String> originList = new ArrayList<>();
        List<String> encryptList = new ArrayList<>();
        for (String str : list) {
            originList.add("'" + str + "'");
            encryptList.add("'" + AESUtils.encrypt(str) + "'");
        }
        PrintUtils.println(originList);
        PrintUtils.println(encryptList);
    }

    @Test
    public void decryptTest() {
        // 批量解密
        List<String> list = ArrayUtils.toList("GIVhrugwNuqPRhB-JiJ5WQ==", "Ajxnb8a1_aWcxtdCSavccIGxbxIQeZUI0epTiZQB0fE=", "JiEjsc0t5d4eIgTQ4bZOYvIqeiuDg-3rT0MJI5r6EjE=");
        List<String> originList = new ArrayList<>();
        List<String> decryptList = new ArrayList<>();
        for (String str : list) {
            originList.add("'" + str + "'");
            decryptList.add("'" + AESUtils.decrypt(str) + "'");
        }
        PrintUtils.println(originList);
        PrintUtils.println(decryptList);
    }

    /**
     * 批量解密Excel文件
     *
     * @author wengym
     *
     * @date 2023/9/1 15:39
     *
     * @return void
     */
    @Test
    public void batchDecryptExcelFile() {
        String path = "C:\\Users\\Administrator\\Desktop\\北京中西医图像数据处理.xlsx";
        String outPath = "C:\\Users\\Administrator\\Desktop\\北京中西医图像数据处理1.xlsx";
        JSONObject result = EasyExcelUtils.readFile(path);
        // 数据列表
        List<List<String>> dataList = new ArrayList<>();
        List<Map<Integer, Object>> list = (List<Map<Integer, Object>>)result.get("data");
        for (int j = 0; j < list.size(); j++) {
            Map<Integer, Object> map = list.get(j);
            List<String> data = new ArrayList<>();
            for (Integer i : map.keySet()) {
                if (map.get(i) == null) {
                    data.add("");
                    continue;
                }
                data.add(AESUtils.decrypt(map.get(i) + ""));
            }
            dataList.add(data);
        }
        // 头列表
        List<List<String>> headList = new ArrayList<>();
        Map<Integer, String> head = (Map<Integer, String>)result.get("head");
        for (Integer i : head.keySet()) {
            headList.add(new ArrayList<String>() {{
                add(head.get(i) + "");
            }});
        }
        // 导出
        EasyExcelUtils.exportFile(dataList, headList, outPath);
    }

    /**
     * 批量加密Excel文件
     *
     * @author wengym
     *
     * @date 2023/10/20 9:12
     *
     * @return void
     */
    @Test
    public void batchEncryptExcelFile() {
        String path = "C:\\Users\\Administrator\\Desktop\\导出通过学会审核的工作委员会数据.xlsx";
        String outPath = "C:\\Users\\Administrator\\Desktop\\导出通过学会审核的工作委员会数据1.xlsx";
        JSONObject result = EasyExcelUtils.readFile(path);
        //
        List<String> encrypt = DatabaseUtils.asList("身份证号", "小写身份证号", "手机号");
        // 头列表
        List<List<String>> headList = new ArrayList<>();
        Map<Integer, String> head = (Map<Integer, String>)result.get("head");
        for (Integer i : head.keySet()) {
            headList.add(new ArrayList<String>() {{
                add(head.get(i) + "");
            }});
        }
        // 数据列表
        List<List<String>> dataList = new ArrayList<>();
        List<Map<Integer, Object>> list = (List<Map<Integer, Object>>)result.get("data");
        for (int j = 0; j < list.size(); j++) {
            Map<Integer, Object> map = list.get(j);
            List<String> data = new ArrayList<>();
            for (Integer i : map.keySet()) {
                if (map.get(i) == null) {
                    data.add("");
                    continue;
                }
                if (encrypt.contains(headList.get(i).get(0))) {
                    // 在加密列表中则加密
                    data.add(AESUtils.encrypt(map.get(i) + ""));
                } else {
                    data.add(map.get(i) + "");
                }
            }
            dataList.add(data);
        }
        // 导出
        EasyExcelUtils.exportFile(dataList, headList, outPath);
    }
}