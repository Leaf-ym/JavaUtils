package com.ncepu.util;

import com.alibaba.fastjson.JSONObject;
import com.ncepu.model.NewUserBean;
import com.ncepu.model.Pager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

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
    /**
     * 包装类类型和对应基本数据类型的关联关系
     */
    public static Map<String, String> basicDataType = new HashMap<String, String>() {{
        put("java.lang.Byte", "byte");
        put("java.lang.Short", "short");
        put("java.lang.Integer", "int");
        put("java.lang.Long", "long");
        put("java.lang.Float", "float");
        put("java.lang.Double", "double");
        put("java.lang.Boolean", "boolean");
        put("java.lang.Character", "char");
    }};

    /**
     * 去掉列表中的bean的非必要字段
     *
     * @param unnecessaryFieldSet
     * @param list
     * @return Map
     * @author wengym
     * @date 2022/10/28 10:46
     */
    public static <T> List<Map<String, Object>> removeUnnecessaryFields(List<T> list, Set<String> unnecessaryFieldSet) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (list == null) {
            return resultList;
        }
        for (T bean : list) {
            resultList.add(removeUnnecessaryFields(bean, unnecessaryFieldSet));
        }
        return resultList;
    }

    /**
     * 去掉bean中的非必要字段
     *
     * @param unnecessaryFieldSet
     * @param bean
     * @return Map
     * @author wengym
     * @date 2022/10/28 10:46
     */
    public static <T> Map<String, Object> removeUnnecessaryFields(T bean, Set<String> unnecessaryFieldSet) {
        Map<String, Object> resultMap = new HashMap<>();
        if (bean == null) {
            return resultMap;
        }
        if (unnecessaryFieldSet == null) {
            unnecessaryFieldSet = new HashSet<>();
        }
        Class cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (unnecessaryFieldSet.contains(fieldName)) {
                continue;
            }
            try {
                field.setAccessible(true);
                resultMap.put(fieldName, field.get(bean));
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    /***
     *
     * 把bean转化为Map
     *
     * @param bean
     *
     * @author wengym
     *
     * @date 2022/6/9 18:07
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     *
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> resultMap = new HashMap<>();
        if (bean == null) {
            return resultMap;
        }
        Class cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(bean);
                field.setAccessible(false);
                resultMap.put(fieldName, fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    /***
     *
     * 获取本类及其父类的字段属性
     *
     * @param cls 当前类对象
     *
     * @author wengym
     *
     * @date 2022/5/24 11:17
     *
     * @return java.lang.reflect.Field[]
     *
     */
    public static Field[] getAllFields(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("cls不能为空");
        }
        List<Field> fieldList = new ArrayList<>();
        while (cls != null) {
            fieldList.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }

    /***
     *
     * 用model中的字段值填充DTO
     *
     * @param model
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/5/20 9:06
     *
     * @return T
     *
     */
    public static <T, D> D modelToDTO(T model, Class<D> cls) {
        if (model == null || cls == null) {
            throw new NullPointerException("model and cls");
        }
        try {
            D dto = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();
            String fieldName;
            for (Field field : fields) {
                fieldName = field.getName();
                if (isExistField(fieldName, model.getClass())) {
                    // 如果model中存在该字段，则取其值赋予dto
                    Object modelValue = getFieldValue(fieldName, model);
                    setFieldValue(fieldName, dto, modelValue);
                }
            }
            return dto;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *
     * 把列表中model的字段值填充DTO，并返回DTO列表
     *
     * @param list
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/6/14 15:10
     *
     * @return java.util.List<D>
     *
     */
    public static <T, D> List<D> modelListToDTOList(List<T> list, Class<D> cls) {
        if (list == null || cls == null) {
            throw new NullPointerException("list and cls");
        }
        List<D> dtoList = new ArrayList<>();
        for (T model : list) {
            dtoList.add(BeanUtils.modelToDTO(model, cls));
        }
        return dtoList;
    }

    /***
     *
     * cls中是否存在字段名为fieldName的字段
     *
     * @param fieldName
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/5/20 9:13
     *
     * @return java.lang.Boolean
     *
     */
    public static Boolean isExistField(String fieldName, Class<?> cls) {
        try {
            cls.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            // 没有这样的字段
            return false;
        }
        return true;
    }

    /***
     *
     * 根据类型名称获取对象
     *
     * @param className
     *
     * @author wengym
     *
     * @date 2022/5/19 8:50
     *
     * @return java.lang.Object
     *
     */
    public static Object getObjectByClassName(String className) {
        Object result = null;
        try {
            result = Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     *
     * 把Map转化成Bean，并返回Bean
     *
     * @param paramMap
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/5/17 10:54
     *
     * @return T
     *
     */
    public static <T> T mapToBean(Map<String, Object> paramMap, Class<?> cls) {
        try {
            T newBean = (T) cls.newInstance();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                Object value = paramMap.get(field.getName());
                if (value != null) {
                    Class valueClass = value.getClass();
                    Class fieldClass = field.getType();
                    if (valueClass != fieldClass) {
                        String valueType = valueClass.getTypeName();
                        String fieldType = field.getType().getTypeName();
                        Boolean isBasicType = StringUtils.equals(BeanUtils.basicDataType.get(valueType), fieldType);
                        if (!isBasicType) {
                            // 不是基本数据类型
                            if (fieldType.equals("java.lang.String")) {
                                value = value.toString();
                            }
                        }
                    }
                    BeanUtils.setFieldValue(field, newBean, value);
                }
            }
            return newBean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

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
     * @param fieldName
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
    public static <T> Object getFieldValue(String fieldName, T bean) {
        Object result = null;
        Class cls = bean.getClass();
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(bean);
            field.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
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
        try {
            field.setAccessible(true);
            result = field.get(bean);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     *
     * 获取bean中的字段名为fieldName的值（包括父类的）
     *
     * @param fieldName
     *
     * @param bean
     *
     * @author wengym
     *
     * @date 2022/5/25 9:20
     *
     * @return java.lang.Object
     *
     */
    public static <T> Object getAllFieldValue(String fieldName, T bean) {
        Object result = null;
        // 获取当前类的所有属性名称，并形成数组
        List<String> currentFieldNames = new ArrayList<>();
        for (Field field : Arrays.asList(bean.getClass().getDeclaredFields())) {
            currentFieldNames.add(field.getName());
        }
        Field f = null;
        try {
            if (currentFieldNames.contains(fieldName)) {
                // 如果访问字段在当前类中
                f = bean.getClass().getDeclaredField(fieldName);
            } else {
                // 如果访问字段不在当前类中，就探索父类
                Class cls = bean.getClass().getSuperclass();
                while (cls != null) {
                    List<String> parentFieldNames = new ArrayList<>();
                    for (Field ff : Arrays.asList(cls.getDeclaredFields())) {
                        parentFieldNames.add(ff.getName());
                    }
                    if (parentFieldNames.contains(fieldName)) {
                        // 如果在父类中，取该字段，退出循环
                        f = cls.getDeclaredField(fieldName);
                        break;
                    }
                    // 如果不在父类中，就探索父类的父类，逐步向前探索，如果到cls为Object.class时，还没有取到对应字段，cls会更新为null（Object.class.getSuperclass()是null），退出循环
                    cls = cls.getSuperclass();
                }
            }
            if (f != null) {
                // 对于用private修饰的成员，可访问性需设为true，才可以访问
                f.setAccessible(true);
                result = f.get(bean);
                f.setAccessible(false);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
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

     */
    public static <T> void setFieldValue(Field field, T bean, Object value) {
        try {
            field.setAccessible(true);
            field.set(bean, value);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * 设置bean中的某个字段的值
     *
     * @param fieldName
     *
     * @param bean
     *
     * @param value
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
    public static <T> void setFieldValue(String fieldName, T bean, Object value) {
        Class cls = bean.getClass();
        // 组装setter
        String fieldSetName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            Field field = cls.getDeclaredField(fieldName);
            Method fieldSetMethod = cls.getMethod(fieldSetName, field.getType());
            fieldSetMethod.invoke(bean, value);
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
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
