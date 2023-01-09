package com.ncepu.util;

import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 接口调用工具
 * @date 2022/10/13 10:00
 */
public class InterfaceUtils {

    /**
     * 创建接口验证token，用于验证参数信息的完整性，和验证用户的接口调用权限
     *
     * @param secretKey
     *
     * @param appId
     *
     * @param createTime
     *
     * @param params
     *
     * @author wengym
     *
     * @date 2022/10/13 10:59
     *
     * @return java.lang.String
     */
    public static String createToken(String appId, String secretKey, long createTime, Map<String, Object> params) {
        if (params != null) {
            params.remove("appid");
            params.remove("secretkey");
        }
        String httpQueryStr = getHttpQueryParams(params, SortUtils.ASC);
        StringBuilder sn = new StringBuilder(httpQueryStr);
        sn.append("&appid=").append(appId);
        sn.append("&timestamp=").append(createTime);
        sn.append("&secretkey=").append(secretKey);
        // 两次md5加密，每次加密都要转换为小写字母
        String token =  Md5Utils.toMD5(Md5Utils.toMD5(sn.toString().toLowerCase())).toLowerCase();
        return token;
    }

    /**
     * 获取指定排序的HttpQuery形式的参数
     * GZDW=北京人民医院&SZXHQC=中国医学会&XB=男&YDDHHM=13513516666&ZWXM=王五
     *
     * @param orderBy
     *
     * @author wengym
     *
     * @date 2022/10/13 11:03
     *
     * @return java.lang.String
     */
    public static String getHttpQueryParams(Map<String, Object> params, String orderBy) {
        Map<String, Object> sortedMap = SortUtils.getTreeMap(params, orderBy);
        StringBuilder sn = new StringBuilder();
        sortedMap.forEach((k, v) -> {
            sn.append("&");
            sn.append(k);
            sn.append("=");
            sn.append(v);
        });
        sn.deleteCharAt(0);
        return sn.toString();
    }
}
