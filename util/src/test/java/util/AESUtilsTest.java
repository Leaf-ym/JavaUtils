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
        List<String> list = ArrayUtils.toList("450103197807080540", "18968187955");
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
        List<String> list = ArrayUtils.toList("MyR2XAxvBxsaF46BnRyGLHkTsXDD8B3hBsEX3X3kThY=","eFAaaUjM99EWPeg-P6913Ku0bEhQx54qlD6q0kors7k=","5KqkFqKNoDu7pKb2rSXDXQ==","0uQOt3FrAAYyWfHtehHKSw==","XbgeFM9heJ59xOuI5nSxow==","QAz0Pw_3ss6wzZcHWUYB2Q==","jeTbhTFLebWXDZHQZvArpg==","lgoyveO_qqxmjgQvd3tJYQ==","Kf5uEZEU6qZF9gvNpJOz3w==","8w4zLb4iL6-LfX6-YbLreA==","DF2Bsbee_trjG0kSUK_YBA==","2_M1rpuLFR2kIKI8T1GF0A==","UQDNzJxor5L3GvGtZ24Xqw==");
        List<String> originList = new ArrayList<>();
        List<String> decrypttList = new ArrayList<>();
        for (String str : list) {
            originList.add("'" + str + "'");
            decrypttList.add("'" + AESUtils.decrypt(str) + "'");
        }
        PrintUtils.println(originList);
        PrintUtils.println(decrypttList);
    }
}