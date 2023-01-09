package util;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.model.NewUserBean;
import com.ncepu.model.UserDTO;
import com.ncepu.model.UserRegisterModel;
import com.ncepu.util.BeanUtils;
import com.ncepu.util.CollectionUtils;
import com.ncepu.util.HttpUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc HTTP工具测试类
 * @date 2022/8/19 14:40
 */
public class HttpUtilsTest {
    @Test
    public void testPost() {
        String url = "https://test.nursehealth.cn/meetapi/getMobileToken";
        Map<String, Object> params = new HashMap<>();
        params.put("environment", "H5");
        params.put("loginWay", 1);
        params.put("openid", null);
        params.put("password", "e10adc3949ba59abbe56e057f20f883e");
        params.put("sysType", "client");
        params.put("userId", "17325302081");
        Map<String, Object> headers = new HashMap<>();
        headers.put("sysId", "hjk");
        headers.put("version", "2.0.1");
        String content = HttpUtils.post(url, params, headers, HttpUtils.ContentTypeEnum.APPLICATION_JSON);
        PrintUtils.println(content);
        String token = JSONObject.parseObject(content).getJSONObject("data").getString("token");
        String url1 = "https://test.nursehealth.cn/meetapi/user/userInfo";
        headers.put("token", token);
        String content1 = HttpUtils.get(url1, new HashMap<>(), headers, null);
        PrintUtils.println(content1);

    }
}
