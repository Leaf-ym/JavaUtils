package com.ncepu.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class PlayUtil {

    // 自定义主域名
    public static final String MAIN_DOMAIN = "zhhlxh.org.cn";
    // 系统自带
    public static final String DEFAULT_DOMAIN = "myqcloud.com";
    // 主域名防盗链
    private static final String RANDOM_KEY_MAIN = "INsCUOhQrBpJpmu0sLHu";
    // 限制访问IP数量
    private static final int RLIMIT = 3;
    private static final String SecretId = "AKIDDHB0icD3d4MYaVFv74qocIpBJHOMojD5";
    private static final String SecretKey = "xjL9nGl4CWegCXleMsEOTIvb7h7bua4Q";
    // 地域节点
    private static final String ENDPOINT = "vod.tencentcloudapi.com";

    public static void main(String[] args) {
//        String originUrl = "https://mpa.nurseh.com/68b740f0vodcq1259373358/34716eba5285890798565163383/ChJnubiRZk8A.mp4";
//        // 超时时间戳
//        System.out.println(getSafeUrl(originUrl, 20, "0"));

//        List<ClassTypeModel> list = getAllClasses("{}");
//        for (ClassTypeModel classTypeModel : list
//        ) {
//            System.out.println(JSONObject.toJSONString(classTypeModel));
//        }

//        getSignForFront();
    }

    /**
     * 根据原始链接，返回安全链接
     *
     * @param originUrl 原始视频地址
     * @param minutes   地址有效时间，可以默认俩小时
     * @param exper     0或者空表示不试看
     * @return
     */
    public static String getSafeUrl(String originUrl, int minutes, String exper) {
        // 参数准备
        String dir = getDir(originUrl);
        String t = getEndTime(minutes);
        String us = CommonUtil.randomLOWUUID().substring(0, 10);
        String sign = CommonUtil.MD5Encode(RANDOM_KEY_MAIN + dir + t + exper + RLIMIT + us);
        StringBuilder sb = new StringBuilder();
        return sb.append(originUrl)
                .append("?t=")
                .append(t)
                .append("&exper=")
                .append(exper)
                .append("&rlimit=")
                .append(RLIMIT)
                .append("&us=")
                .append(us)
                .append("&sign=")
                .append(sign).toString();
    }

    /**
     * 获取dir
     *
     * @param originUrl
     * @return
     */
    public static String getDir(String originUrl) {
        if (originUrl.contains(MAIN_DOMAIN)) {
            return originUrl.substring(originUrl.indexOf(MAIN_DOMAIN) + MAIN_DOMAIN.length(), originUrl.lastIndexOf("/") + 1);
        } else {
            return originUrl.substring(originUrl.indexOf(DEFAULT_DOMAIN) + DEFAULT_DOMAIN.length(), originUrl.lastIndexOf("/") + 1);
        }
    }

    /**
     * 获取地址失效时间节点时间戳
     *
     * @param minutes
     * @return
     */
    public static String getEndTime(int minutes) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        date = calendar.getTime();
        return Long.toHexString(date.getTime() / 1000);
    }

}
