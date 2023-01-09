package util;

import com.ncepu.util.HttpUtils;
import com.ncepu.util.InterfaceUtils;
import com.ncepu.util.PrintUtils;
import org.apache.commons.httpclient.URIException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 接口工具测试类
 * @date 2022/10/13 10:18
 */
public class InterfaceUtilsTest {
    @Test
    public void testCreateToken() {
        String appId = "6077587c-404d-4b41-b631-5cb9ba22f9aa";
        String secretKey = "bgkqhkig9w0baqefaaocaq8amiibcgkcaqeazxon";
        String secretKey1 = "BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzxOn";
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("GZDW", "北京人民医院");
            put("SZXHQC", "中国医学会");
            put("YDDHHM", "13513516666");
            put("ZWXM", "王五");
            put("XB", "男");
        }};
        long timestamp = 1662105872587L;
        String token1 = InterfaceUtils.createToken(appId, secretKey, timestamp, params);
        String token2 = InterfaceUtils.createToken(appId, secretKey1, timestamp, params);
        PrintUtils.println(token1);
        PrintUtils.println(token2);
    }

    @Test
    public void testParams() throws UnsupportedEncodingException, URIException {
        Map<String, Object> parameterMap = new HashMap<>(1 << 2);
        String ZWXM = "王五1";
        String SZXHQC = "中国医学会";
        String YDDHHM = "13513516666";
        String GZDW = "北京人民医院";
        String XB = "男";
        String baseUrl = "/scimall_member/userInfo/addUserInfo";
        String appid = "6077587c-404d-4b41-b631-5cb9ba22f9aa";
        String secretkey = "BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzxOn";
        long timestamp = System.currentTimeMillis();
        long firstPaymentDate = 1662027690070L;
        parameterMap.put("SZXHQC", SZXHQC);
        parameterMap.put("ZWXM", ZWXM);
        parameterMap.put("YDDHHM", YDDHHM);
        parameterMap.put("GZDW", GZDW);
        parameterMap.put("appid", appid);
        parameterMap.put("XB", XB);
        parameterMap.put("secretkey", secretkey);
        parameterMap.put("timestamp", String.valueOf(timestamp));
        parameterMap.put("firstPaymentDate", String.valueOf(firstPaymentDate));
        String token = InterfaceUtils.createToken(appid, secretkey, timestamp, parameterMap);
        String requestUrl = "http://localhost:8898" + baseUrl + "?" +
                "appid=" + appid + "&timestamp=" + timestamp +
                "&ZWXM=" + ZWXM + "&GZDW=" + GZDW +
                "&YDDHHM=" + YDDHHM + "&SZXHQC=" + SZXHQC +
                "&XB=" + XB +
                "&firstPaymentDate=" + firstPaymentDate +
                "&token=" + token;
        String result = HttpUtils.post(requestUrl, parameterMap, HttpUtils.ContentTypeEnum.APPLICATION_JSON);
        System.out.println(result);
    }
}
