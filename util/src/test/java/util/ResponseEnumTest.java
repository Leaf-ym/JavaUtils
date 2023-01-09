package util;

import com.ncepu.enums.ResponseEnum;
import com.ncepu.util.PrintUtils;

/**
 * @author wengym
 * @version 1.0
 * @desc 响应枚举测试类
 * @date 2022/4/24 11:23
 */
public class ResponseEnumTest {
    public static void main(String[] args) {
        ResponseEnum responseEnum = ResponseEnum.AUTH_FAIL;
        PrintUtils.println(responseEnum.toString());
    }
}
