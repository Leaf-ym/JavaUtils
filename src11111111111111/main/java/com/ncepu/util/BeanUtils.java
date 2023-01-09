package com.ncepu.util;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.model.NewUserBean;
import com.ncepu.model.Pager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Bean转换工具类
 * <p>
 * 反射机制：
 * （1）forName 方法：forName是一个静态方法，其作用：通过调用来获取类名对应的Class对象，同时将Class对象加载进来。
 * 如果将类名保存在字符串（如xml）中，就可以在程序运行时，动态调用加载。注意：只有调用的参数是类名或者方法时，才可用。
 * （2）newInstance()方法：作用：将对象实例化。返回类型为Object。与new的区别在于，new可以带参，而newInstance（）
 * 不可以，一边初始化无参类。通常与forName()配合使用。
 * （3）getMethod()方法：getMethod方法与getField方法类似，getField方法根据表示域名的字符串，返回一个Field对象。
 * 而getMethod方法则根据方法名称和相关参数，来定位需要查找的Method对象并返回。getMethod与getDeclareMethods
 * 方法的区别在于，后者返回一个Method对象数组，需要自己在结果中查找所需Method对象。
 * 原型： Method getMethod(String name,Class...parameterTypes)
 * 参数解释：name： method的名称
 * parameterTypes：method的参数类型的列表（参数顺序需按声明method时的参数列表排列）
 * 返回：符合method名称和参数的method对象
 * 抛出错误：NoSuchMethodException
 * 原因：没有找到所要查询的Method对象  或  Method名称为“<init>”或“<clinit>”
 * NullPointerException
 * 原因：所要查询的Method对象的名称为null
 * SecurityException
 * 原因：调用的类或其父类没有调用权限
 * （4）invoke方法：作用：调用包装在当前Method对象中的方法。
 * 原型：Object invoke（Object obj,Object...args）
 * 参数解释：obj：实例化后的对象
 * args：用于方法调用的参数
 * 返回：根据obj和args调用的方法的返回值
 * 抛出错误：IllegalAccessException
 * 原因：Method对象强制Java语言执行控制 或 无权访问obj对象
 * IllegalArgumentException
 * 原因：方法是实例化方法，而指定需要调用的对象并不是实例化后的类或接口
 * （5）Field.setAccessible(boolean flag)：此方法可以设置是否忽略权限直接访问private等私有权限的成员变量
 * （注意，这里说的是直接访问，而不是通过getter访问），如果打算通过getter和setter方法访问成员变量，则方法用处不大，
 * 因为如果不打算直接访问成员变量，那么是否忽略权限就没有意义了
 * （6）Class.getDeclaredFields()：此方法获取Class对象对应类的所有成员变量，包括private的，但是不包括继承到的成员变量
 *
 * @author wengym
 * @date 2021-11-18
 */
public class BeanUtils {
    
    /***
     * 
     * 把集合中的所有bean的数据类型为字符串的字段的前后空格给去掉
     *
     * @param collection - List,Set
     *
     * @author wengym
     *
     * @date 2022/5/11 16:23 
     *
     * @return void
     *
     */
    public static <T> void trimCollection(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        for (T bean : collection) {
            BeanUtils.trimBean(bean);
        }
    }

