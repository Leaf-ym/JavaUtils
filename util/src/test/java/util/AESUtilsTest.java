package util;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.util.*;
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
        List<String> list = ArrayUtils.toList("13570375286", "142402198908236624");
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
        List<String> list = ArrayUtils.toList("TmdNnxFd4byYteopxTVjHw==", "QLQab30mvPdMfa1TquVU96Oy_gOx2G95JMqg1CpnY5I=", "Sxg__6lMESCa_JhiP4vEJQ==");
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
        String path = "C:\\Users\\Administrator\\Desktop\\cna_member_exposed_temp.xlsx";
        String outPath = "C:\\Users\\Administrator\\Desktop\\cna_member_exposed_temp1.xlsx";
        JSONObject result = EasyExcelUtils.readFile(path);
        // 数据列表
        List<List<String>> dataList = new ArrayList<>();
        List<Map<Integer, Object>> list = (List<Map<Integer, Object>>)result.get("data");
        for (Map<Integer, Object> map : list) {
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
}