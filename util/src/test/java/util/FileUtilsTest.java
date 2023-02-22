package util;

import com.ncepu.util.CollectionUtils;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;
import com.ncepu.util.FileUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 文件工具类测试类
 * @date 2022/8/2 9:12
 */
public class FileUtilsTest {

    @Test
    public void getStrSetTest() {
        // DD20221007202032501
        String p1 = "DD202211\\d{11}";
        String out = "";
        Set<String> allResult = new HashSet<>();
        File dir = new File("D:\\logs");
        File[] files = dir.listFiles();
        for (File file : files) {
            Set<String> result = FileUtils.getStrSet(p1, file);
            allResult.addAll(result);
        }
        List<List<String>> allList = new ArrayList<>();
        for (String str : allResult) {
            List<String> list = new ArrayList<>();
            list.add(str);
            allList.add(list);
        }
        List<List<String>> headList = new ArrayList<>();
        List<String> docNoList = new ArrayList<>();
        docNoList.add("订单号");
        headList.add(docNoList);
        EasyExcelUtils.exportFile(allList, headList, "D:\\" + System.currentTimeMillis() + ".xlsx");
    }

    @Test
    public void urlEncodeTest() {
        String result = FileUtils.urlEncode("翁一茗");
        PrintUtils.println(result);
    }

    @Test
    public void urlDecodeTest() {
        String result = FileUtils.urlDecode("https://nursevideo.oss-cn-beijing.aliyuncs.com/meet/efa66bbaff0c40bc1d004f32707ede991659946068472.1%20%E2%80%9C%E4%B8%AD%E5%8D%8E%E6%8A%A4%E7%90%86%E5%AD%A6%E4%BC%9A%E5%88%9B%E6%96%B0%E5%8F%91%E6%98%8E%E5%A5%96%E2%80%9D%20--%20%E4%B8%80%E7%A7%8D%E5%A4%9A%E5%8A%9F%E8%83%BD%E6%99%BA%E8%83%BD%E5%8A%A9%E8%A1%8C%E5%99%A8%20%20%E9%87%91%E7%8F%A0%E8%8B%91%E8%BA%AB%E4%BB%BD%E8%AF%81");
        PrintUtils.println(result);
    }

    @Test
    public void getTempDirPathTest() {
        String result = FileUtils.getTempDirPath();
        PrintUtils.println(result);
    }

    @Test
    public void getFileInfoTest() {
        String path = "D:\\zip\\zip.txt";
        String result = FileUtils.getFileInfo(path);
        PrintUtils.println(result);
    }

    @Test
    public void getFileSuffixTest() {
        String result1 = FileUtils.getFileSuffix("123.txt");
        Assert.assertEquals("txt", result1);
        String result2 = FileUtils.getFileSuffix("123.");
        Assert.assertEquals("", result2);
        String result3 = FileUtils.getFileSuffix(".");
        Assert.assertEquals("", result3);
        String result4 = FileUtils.getFileSuffix(".123");
        Assert.assertEquals("123", result4);
        String result5 = FileUtils.getFileSuffix("123");
        Assert.assertEquals("", result5);
        String result6 = FileUtils.getFileSuffix("终版PDF 气道打开体位自动调节装置2022.08.08 .pdf");
        Assert.assertEquals("pdf", result6);
    }

    @Test
    public void downloadNetImageTest() {
        String basePath = "D:/";
        String url1 = "https://nursevideo.oss-cn-beijing.aliyuncs.com/meet/efa66bbaff0c40bc1d004f32707ede991659946068472.1%20%E2%80%9C%E4%B8%AD%E5%8D%8E%E6%8A%A4%E7%90%86%E5%AD%A6%E4%BC%9A%E5%88%9B%E6%96%B0%E5%8F%91%E6%98%8E%E5%A5%96%E2%80%9D%20--%20%E4%B8%80%E7%A7%8D%E5%A4%9A%E5%8A%9F%E8%83%BD%E6%99%BA%E8%83%BD%E5%8A%A9%E8%A1%8C%E5%99%A8%20%20%E9%87%91%E7%8F%A0%E8%8B%91%E8%BA%AB%E4%BB%BD%E8%AF%81";
        String path1 = basePath + "图片" + System.currentTimeMillis() + "." + FileUtils.getFileSuffix(url1);
        FileUtils.downloadNetworkFile(url1, path1);
        String url2 = "https://course.zhhlxh.org.cn/d7e50afevodcq1301348044/7b904d5d387702304179295768/MXfr9Xxfkf8A.mp4";
        String path2 = basePath + "视频" + System.currentTimeMillis() + "." + FileUtils.getFileSuffix(url2);
        FileUtils.downloadNetworkFile(FileUtils.getSafeUrl(url2, 120, ""), path2);
    }

    @Test
    public void zipFileTest() {
        //String path = "https://nurseapp.oss-cn-beijing.aliyuncs.com/h5/manage/2022/08/01/fcd45eb9cd26d30250c8237ef2f150631659324759355.jpeg";
        String path = "D:\\zip";
        try {
            FileUtils.zipFile(new File(path), "zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFileEncodeTest() {
        String encode = FileUtils.getFileEncode("D:\\recources\\encode.txt");
        PrintUtils.println(encode);
    }

    @Test
    public void deleteFilesTest() {
        String path = "D:\\recources";
        Boolean result = FileUtils.deleteFiles(path);
        PrintUtils.println(result);
    }

    @Test
    public void getProjectRootDirTest() {
        String result = FileUtils.getProjectRootDir();
        PrintUtils.println(result);
    }

    @Test
    public void appendToTest() {
        // /home在C:\home中
        String path1 = "D:\\400兆word文档-append.docx";
        String path2 = "D:\\400兆word文档 - 副本.docx";
        for (int i = 0; i < 50; i++) {
            String result = FileUtils.readFrom(path2);
            FileUtils.appendTo(result, path1);
        }
    }

    @Test
    public void writeToTest() {
        // /home在C:\home中
        String path1 = "D:\\400兆word文档-write.docx";
        String path2 = "D:\\400兆word文档 - 副本.docx";
        String result = FileUtils.readFrom(path2);
        FileUtils.writeTo(result, path1);
    }

    @Test
    public void readFromTest() {
        // /home在C:\home中
        String path = "/home/out1153.txt";
        String result = FileUtils.readFrom(path);
        PrintUtils.println(result);
    }

    @Test
    public void mkdirs() {
        String path = "D:/mkdir/1/2/3";
        Boolean result = FileUtils.mkdirs(path);
        PrintUtils.println(result);
    }
}