    /***
     *
     * 把bean中的数据类型为字符串的字段的前后空格给去掉
     *
     * @param bean
     *
     * @author wengym
     *
     * @date 2022/5/11 14:53
     *
     * @return void
     *
     */
    public static <T> void trimBean(T bean) {
        if (bean == null) {
            return;
        }
        Class cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String fieldType = field.getType().getSimpleName();
            if ("String".equals(fieldType)) {
                String value = (String) BeanUtils.getFieldValue(field, bean);
                if (value != null) {
                    BeanUtils.setFieldValue(field, bean, value.trim());
                }
            }
        }
    }

    /***
     *
     * 获取bean中的某个字段的值
     *
     * @param field
     *
     * @param bean
     *
     * @author wengym
     *
     * @date 2022/5/11 14:56
     *
     * @return java.lang.Object
     *
     */
    public static <T> Object getFieldValue(Field field, T bean) {
        Object result = null;
        Class cls = bean.getClass();
        // 组装getter
        String fieldName = field.getName();
        String fieldGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            Method fieldGetMethod = cls.getMethod(fieldGetName);
            result = fieldGetMethod.invoke(bean);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     *
     * 设置bean中的某个字段的值
     *
     * @param field
     *
     * @param bean
     *
     * @param value
     *
     * @author wengym
     *
     * @date 2022/5/11 14:57
     *
     * @return void
     *
     */
    public static <T> void setFieldValue(Field field, T bean, Object value) {
        Class cls = bean.getClass();
        // 组装setter
        String fieldName = field.getName();
        String fieldSetName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            Method fieldSetMethod = cls.getMethod(fieldSetName, field.getType());
            fieldSetMethod.invoke(bean, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * 把带有分页参数的实体类转Map的共通方法
     *
     * @param returnMap
     *
     * @author wengym
     *
     * @date 2022/3/19 8:54
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     *
     */
    public static Map<String, Object> convertPageBean(Map<String, Object> returnMap, Object bean, Pager pager) {
        if (bean == null || returnMap == null) {
            return returnMap;
        }
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
                                returnMap.put(f.getName(), "");
                            } else {
                                returnMap.put(field.getName(), o);
                            }
                        }
                        f.setAccessible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //log.error("实体类转化bean异常！");
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            //log.error("实体类转化bean异常！");
        }
        return returnMap;
    }

    /***
     *
     * 把valueBean对象的值一一赋给bean
     *
     * @param bean
     *
     * @param valueBean
     *
     * @author wengym
     *
     * @date 2022/3/8 15:05
     *
     * @return void
     *
     */
    public static <T> void copyValueToBean(T bean, T valueBean) {
        if (bean == null || valueBean == null) {
            return;
        }

        Class cls = bean.getClass();
        // 获取T类的所有成员，注意，不包括继承到的成员
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            // 组装getter和setter方法
            String fieldName = field.getName();
            String fieldGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String fieldSetName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method fieldGetMethod = cls.getMethod(fieldGetName);
                // field.getType()：获取该成员变量的Class对象
                Method fieldSetMethod = cls.getMethod(fieldSetName, field.getType());
                Object value = fieldGetMethod.invoke(valueBean);
                fieldSetMethod.invoke(bean, value);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     *
     * @param oldModel
     *
     * @param newModel
     *
     * @desc 整理出中华会员新旧model的修改字段信息
     *
     * @author wengym
     *
     * @date 2022/1/24 16:32
     *
     * @return java.util.List<com.meet.member.model.dto.MemberHistoryDTO>
     *
     */
    public static void dealUpdateInfoOfCnaMember(NewUserBean oldModel, NewUserBean newModel) {
        JSONObject historyColumn = new JSONObject();
        JSONObject modifyColumn = new JSONObject();
        Class cls = oldModel.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldType = field.getType().getSimpleName();
            String fieldName = field.getName();
            String fieldGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method fieldGetMethod = cls.getMethod(fieldGetName, new Class[]{});
                Object oldFieldValue = fieldGetMethod.invoke(oldModel, new Object[]{});
                Object newFieldValue = fieldGetMethod.invoke(newModel, new Object[]{});
                if (oldFieldValue != null || newFieldValue != null) {
                    if ("Integer".equals(fieldType)) {
                        Integer oldVal = (Integer) oldFieldValue;
                        Integer newVal = (Integer) newFieldValue;
                        if (!StringUtils.equals(oldVal + "", newVal + "")) {
                            historyColumn.put(fieldName, oldVal);
                            modifyColumn.put(fieldName, newVal);
                        }
                    } else if ("Long".equals(fieldType)) {
                        Long oldVal = (Long) oldFieldValue;
                        Long newVal = (Long) newFieldValue;
                        if (!StringUtils.equals(oldVal + "", newVal + "")) {
                            historyColumn.put(fieldName, oldVal);
                            modifyColumn.put(fieldName, newVal);
                        }
                    } else if ("String".equals(fieldType)) {
                        String oldVal = (String) oldFieldValue;
                        String newVal = (String) newFieldValue;
                        if (!StringUtils.equals(oldVal, newVal)) {
                            historyColumn.put(fieldName, oldVal);
                            modifyColumn.put(fieldName, newVal);
                        }
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        System.out.println("historyColumn：" + historyColumn.toJSONString());
        System.out.println("modifyColumn：" + modifyColumn.toJSONString());
    }



    /*private static void mapToBean(Map<String, Object> map, UserBean user) throws Exception {

        //拿到 user 的对应的class对象
        Class<UserBean> clazz = (Class<UserBean>) user.getClass();

        //通过反射拿到所有的属性

        //拿到所有的属性值
        Field[] fields = clazz.getDeclaredFields();

        //遍历属性

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];

            //获取属性的名称
            String key = f.getName();

            //跳过属性的权限检查
            f.setAccessible(true);

            //都是字符串 操作  进行判断

            //如果属性的类型是int 判断int.class   如果是Ingeter 判断为 Integer.class
            if (f.getType() == int.class){

                f.set(user,Integer.valueOf(map.get(key)));
            }else if (f.getType() == double.class){
                f.set(user,Double.valueOf(map.get(key)));
            }else{
                f.set(user,map.get(key));
            }

        }
    }*/

    public static void main(String[] args) {
        /*NewUserBean newModel = new NewUserBean();
        newModel.setUserName("张三");
        newModel.setPassword("123456");
        //newModel.setId(1000);
        newModel.setMlId(10000L);
        NewUserBean oldModel = new NewUserBean();
        oldModel.setUserName("李四");
        oldModel.setPassword("123456");
        oldModel.setId(1000);
        oldModel.setMlId(10000L);
        BeanUtils.dealUpdateInfoOfCnaMember(oldModel, newModel);*/
        ////////////////////////////////////////////////////////
        /*NewUserBean newUserBean = new NewUserBean();
        newUserBean.setId(1);
        newUserBean.setMlId(2L);
        newUserBean.setUserName("wengym");
        newUserBean.setPassword("123456");
        NewUserBean bean = new NewUserBean();
        BeanUtils.copyValueToBean(bean, newUserBean);
        PrintUtils.println(bean);*/
    }
}
