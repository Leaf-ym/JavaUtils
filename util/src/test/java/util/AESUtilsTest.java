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
    public void batchEncryptTest() {
        // 批量加密
        List<String> list = ArrayUtils.toList("15907770699", "18930177536");
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
        List<String> list = ArrayUtils.toList("6LBDulmb2TpOB5kHBB5qFg==", "IIVLN4dTG16Z0IPAKZZXHA==");
        List<String> originList = new ArrayList<>();
        List<String> decryptList = new ArrayList<>();
        for (String str : list) {
            originList.add("'" + str + "'");
            decryptList.add("'" + AESUtils.decrypt(str) + "'");
        }
        PrintUtils.println(originList);
        PrintUtils.println(decryptList);
    }
}