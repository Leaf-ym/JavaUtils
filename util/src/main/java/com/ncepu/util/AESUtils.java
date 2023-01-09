package com.ncepu.util;

import com.ncepu.model.Constants;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AESUtils {

    private static final String CHAR_UTF_8 = "UTF-8";
    private static final String KEY = "CsfmEhNRzQRtODHu";
    private static final String IV = "CsfmEhNRzQRtODHu";

    /***
     *
     * 对Map做加解密处理
     *
     * @param queryMap
     * @param type
     *
     * @author wengym
     *
     * @date 2022/4/29 13:57
     *
     * @return java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    public static Map<String, Object> mapInit(Map<String, Object> queryMap, String type) {
        if (CommonUtil.isNullStr(queryMap)) {
            return null;
        }
        List<String> list = Constants.INCLUDE_USER_INFO;
        // 处理map中的数据
        dealMapData(queryMap, type, list);
        return queryMap;
    }

    /***
     *
     * 对Map做加解密处理
     *
     * @param queryMap
     * @param type
     *
     * @author wengym
     *
     * @date 2022/4/29 13:57
     *
     * @return java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    public static Map<String, Object> mapInit(Map<String, Object> queryMap, String type, List<String> list) {
        if (CommonUtil.isNullStr(queryMap)) {
            return null;
        }
        // 处理map中的数据
        dealMapData(queryMap, type, list);
        return queryMap;
    }

    /***
     *
     * 对Map中的数据进行加解密处理
     *
     * @param queryMap
     * @param type
     * @param list
     *
     * @author wengym
     *
     * @date 2022/4/29 14:02
     *
     * @return void
     *
     */
    private static void dealMapData(Map<String, Object> queryMap, String type, List<String> list) {
        for (String str : queryMap.keySet()) {
            if (!list.contains(str) || CommonUtil.isNullStr(queryMap.get(str))) {
                continue;
            }
            if (org.apache.commons.codec.binary.StringUtils.equals(type, Constants.PUT)) {
                // 加密
                queryMap.put(str, encrypt((String)queryMap.get(str)));
            } else {
                // 解密
                queryMap.put(str, decrypt((String)queryMap.get(str)));
            }
        }
    }

    /***
     *
     * 对列表model做加解密处理
     *
     * @param list 需要处理的列表model
     * @param type out：解密，put：加密
     *
     * @author wengym
     *
     * @date 2022/4/29 11:27
     *
     * @return java.util.List<java.lang.Object>
     *
     */
    public static <T> List<T> listInit(List<T> list, String type) {
        for (T model : list) {
            objectInit(model, type);
        }
        return list;
    }

    /***
     *
     * @param model 操作的对象 type 入库或者出库类型 out/put
     *
     * @desc 利用反射机制修改属性值，加解密参数
     *
     * @author yexiaowu
     *
     * @date 2020-09-23 09:24
     *
     * @return java.lang.Object
     *
     */
    public static Object objectInit(Object model, String type) {
        // 加密字段信息
        List<String> list = Constants.INCLUDE_USER_INFO;
        // 获取实体类的所有属性，返回Field数组
        dealModelData(model, type, list);
        return model;
    }

    /***
     *
     * @param model 需要进行加密的mode
     *
     * @param type 操作类型：OUT解密，PUT加密
     *
     * @param column_infos 需求进行加解密的字段列表
     *
     * @desc 对model中的某些字段进行加解密操作
     *
     * @author wengym
     *
     * @date 2021/12/20 10:08
     *
     * @return java.lang.Object
     *
     */
    public static Object objectInit(Object model, String type, List<String> column_infos) {
        // 加密字段信息
        List<String> list = column_infos;
        // 获取实体类的所有属性，返回Field数组
        dealModelData(model, type, list);
        return model;
    }

    /***
     *
     * 对model中的指定字段进行加解密处理
     *
     * @param model
     * @param type
     * @param list
     *
     * @author wengym
     *
     * @date 2022/4/29 11:54
     *
     * @return void
     *
     */
    private static void dealModelData(Object model, String type, List<String> list) {
        // 获取实体类的所有属性，返回Field数组
        Field[] field = model.getClass().getDeclaredFields();
        for (Field field1 : field) {
            field1.setAccessible(true);
            try {
                String name = field1.getName();
                if (!list.contains(name)) {
                    continue;
                }
                Class<?> fieldType = model.getClass().getDeclaredField(name).getType();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = model.getClass().getMethod("get" + name);
                // 调用getter方法获取属性值
                if (!CommonUtil.isNullStr(m.invoke(model)) && fieldType == String.class) {
                    String value = m.invoke(model) + Constants.COMMON_STR_EMPTY;
                    m = model.getClass().getMethod("set" + name, fieldType);
                    if (org.apache.commons.codec.binary.StringUtils.equals(type, Constants.PUT)) {
                        //入库加密赋值
                        m.invoke(model, encrypt(value));
                    } else {
                        //出库解密赋值
                        m.invoke(model, decrypt(value));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加密
     */
    public static String encrypt(String content) {
        try {
            byte[] b = encrypt(content.getBytes(CHAR_UTF_8), KEY.getBytes(CHAR_UTF_8), IV.getBytes(CHAR_UTF_8));
            if (null != b) {
                return Base64.getUrlEncoder().encodeToString(b);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 加密
     */
    public static String encrypt(String content, String key, String iv) {
        try {
            byte[] b = encrypt(content.getBytes(CHAR_UTF_8), key.getBytes(CHAR_UTF_8), iv.getBytes(CHAR_UTF_8));
            if (null != b) {
                return Base64.getUrlEncoder().encodeToString(b);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 加密
     */
    public static byte[] encrypt(byte[] content, byte[] keyBytes, byte[] iv) {
        try {
            // 根据给定的字节数组和算法构造一个密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, "AES");
            // 加密
            IvParameterSpec ivs = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ivs);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密
     */
    public static String decrypt(String content) {
        try {
            byte[] b = decrypt(Base64.getUrlDecoder().decode(content), KEY.getBytes(CHAR_UTF_8), IV.getBytes(CHAR_UTF_8));
            if (null != b) {
                return new String(b, CHAR_UTF_8);
            }
        } catch (Exception e) {
            return content;
        }
        return content;
    }

    /**
     * 解密
     */
    public static String decrypt(String content, String key, String iv) {
        try {
            byte[] b = decrypt(Base64.getUrlDecoder().decode(content), key.getBytes(CHAR_UTF_8), iv.getBytes(CHAR_UTF_8));
            if (null != b) {
                return new String(b, CHAR_UTF_8);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密
     */
    public static byte[] decrypt(byte[] content, byte[] keyBytes, byte[] iv) {
        try {
            SecretKey deskey = new SecretKeySpec(keyBytes, "AES");
            // 加密
            IvParameterSpec ivs = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, deskey, ivs);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("解密："+decrypt("ISJI0b6LYnxu4wz_PRrid3gIsokF8A0-xcZr_6Qy7XE="));
        System.out.println("加密："+encrypt("44098219931009277x"));
        System.out.println("解密："+decrypt("emaEh6ImFGPRrmd220fdPUerd0PHieXUN0nF1JW5JVI="));
        System.out.println("加密："+encrypt("33042519760313222X"));
        System.out.println("加密："+encrypt("330624197807200065"));
        System.out.println("解密："+decrypt("a_D_rRJHQ0nnZREEs8FBQQ=="));
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "2");
        String gender = "男".equals(paramsMap.get("gender")) ? "1" : "女".equals(paramsMap.get("gender")) ? "2" : (String) paramsMap.get("gender");
        System.out.println("性别："+gender);
        System.out.println("加密："+encrypt("张三"));
        System.out.println("加密："+encrypt("158797845182"));
        System.out.println("加密："+encrypt("15897845182"));
        System.out.println("解密："+decrypt("EQ8vKgblizaG4AlnxUcU2w=="));
        System.out.println("加密："+encrypt("https://nurseapp.oss-cn-beijing.aliyuncs.com/h5/2022/03/11/20220311174732.png"));
        System.out.println("加密："+encrypt("18378957424"));
        System.out.println("加密："+encrypt("13919009987"));
        System.out.println("解密："+decrypt(decrypt("6IYQXngB40he8SXKH2c4FGUsW79fCX-1bJGoVRnypoU=")));
        System.out.println("对明文解密："+decrypt("15386173690"));
        System.out.println("加密："+encrypt("13919113710"));
        System.out.println("加密："+encrypt("13830991318"));
        System.out.println("加密："+encrypt("13679463898"));
        System.out.println("加密："+encrypt("13919009987"));
        System.out.println("加密："+encrypt("13919371306"));
        System.out.println("加密："+encrypt("13909316652"));
        System.out.println("加密："+encrypt("13659413462"));
    }
}