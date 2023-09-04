package util;

import com.ncepu.util.DatabaseUtils.example.UserModel;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.algorithm.SimHash;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 算法工具测试类
 * @date 2023/09/02 10:40
 */
public class AlgorithmUtilsTest {
    @Test
    public void testSimHashStr() throws IOException {
        long startTime = System.currentTimeMillis();
        String str1 = "最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen），得到相似度最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）。";
        String str2 = "最后：先最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）取两长度的最大值maxLen，用1-（需要操axLen），得到相似度。";
        SimHash hash1 = new SimHash(str1, 64);
        SimHash hash2 = new SimHash(str2, 64);
        PrintUtils.println("字符串：" + str1);
        PrintUtils.println("字符串：" + str2);
        PrintUtils.println("海明距离为：" + hash1.hammingDistance(hash2));
        PrintUtils.println("相似度为：" + hash1.similarity(hash2));
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (float) (endTime - startTime) / 1000 + "s");
    }
    @Test
    public void testSimHash() throws IOException {
        long startTime = System.currentTimeMillis();
        String str1 = "11111111111112222222222";
        String str2 = "11111111111112222222222";
        String str3 = "马马马马马马马马马马马马马";
        String str4 = "最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen），得到相似度最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）。";
        String str5 = "最后：先最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）取两长度的最大值maxLen，用1-（需要操axLen），得到相似度。";
        String str6 = "牛牛牛牛牛牛牛牛牛牛牛牛牛";
        UserModel userModel1 = new UserModel();
        userModel1.setId("1");
        userModel1.setContent(str1);
        UserModel userModel2 = new UserModel();
        userModel2.setId("2");
        userModel2.setContent(str2);
        UserModel userModel3 = new UserModel();
        userModel3.setId("3");
        userModel3.setContent(str3);
        UserModel userModel4 = new UserModel();
        userModel4.setId("4");
        userModel4.setContent(str4);
        UserModel userModel5 = new UserModel();
        userModel5.setId("5");
        userModel5.setContent(str5);
        UserModel userModel6 = new UserModel();
        userModel6.setId("6");
        userModel6.setContent(str6);
        List<UserModel> list = new ArrayList<>();
        list.add(userModel1);
        list.add(userModel2);
        list.add(userModel3);
        list.add(userModel4);
        list.add(userModel5);
        list.add(userModel6);
        Set<String> hasHandle = new HashSet<>();
        for (UserModel s1 : list) {
            SimHash hash1 = new SimHash(s1.getContent(), 64);
            //List characters1 = new ArrayList();
            //List characters2 = new ArrayList();
            //characters1 = hash1.subByDistance(hash1, 3);
            for (UserModel s2 : list) {
                if (hasHandle.contains(s1.getId() + "-" + s2.getId()) || hasHandle.contains(s2.getId() + "-" + s1.getId())) {
                    continue;
                }
                hasHandle.add(s1.getId() + "-" + s2.getId());
                hasHandle.add(s2.getId() + "-" + s1.getId());
                if (!s1.getId().equals(s2.getId())) {
                    SimHash hash2 = new SimHash(s2.getContent(), 64);
                    int dishm = hash1.hammingDistance(hash2);
                    /*if (dishm <= 5) {
                        System.out.println("s1.id:" + s1.getId() + " s2.id:" + s2.getId() + " dishm:" + dishm);
                    }*/
                    System.out.println("s1.id:" + s1.getId() + " s2.id:" + s2.getId() + " dishm:" + dishm);
                    PrintUtils.println("字符串：" + s1.getContent());
                    PrintUtils.println("字符串：" + s2.getContent());
                    PrintUtils.println("相似度为：" + hash1.similarity(hash2));
                    //}
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (float) (endTime - startTime) / 1000 + "s");
    }
}
