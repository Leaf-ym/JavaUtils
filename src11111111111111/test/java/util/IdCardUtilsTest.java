package util;

import com.ncepu.util.DateUtils;
import com.ncepu.util.IdCardUtils;
import com.ncepu.util.PrintUtils;

/**
 * @author wengym
 * @version 1.0
 * @desc 身份证工具测试类
 * @date 2022/4/27 9:14
 */
public class IdCardUtilsTest {
    public static void main(String[] args) {
        String idCard = "44098219931009277x";
        PrintUtils.println(IdCardUtils.getGenderByIdCard(idCard));
        PrintUtils.println(IdCardUtils.getBirthByIdCard(idCard));
        PrintUtils.println(IdCardUtils.getMonthByIdCard(idCard));
        PrintUtils.println(IdCardUtils.getDayByIdCard(idCard));
        PrintUtils.println(IdCardUtils.getAgeByIdCard(idCard));
        PrintUtils.println(IdCardUtils.getConstellationById(idCard));
        PrintUtils.println(IdCardUtils.getZodiacById(idCard));
        PrintUtils.println(IdCardUtils.getChineseEraById(idCard));
        PrintUtils.println(IdCardUtils.getProvinceByIdCard(idCard));
        PrintUtils.println(IdCardUtils.getBirthByIdCard(idCard, DateUtils.FORMAT_YYYY_MM_DD_Z));
    }
}
