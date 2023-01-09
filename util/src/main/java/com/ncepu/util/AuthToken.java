package com.ncepu.util;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.TreeMap;

@ToString
public class AuthToken {

    @Getter
    private String token;

    private long createTime;


    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public static AuthToken createToken(String appId, String secretKey, long createTime, String baseUrl, Map<String, String> params) {
        Map<String, Object> sortedMap = new TreeMap<>();
        if (params != null) {
            params.remove("appid");
            params.remove("secretkey");
            sortedMap.putAll(params);
        }

        sortedMap.put("appid", appId);

        StringBuilder sn = new StringBuilder();
        sortedMap.forEach((k, v) -> {
            sn.append("&");
            sn.append(k);
            sn.append("=");
            sn.append(v);
        });

        sn.append("&secretkey=").append(secretKey);
        sn.deleteCharAt(0);
        // 两次md5加密，每次加密都要转换为小写字母
        String token =  Md5Utils.toMD5(Md5Utils.toMD5(sn.toString().toLowerCase())).toLowerCase();
        return new AuthToken(token, createTime);
    }
}