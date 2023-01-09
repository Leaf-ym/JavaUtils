package com.ncepu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.util.Date;

/**
 * Token工具类
 *
 * @author wengym
 * @date 2021-11-12
 */
public class JwtTokenUtils {

    // 过期时间，单位毫秒 token有效时效24小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    // 内部加密key
    private static final String KEY = "29deAqdpI930Qr40129";

    /***
     *
     * @param token 密文
     *
     * @desc 用MD5算法对字符串进行加密
     *
     * @author wengym
     *
     * @date 2021/11/12 10:03
     *
     * @return java.lang.String
     *
     */
    public static String decode(String token) {
        // 获取 token 中的 user id
        String uuid = "";
        String userId = "";
        String organize = "";
        String password = "";
        String environment = "";
        String expire = "";
        String userName = "";
        String sysId = "";
        String algorithm = "";
        try {
            uuid = JWT.decode(token).getId();
            userId = JWT.decode(token).getAudience().get(0);
            organize = JWT.decode(token).getIssuer();
            password = JWT.decode(token).getClaim("sysId").asString();
            environment = JWT.decode(token).getSubject();
            expire = JWT.decode(token).getExpiresAt().toString();
            userName = JWT.decode(token).getClaim("userName").asString();
            sysId = JWT.decode(token).getClaim("sysId").asString();
            algorithm = JWT.decode(token).getAlgorithm();
        } catch (JWTDecodeException j) {
            throw new RuntimeException("token过期，请重新登录");
        }
        return uuid+"-"+userId+"-"+organize+"-"+password+"-"+userName+"-"+environment+"-"+sysId+"-"+expire+"-"+algorithm;
    }

    /**
     * 生成token
     * token和请求IP进行绑定，如果IP发生改变，则认为过期
     *
     * @param userId   用户ID
     * @param organize 所属组织
     * @param password 密码
     * @param userName 用户姓名
     * @return
     */
    public static String getToken(String userId, String organize, String password, String userName, String environment, String sysId) {
        String token = JWT.create()
                // 编号
                .withJWTId(CommonUtil.randomLOWUUID())
                // 签发人
                .withIssuer(organize)
                // 受众
                .withAudience(userId)
                // 主题
                .withSubject(environment)
                // 过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .withClaim("userName", userName)
                .withClaim("sysId", sysId)
                .sign(Algorithm.HMAC384(password));
        return token;
    }

    public static void main(String[] args) {
        String token = JwtTokenUtils.getToken("wengym", "440000", "123456", "翁一茗", "Web", "440000");
        System.out.println("提起token的基本信息：" + JwtTokenUtils.decode(token));
    }
}
