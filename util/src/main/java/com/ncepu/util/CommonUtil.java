package com.ncepu.util;

import com.ncepu.model.UserBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 通用工具类
 * @date 2021/11/17 9:25
 */
public class CommonUtil {
    /**
     * 常用字符空，0，1，2
     */
    public static final String COMMON_STR_EMPTY = "";
    public static final String COMMON_STR_ZERO = "0";
    public static final String COMMON_STR_ONE = "1";
    public static final String COMMON_STR_TWO = "2";
    public static final String COMMON_STR_THREE = "3";

    public static final String[] nationArray = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛难族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", "崩龙族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"};
    public static final String[] educationArray = {"小学", "初中", "高中", "中专", "大专", "本科", "硕士研究生", "博士研究生及以上"};
    public static final String[] politicArray = {"中共党员", "中共预备党员", "共青团员", "民革党员", "民盟盟员", "民建会员", "民进会员", "农工党党员", "致公党党员", "九三学社社员", "台盟盟员", "无党派人士", "群众"};
    public static final String[] positionTitleArray = {"护士", "护师", "主管护师", "副主任护师", "主任护师"};
    public static final String[] cardTypeArray = {"中国大陆居民身份证", "港、澳、台通行证", "军官证"};
    public static final String[] genderArray = {"男", "女"};

    /**
     * 省份
     */
    public static final Map<String, String> provinceMap = new HashMap() {{
        put("广西壮族自治区", "450000");
    }};

    /**
     * 城市
     */
    public static final Map<String, String> cityMap = new HashMap() {{
        put("南宁市", "450100");
        put("柳州市", "450200");
        put("桂林市", "450300");
        put("梧州市", "450400");
        put("北海市", "450500");
        put("防城港市", "450600");
        put("钦州市", "450700");
        put("贵港市", "450800");
        put("玉林市", "450900");
        put("百色市", "451000");
        put("贺州市", "451100");
        put("河池市", "451200");
        put("来宾市", "451300");
        put("崇左市", "451400");
    }};

    /**
     * 32位长度大写UUID
     *
     * @return
     */
    public static String randomUPUUID() {
        return String.valueOf(UUID.randomUUID()).toUpperCase().replaceAll("-", "");
    }

    /**
     * 32位长度小写UUID
     *
     * @return
     */
    public static String randomLOWUUID() {
        return String.valueOf(UUID.randomUUID()).toLowerCase().replaceAll("-", "");
    }

    /**
     * 实体类转Map共通方法
     *
     * @param bean 实体类
     * @return Map
     * @throws Exception
     */
    public static Map<String, Object> convertBean(Object bean) {
        Map<String, Object> returnMap = new HashMap<>();
        if (bean == null)
            return returnMap;

        List<Field> fields = new ArrayList<>();
        List<Field> childFields;
        List<String> fieldsName = new ArrayList<>();
        Class type = bean.getClass();
        while (type != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
            type = type.getSuperclass(); //得到父类,然后赋给自己
        }
        childFields = Arrays.asList(bean.getClass().getDeclaredFields());
        for (Field field : childFields) {
            fieldsName.add(field.getName());
        }
        try {
            for (Field field : fields) {
                try {
                    Field f = null;
                    if (fieldsName.contains(field.getName())) {
                        f = bean.getClass().getDeclaredField(
                                field.getName());
                    } else {
                        f = bean.getClass().getSuperclass().getDeclaredField(
                                field.getName());
                    }
                    if (!f.getName().equals("pageSize") && !f.getName().equals("pageIndex")) {
                        f.setAccessible(true);
                        Object o = f.get(bean);
                        if (o != null) {
                            if ((f.getType().toString().endsWith("Integer") && Integer.valueOf(o.toString()) == -1) || ("all".equalsIgnoreCase(o.toString()) && !f.getName().equals("param"))) {
                                returnMap.put(f.getName(), CommonUtil.COMMON_STR_EMPTY);
                            } else {
                                returnMap.put(field.getName(), o);
                            }
                        }
                        f.setAccessible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error:实体类转化bean异常！");
                    //log.error("实体类转化bean异常！");
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println("Error:实体类转化bean异常！");
            //log.error("实体类转化bean异常！");
        }
        return returnMap;
    }

    /***
     *
     * @param map 要转化的map数据
     *
     * @param beanClass 要转化的Bean的Class类型
     *
     * @desc 把map中的数据转换为响应的bean
     *
     * @author wengym
     *
     * @date 2021/11/26 17:22
     *
     * @return void
     *
     */
    public static <T> T convertMapToBean(Map<String, Object> map, Class<T> beanClass) {
        if (map == null) {
            return null;
        }
        try {
            T bean = beanClass.newInstance();
            Field[] fields = beanClass.getDeclaredFields();
            Set<String> keySet = map.keySet();
            for (Field field : fields) {
                if (keySet.contains(field.getName())) {
                    System.out.println("设置："+field.getName()+"的值");
                    Method method = beanClass.getMethod("set".concat(StringUtils.captureName(field.getName())),field.getType());
                    method.invoke(bean, map.get(field.getName()));
                }
            }
            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *
     * 字符串判空
     *
     * @param str
     *
     * @author wengym
     *
     * @date 2022/3/17 20:31
     *
     * @return java.lang.Boolean
     *
     */
    public static Boolean isNullStr(Object str) {
        return str == null || "".equals(String.valueOf(str)) || "null".equals(String.valueOf(str).trim()) || "''".equals(String.valueOf(str).trim());
    }

    public static void test() {
        ///////////////////////////////bean转map
        UserBean user = new UserBean();
        user.setUserName("wengym");
        user.setPassword("123456");
        user.setParam("模糊查询");
        user.setPageIndex(1);
        user.setPageSize(10);
        Map<String,Object> map = CommonUtil.convertBean(user);
        System.out.println("bean转map："+map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("userName", "ym");
        map1.put("password", "654321");
        map1.put("bb", true);
        map1.put("bb1", false);
        map1.put("by", Byte.valueOf("1"));
        map1.put("by1", Byte.valueOf("2"));
        map1.put("cc", 'a');
        map1.put("cc1", 'A');
        map1.put("sh", Short.valueOf((short)12));
        map1.put("sh1", (short)12);
        map1.put("in", 12);
        map1.put("in1", 12);
        map1.put("lg", 12L);
        map1.put("lg1", 12L);
        map1.put("ft", 12F);
        map1.put("ft1", 12F);
        map1.put("db", 12D);
        map1.put("db1", 12D);
        UserBean bean = CommonUtil.convertMapToBean(map1, UserBean.class);
        System.out.println("map转bean："+bean.toString());
    }

    public static void main(String[] args) {
        CommonUtil.test();
    }
}
