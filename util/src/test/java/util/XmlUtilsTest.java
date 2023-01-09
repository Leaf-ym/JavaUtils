package util;

import com.ncepu.util.PrintUtils;
import com.ncepu.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc XML文档工具测试类
 * @date 2022/10/11 18:09
 */
public class XmlUtilsTest {

    @Test
    public void testGetDocumentByStr() {
        // UTF-8，GB2312
        String xmlStr = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\" ?> \n" +
                "<TX> \n" +
                "  <REQUEST_SN>请求序列码</REQUEST_SN> \n" +
                "  <CUST_ID>商户号</CUST_ID> \n" +
                "  <TX_CODE>5W1004</TX_CODE> \n" +
                "  <RETURN_CODE>返回码</RETURN_CODE> \n" +
                "  <RETURN_MSG>返回码说明</RETURN_MSG> \n" +
                "  <LANGUAGE>CN</LANGUAGE> \n" +
                "  <TX_INFO> \n" +
                "    <ORDER_NUM>订单号</ORDER_NUM> \n" +
                "    <PAY_AMOUNT>支付金额</PAY_AMOUNT> \n" +
                "    <AMOUNT>退款金额</AMOUNT> \n" +
                "    <REM1>备注1</REM1> \n" +
                "    <REM2>备注2</REM2> \n" +
                "  </TX_INFO>   \n" +
                "</TX> \n";
        String encoding = "GB2312";
        Document doc = XmlUtils.getDocumentByStr(xmlStr, encoding);
        PrintUtils.println(XmlUtils.getStringValue(doc, "CUST_I1D"));
        PrintUtils.println(XmlUtils.getStringValue(doc, "TX_CODE"));
        PrintUtils.println(XmlUtils.getStringValue(doc, "RETURN_CODE"));
        PrintUtils.println(XmlUtils.getStringValue(doc, "RETURN_MSG"));
        PrintUtils.println(XmlUtils.getStringValue(doc, "ORDER_NUM"));
        PrintUtils.println(XmlUtils.getStringValue(doc, "TX_INFO"));
        PrintUtils.println(XmlUtils.getStringValue(doc, "TX"));
    }
}
