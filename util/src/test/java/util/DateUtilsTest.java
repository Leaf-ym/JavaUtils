package util;

import com.ncepu.util.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author wengym
 * @version 1.0
 * @desc 时间工具测试类
 * @date 2022/6/15 13:36
 */
public class DateUtilsTest {
    @Test
    public void testGetDateRange() {
        Set<String> s1 = DateUtils.getDateRange("2023-10-27", "2023-10-27", DateUtils.FORMAT_YYYY_MM_DD);
        PrintUtils.println(s1);
    }

    @Test
    public void testDateDiff() {
        long second = DateUtils.getDiffSecondOfDateTime("2023-10-08", "2023-10-09", DateUtils.FORMAT_YYYY_MM_DD);
        PrintUtils.println(second / 60);
        PrintUtils.println(second / 60 / 60);
    }

    @Test
    public void testCheckDateWithNow() {
        int r1 = DateUtils.checkDateWithNow("2023-06-05 14:39:20", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
        int r2 = DateUtils.checkDateWithNow("2024-06-05 14:39:20", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
        int r3 = DateUtils.checkDateWithNow("2023-06-05", DateUtils.FORMAT_YYYY_MM_DD);
        PrintUtils.println(r1);
        PrintUtils.println(r2);
        PrintUtils.println(r3);
    }

    @Test
    public void testGetAfterDayDateStr() {
        String r1 = DateUtils.getAfterDayDateStr(DateUtils.FORMAT_YYYY_MM_DD, "2023-05-11", -11);
        PrintUtils.println(r1);
    }

    @Test
    public void testGetNowDateAfterSecondMonth() {
        String r1 = DateUtils.getNowDateAfterNumSecond(DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS, 30);
        PrintUtils.println(r1);
    }

    @Test
    public void testFormatDate() {
        String r1 = DateUtils.getNowDate(DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
        PrintUtils.println(r1);
        String r2 = DateUtils.formatDate(r1, DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS, DateUtils.FORMAT_YYYYMMDDHHMISS);
        PrintUtils.println(r2);
    }

    @Test
    public void testGetNowDate() {
        String r1 = DateUtils.getNowDate(DateUtils.FORMAT_YYYYMMDDHHMISS);
        PrintUtils.println(r1);
        String r2 = DateUtils.getNowDate(DateUtils.FORMAT_YYYY_MM_DD);
        PrintUtils.println(r2);
    }

    @Test
    public void getMonth() {
        Integer r1 = DateUtils.getMonth("2022-02-25 12:05:05", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
        PrintUtils.println(r1);
        int r4 = DateUtils.getNowMonth();
        PrintUtils.println(r4);
    }

    @Test
    public void dealTime() {
        String time1 = "2022-10-22 07:48:55,2022-10-22 14:03:43,2022-10-22 16:05:19,2022-10-22 16:06:24";
        String time2 = "5时43分,1时59分,0时1分,0时30分";
        List<String> time1List = CollectionUtils.getList(StringUtils.split(time1, ","));
        List<String> time2List = CollectionUtils.getList(StringUtils.split(time2, ","));
        List<String> rightTime1List = new ArrayList<>();
        for (int i = 0; i < time1List.size(); i++) {
            int compare = DateUtils.compareTwoDateTime(time1List.get(i), "2022-10-22 13:00:00", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
            if (compare == 1) {
                break;
            }
            rightTime1List.add(time1List.get(i));
        }
        long seconds = DateUtils.getDiffSecondOfDateTime("2022-10-22 13:00:00", rightTime1List.get(0), DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS);
        long minutes = seconds / 60;
        PrintUtils.println(seconds);
        PrintUtils.println(minutes);
        Long totalMinutes = 0L;
        for (int i = 0; i < rightTime1List.size(); i++) {
            totalMinutes += getMinutes(time2List.get(i));
        }
        if (minutes < totalMinutes) {
            totalMinutes = minutes;
        }
        PrintUtils.println(totalMinutes);
        PrintUtils.println(totalMinutes / 120.0);
        PrintUtils.println(Math.ceil(totalMinutes / 120.0));
    }

    public Integer getMinutes(String time) {
        String hour = Pattern.compile("时.*分").matcher(time).replaceAll("");
        String minute = Pattern.compile(".*时").matcher(time).replaceAll("").replace("分", "");
        Integer minutes = (Integer.valueOf(hour) * 60) + Integer.valueOf(minute);
        PrintUtils.println(minutes);
        return minutes;
    }

    @Test
    public void getYesterdayDateStr() {
        String str = DateUtils.getYesterdayDateStr(DateUtils.FORMAT_YYYY_MM_DD);
        PrintUtils.println(str);
    }

    @Test
    public void getDateBackNumYear() {
        String result = DateUtils.getNowDateAfterNumYear(DateUtils.FORMAT_YYYY_MM_DD, 1);
        PrintUtils.println(result);
    }

    @Test
    public void testGetStartDate() {
        String result = DateUtils.getStartDate();
        PrintUtils.println(result);
    }

    @Test
    public void testGetEndDate() {
        String result = DateUtils.getEndDate(1);
        PrintUtils.println(result);
    }
    @Test
    public void testValidateDate() {
        // 闰年
        Boolean r1 = DateUtils.validateDate(2004, 2, 29);
        Assert.assertTrue(r1);
        Boolean r2 = DateUtils.validateDate(2020, 2, 29);
        Assert.assertTrue(r2);
        Boolean r3 = DateUtils.validateDate(2000, 2, 29);
        Assert.assertTrue(r3);
        // 平年
        Boolean r4 = DateUtils.validateDate(1900, 2, 29);
        Assert.assertFalse(r4);
        Boolean r5 = DateUtils.validateDate(1993, 2, 29);
        Assert.assertFalse(r5);
    }

    @Test
    public void testGetSecondTimestamp() {
        long r1 = DateUtils.getSecondTimestamp("2022-06-15", DateUtils.FORMAT_YYYY_MM_DD);
        long r2 = DateUtils.getSecondTimestamp("2022-06-16", DateUtils.FORMAT_YYYY_MM_DD);
        PrintUtils.println(r1);
        PrintUtils.println(r2);
        PrintUtils.println(r2-r1);
    }
}
