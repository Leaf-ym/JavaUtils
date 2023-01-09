package util;

import com.ncepu.util.EncodingDetect;
import com.ncepu.util.FileEncodeUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

public class FileEncodeUtilsTest {
    @Test
    public void testGetFileEncode() {
        String filePath = "C:\\Users\\Administrator\\Desktop\\新建文本文档.txt";
        // 缺点是不稳定，因为利用的是统计学原理，文件内容太短的话，判断会有偏差，需引入四个jar包
        String result = FileEncodeUtils.getFileEncode(filePath);
        PrintUtils.println(result);
        // 缺点是java文件比较大，但是判断较为准确
        String result2 = EncodingDetect.getJavaEncode(filePath);
        PrintUtils.println(result2);
    }
}
