package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncepu.util.JsonUtils.JsonUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc JSON测试工具类
 * @date 2022/10/13 9:22
 */
public class JSONUtilsTest {
    @Test
    public void test() {
        JSONObject result = JSONObject.parseObject("{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"fphm\":\"23952000000010792710\",\"fpqqlsh\":\"95952023081812191065931874935061\",\"kprq\":\"2023-08-18 12:30:55\"}}");
        result.getJSONObject("data").put("pdf", "1111111111111");
        PrintUtils.println(JSONObject.toJSONString(result));
    }

    @Test
    public void getJSONArrayTest() {
        JSONArray r1 = JsonUtils.getJSONArray("[{\"workUnitName\":\"贵州省人民医院\"},{\"workUnitName\":\"清镇市护幼保健院\"},{\"workUnitName\":\"贵州省骨科医院\"},{\"workUnitName\":\" 贵阳市南明区医院\"}]");
        JSONArray r2 = JsonUtils.getJSONArray("");
        JSONArray r3 = JsonUtils.getJSONArray("1");
        PrintUtils.println(r1);
        PrintUtils.println(r2);
        PrintUtils.println(r3);
    }
}
