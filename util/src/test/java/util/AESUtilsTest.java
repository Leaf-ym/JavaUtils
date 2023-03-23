package util;

import com.ncepu.util.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc AES加解密工具类测试
 * @date 2022/6/8 11:12
 */
public class AESUtilsTest {
    @Test
    public void batchEncryptTest() throws IOException {
        // 批量加密
        List<String> list = ArrayUtils.toList("13359588568");
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
        String decryptStr = AESUtils.decrypt("cSLKg1aMwRwXur7kEmvQBw==");
        PrintUtils.println(decryptStr);
    }
}