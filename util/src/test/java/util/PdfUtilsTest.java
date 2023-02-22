package util;

import com.ncepu.util.PdfUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc PDF文件工具测试类
 * @date 2023/2/2 9:00
 */
public class PdfUtilsTest {
    @Test
    public void testPdfRemoveWatermark() {
        String filePath = "D:\\测试\\pdf\\1.pdf";
        PdfUtils.pdfRemoveWatermark(filePath);
    }
}
