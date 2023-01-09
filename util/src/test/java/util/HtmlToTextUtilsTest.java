package util;

import com.ncepu.util.HtmlToTextUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc Html字符串工具测试类
 * @date 2022/10/11 13:35
 */
public class HtmlToTextUtilsTest {
    @Test
    public void testDelHtmlTags() {
        String str="<p>\n" +
                "    \r\t\n133      3\n" +
                "\t\n6464646&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"http://nursevideo.oss-cn-beijing.aliyuncs.com/meet/ueditor_1647422938171_te8tabvrk28.jpg\" title=\"1647422938171_te8tabvrk28.jpg\" alt=\"1647422938171_te8tabvrk28.jpg\"/>-131313213132131231<img src=\"http://nursevideo.oss-cn-beijing.aliyuncs.com/meet/ueditor_1647422944943_2epm1bfjondg.jpg\" title=\"1647422944943_2epm1bfjondg.jpg\" alt=\"1647422944943_2epm1bfjondg.jpg\"/>\n" +
                "</p>";
        PrintUtils.println(HtmlToTextUtils.deleteHtmlTags(str));
        PrintUtils.println(HtmlToTextUtils.deleteHtmlTagsIgnore(str, "img"));
    }
}
