package com.ncepu.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 数据库操作工具类
 * @date 2022/3/11 9:18
 */
public class DatabaseUtils {

    /***
     *
     * 构造单条记录插入的SQL语句
     *
     * @param tableName 数据库表名称
     * @param cls 数据库表对应model的Class对象
     * @param model 要插入的数据model
     * @param ignoreFieldSet 要忽略插入的字段集合 如，自动增长的整型id
     * @param databaseValue 不打算用列表中的值插入的键值对map，即自定义插入的默认键值对，
     *                      如，fillDate会用now()作为值表示插入时间为当前时间
     *                      ，isDelete会用0作为值表示未删除的正常数据
     *                      ，键值对中的值必须是符合格式的SQL值，即如果是字符串，就需要加上单引号或双引号才行，如果是整数，则不需处理直接添加即可
     *
     *
     * @author wengym
     *
     * @date 2022/4/14 16:25
     *
     * @return java.lang.String
     *
     */
    public static <T> String insert(String tableName, Class<?> cls, T model, Set<String> ignoreFieldSet, Map<String, Object> databaseValue) {
        List<T> list = new ArrayList<>();
        list.add(model);
        String sql = DatabaseUtils.batchInsert(tableName, cls, list, ignoreFieldSet, databaseValue);
        return sql;
    }

    /***
     *
     * 构造批量插入的SQL语句
     *
     * @param tableName 数据库表名称
     * @param cls 数据库表对应model的Class对象
     * @param list 要批量插入的数据列表
     * @param ignoreFieldSet 要忽略插入的字段集合 如，自动增长的整型id
     * @param databaseValue 不打算用列表中的值插入的键值对map，即自定义插入的默认键值对，
     *                      如，fillDate会用now()作为值表示插入时间为当前时间
     *                      ，isDelete会用0作为值表示未删除的正常数据
     *                      ，键值对中的值必须是符合格式的SQL值，即如果是字符串，就需要加上单引号或双引号才行，如果是整数，则不需处理直接添加即可
     *
     * @author wengym
     *
     * @date 2022/3/17 03:17
     *
     * @return java.lang.String
     *
     */
    public static <T> String batchInsert(String tableName, Class<?> cls, List<T> list, Set<String> ignoreFieldSet, Map<String, Object> databaseValue) {
        if (ignoreFieldSet == null) {
            ignoreFieldSet = new HashSet<>();
        }
        if (databaseValue == null) {
            databaseValue = new HashMap<>();
        }
        // 根据注解@DatabaseField获取数据库表的字段列表
        List<String> fieldList = DatabaseUtils.getDatabaseFieldList(cls);
        StringBuilder sb = new StringBuilder();
        // 头部
        sb.append("INSERT INTO ".concat(tableName).concat(" ("));
        // 插入的字段
        List<String> insertFieldList = new ArrayList<>();
        for (String str : fieldList) {
            // 获取插入的字段列表，有些是不需要插入的，如自动递增的id
            if (ignoreFieldSet.contains(str)) {
                continue;
            }
            insertFieldList.add(str);
        }
        sb.append(StringUtils.join(insertFieldList, ","));
        sb.append(") VALUES ");
        // 插入字段对应的值列表
        List<String> valuesList = new ArrayList<>();
        for (T model : list) {
            List<String> valueList = new ArrayList<>();
            for (String str : insertFieldList) {
                if (databaseValue.keySet().contains(str)) {
                    valueList.add(databaseValue.get(str) + "");
                    continue;
                }
                String insertValue = DatabaseUtils.getBeanSQLValue(str, model);
                valueList.add(insertValue);
            }
            valuesList.add("(" + StringUtils.join(valueList, ",") + ")");
        }
        sb.append(StringUtils.join(valuesList, ","));
        String sql = sb.toString();
        return sql;
    }

    /***
     *
     * 构造删除数据库记录的SQL语句
     *
     * @param tableName
     * @param cls
     * @param queryMap
     * @param ignoreQueryFieldSet
     * @param isEmptyNotQuery
     *
     * @author wengym
     *
     * @date 2022/4/24 10:21
     *
     * @return java.lang.String
     *
     */
    public static <T> String delete(String tableName, Class<?> cls, Map<String, Object> queryMap, Set<String> ignoreQueryFieldSet, Boolean isEmptyNotQuery) {
        if (cls == null) {
            return "";
        }
        if (queryMap == null) {
            queryMap = new HashMap<>();
        }
        String deleteStr = "DELETE";
        String fromStr = DatabaseUtils.getFromSubStatement(tableName);
        String commonSearchStr = DatabaseUtils.commonSearch(cls, queryMap, ignoreQueryFieldSet, null, isEmptyNotQuery);
        String whereStr = DatabaseUtils.isNullStr(commonSearchStr) ? "" : "WHERE ".concat(commonSearchStr);
        return deleteStr + " " + fromStr + " " + whereStr;
    }

    /***
     *
     * 构造单条记录更新的SQL语句
     *
     * @param tableName 数据库表名称
     * @param cls 数据库表对应model的Class对象
     * @param model 要更新的数据model
     * @param ignoreFieldSet 要忽略更新的字段集合
     * @param databaseValue 不打算用列表中的值更新的键值对map 如，updateDate会用now()作为值，键值对中的值必须是符合格式的SQL值，即如果是字符串，就需要加上单引号或双引号才行，如果是整数，则不需处理直接添加即可
     * @param whereFieldSet 查询条件字段集合 都是等值查询条件，多个条件用 && 连起来
     * @param isEmptyNotUpdate 是否非空更新，true：null值不更新，false：null也更新
     *
     * @author wengym
     *
     * @date 2022/04/21 11:34
     *
     * @return java.lang.String
     *
     */
    public static <T> String update(String tableName, Class<?> cls, T model, Set<String> ignoreFieldSet, Map<String, Object> databaseValue, Set<String> whereFieldSet, Boolean isEmptyNotUpdate) {
        List<T> list = new ArrayList<>();
        list.add(model);
        String sql = DatabaseUtils.batchUpdate(tableName, cls, list, ignoreFieldSet, databaseValue, whereFieldSet, isEmptyNotUpdate);
        return sql;
    }

    /***
     *
     * 构造批量更新的SQL语句
     *
     * @param tableName 数据库表名称
     * @param cls 数据库表对应model的Class对象
     * @param list 要批量更新的数据列表
     * @param ignoreFieldSet 要忽略更新的字段集合
     * @param databaseValue 不打算用列表中的值更新的键值对map 如，updateDate会用now()作为值，键值对中的值必须是符合格式的SQL值，即如果是字符串，就需要加上单引号或双引号才行，如果是整数，则不需处理直接添加即可
     * @param whereFieldSet 查询条件字段集合 都是等值查询条件，多个条件用 && 连起来
     * @param isEmptyNotUpdate 是否非空更新，true：null值不更新，false：null也更新
     *
     * @author wengym
     *
     * @date 2022/3/17 19:45
     *
     * @return java.lang.String
     *
     */
    public static <T> String batchUpdate(String tableName, Class<?> cls, List<T> list, Set<String> ignoreFieldSet, Map<String, Object> databaseValue, Set<String> whereFieldSet, Boolean isEmptyNotUpdate) {
        if (ignoreFieldSet == null) {
            ignoreFieldSet = new HashSet<>();
        }
        if (databaseValue == null) {
            databaseValue = new HashMap<>();
        }
        if (whereFieldSet == null) {
            whereFieldSet = new HashSet<>();
        }
        // 根据注解@DatabaseField获取数据库表的字段列表
        List<String> fieldList = DatabaseUtils.getDatabaseFieldList(cls);
        // 构造SQL语句
        StringBuilder sb = new StringBuilder();

        // 整理要更新的字段的字段（从所有的字段列表中剔除掉忽略的字段）
        List<String> updateFieldList = new ArrayList<>();
        for (String str : fieldList) {
            // 获取插入的字段列表，有些是不需要插入的，如自动递增的id
            if (ignoreFieldSet.contains(str)) {
                continue;
            }
            updateFieldList.add(str);
        }

        for (T model : list) {
            // 头部
            sb.append("UPDATE ".concat(tableName).concat(" SET "));
            List<String> updateKeyValueList = new ArrayList<>();
            List<String> whereKeyValueList = new ArrayList<>();
            // 处理更新键值对
            for (String str : updateFieldList) {
                if (databaseValue.keySet().contains(str)) {
                    // 直接更新，不管值是空的，还是非空的
                    updateKeyValueList.add(str + "=" + databaseValue.get(str));
                    continue;
                }
                String updateValue = DatabaseUtils.getBeanSQLValue(str, model);
                String keyValue = str + "=" + updateValue;
                // 是否非空更新，如果值为true，就不更新
                if (isEmptyNotUpdate) {
                    if (!DatabaseUtils.isNullStr(updateValue)) {
                        updateKeyValueList.add(keyValue);
                    }
                } else {
                    updateKeyValueList.add(keyValue);
                }
            }
            // 处理查询键值对
            for (String str : whereFieldSet) {
                String whereValue = DatabaseUtils.getBeanSQLValue(str, model);
                String keyValue = str + "=" + whereValue;
                whereKeyValueList.add(keyValue);
            }
            String whereClause = "";
            whereClause = whereKeyValueList.size() > 0 ? " WHERE " + StringUtils.join(whereKeyValueList, " && ") : "";
            sb.append(StringUtils.join(updateKeyValueList, ",") + whereClause + ";");
        }
        String sql = sb.toString();
        return sql;
    }


    /***
     *
     * 构造查询一条记录的SQL语句
     *
     * @param tableName 数据库表名
     * @param cls 数据库表对应的model的Class对象
     * @param queryMap 查询条件（只会以数据库字段作为查询条件，即model中用注解@DatabaseField修饰的字段）
     * @param ignoreSelectFieldSet 忽略作为select投影字段的字段集合，如果不想投影出不需要的字段，可以在此集合中添加忽略投影的字段
     * @param ignoreQueryFieldSet 忽略作为查询条件的字段集合，如果queryMap中的某字段作为查询条件，可以在此集合中添加上该字段
     * @param isEmptyNotQuery 是否非空查询，即如果queryMap存在键值对：workUnit-null，且是非空查询，则不会添加查询条件workUnit=null，
     *                        如果不是非空查询，则会添加查询条件workUnit=null。
     *
     * @author wengym
     *
     * @date 2022/4/20 14:10
     *
     * @return java.lang.String
     *
     */
    public static <T> String getObject(String tableName, Class<?> cls, Map<String, Object> queryMap, Set<String> ignoreSelectFieldSet, Set<String> ignoreQueryFieldSet, Boolean isEmptyNotQuery) {
        queryMap.put("pageIndex", 0);
        queryMap.put("pageSize", 1);
        String sql = DatabaseUtils.listObjects(tableName, cls, queryMap, ignoreSelectFieldSet, ignoreQueryFieldSet, null, isEmptyNotQuery);
        return sql;
    }

    /***
     *
     * 构造查询多条记录的SQL语句
     *
     * @param tableName 数据库表名
     * @param cls 数据库表对应的model的Class对象
     * @param queryMap - pageIndex，pageSize，查询条件（只会以数据库字段作为查询条件，即model中用注解@DatabaseField修饰的字段）
     * @param ignoreSelectFieldSet 忽略作为select投影字段的字段集合，如果不想投影出不需要的字段，可以在此集合中添加忽略投影的字段
     * @param ignoreQueryFieldSet 忽略作为查询条件的字段集合，如果queryMap中的某字段作为查询条件，可以在此集合中添加上该字段
     * @param orderByStr 排序字符串，如ORDER BY fillDate DESC
     * @param isEmptyNotQuery 是否非空查询，即如果queryMap存在键值对：workUnit-null，且是非空查询，则不会添加查询条件workUnit=null，
     *                        如果不是非空查询，则会添加查询条件workUnit=null。
     *
     * @author wengym
     *
     * @date 2022/4/21 15:43
     *
     * @return java.lang.String
     *
     */
    public static <T> String listObjects(String tableName, Class<?> cls, Map<String, Object> queryMap, Set<String> ignoreSelectFieldSet, Set<String> ignoreQueryFieldSet, String orderByStr, Boolean isEmptyNotQuery) {
        if (cls == null) {
            return "";
        }
        if (queryMap == null) {
            queryMap = new HashMap<>();
        }
        String selectStr = DatabaseUtils.getSelectSubStatement(cls, ignoreSelectFieldSet);
        String fromStr = DatabaseUtils.getFromSubStatement(tableName);
        String commonSearchStr = DatabaseUtils.commonSearch(cls, queryMap, ignoreQueryFieldSet, null, isEmptyNotQuery);
        String whereStr = DatabaseUtils.isNullStr(commonSearchStr) ? "" : "WHERE ".concat(commonSearchStr);
        String limitStr = DatabaseUtils.getLimitSubStatement(queryMap.get("pageSize"));
        String offsetStr = DatabaseUtils.getOffsetSubStatement(queryMap.get("pageIndex"));
        String orderStr = DatabaseUtils.isNullStr(orderByStr) ? "" : orderByStr;
        List<String> sqlList = new ArrayList<>();
        sqlList.add(selectStr);
        sqlList.add(fromStr);
        if (!DatabaseUtils.isNullStr(whereStr)) {
            sqlList.add(whereStr);
        }
        if (!DatabaseUtils.isNullStr(orderStr)) {
            sqlList.add(orderStr);
        }
        if (!DatabaseUtils.isNullStr(limitStr)) {
            sqlList.add(limitStr);
        }
        if (!DatabaseUtils.isNullStr(offsetStr)) {
            sqlList.add(offsetStr);
        }
        String sql = StringUtils.join(sqlList, " ");
        return sql;
    }

    /***
     *
     * 构造统计符合条件的记录数目的SQL语句
     *
     * @param tableName 数据库表名
     * @param cls 数据库表对应的model的Class对象
     * @param statisticalField 统计字段，如是按id统计count(id)，还是按星号统计count(*)
     * @param queryMap 查询条件（只会以数据库字段作为查询条件，即model中用注解@DatabaseField修饰的字段）
     * @param ignoreQueryFieldSet 忽略作为查询条件的字段集合，如果queryMap中的某字段作为查询条件，可以在此集合中添加上该字段
     * @param isEmptyNotQuery 是否非空查询，即如果queryMap存在键值对：workUnit-null，且是非空查询，则不会添加查询条件workUnit=null，
     *                        如果不是非空查询，则会添加查询条件workUnit=null。
     *
     * @author wengym
     *
     * @date 2022/4/21 15:43
     *
     * @return java.lang.String
     *
     */
    public static <T> String countListObjects(String tableName, Class<?> cls, String statisticalField, Map<String, Object> queryMap, Set<String> ignoreQueryFieldSet, Boolean isEmptyNotQuery) {
        if (cls == null) {
            return "";
        }
        if (queryMap == null) {
            queryMap = new HashMap<>();
        }
        String selectStr = DatabaseUtils.getSelectCountSubStatement(statisticalField);
        String fromStr = DatabaseUtils.getFromSubStatement(tableName);
        String commonSearchStr = DatabaseUtils.commonSearch(cls, queryMap, ignoreQueryFieldSet, null, isEmptyNotQuery);
        String whereStr = DatabaseUtils.isNullStr(commonSearchStr) ? "" : "WHERE ".concat(commonSearchStr);
        return selectStr + " " + fromStr + " " + whereStr;
    }

    /***
     *
     * 构造select count(*)子语句：select count(*), select count(id)
     *
     * @param statisticalField
     *
     * @author wengym
     *
     * @date 2022/4/20 14:13
     *
     * @return java.lang.String
     *
     */
    public static String getSelectCountSubStatement(Object statisticalField) {
        if (DatabaseUtils.isNullStr(statisticalField)) {
            statisticalField = "*";
        }
        String countStr = "SELECT COUNT(".concat(String.valueOf(statisticalField)).concat(")");
        return countStr;
    }


    /***
     *
     * 构造offset子语句：offset 0
     *
     * @param offset
     *
     * @author wengym
     *
     * @date 2022/4/20 14:13
     *
     * @return java.lang.String
     *
     */
    public static String getOffsetSubStatement(Object offset) {
        if (DatabaseUtils.isNullStr(offset)) {
            return "";
        }
        String offsetStr = "OFFSET ".concat(String.valueOf(offset));
        return offsetStr;
    }

    /***
     *
     * 构造limit子语句：limit 1
     *
     * @param limit
     *
     * @author wengym
     *
     * @date 2022/4/20 14:13
     *
     * @return java.lang.String
     *
     */
    public static String getLimitSubStatement(Object limit) {
        if (DatabaseUtils.isNullStr(limit)) {
            return "";
        }
        String limitStr = "LIMIT ".concat(String.valueOf(limit));
        return limitStr;
    }

    /***
     *
     * 构造from子语句：from t1
     *
     * @param tableName
     *
     * @author wengym
     *
     * @date 2022/4/20 13:51
     *
     * @return java.lang.String
     *
     */
    public static String getFromSubStatement(String tableName) {
        String fromStr = "FROM ".concat(tableName);
        return fromStr;
    }

    /***
     *
     * 构造查询子语句：select field1,field2,...
     *
     * @param cls 数据库表对应的model类的Class对象
     * @param ignoreFieldSet 忽略查询的字段集合
     *
     * @author wengym
     *
     * @date 2022/3/11 9:24
     *
     * @return java.lang.String
     *
     */
    public static String getSelectSubStatement(Class<?> cls, Set<String> ignoreFieldSet) {
        if (cls == null) {
            return "SELECT *";
        }
        if (ignoreFieldSet == null) {
            ignoreFieldSet = new HashSet<>();
        }
        List<String> fields = DatabaseUtils.getDatabaseFieldList(cls);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        for (String field : fields) {
            if (!ignoreFieldSet.contains(field)) {
                sb.append(" " + field + ",");
            }
        }
        String result = DatabaseUtils.removeSuffix(sb.toString(), ",");
        return result;
    }

    /***
     *
     * 基于model获取普通的查询条件
     *
     * @param model 数据表对应的model，即查询条件
     * @param ignoreQueryFieldSet 一般会结合isEmptyNotQuery，如果不是非空查询，则如果想忽略某个字段，则可将该字段添加到集合中
     * @param alias 别名
     * @param isEmptyNotQuery 是否非空查询
     *
     * @author wengym
     *
     * @date 2022/4/11 13:38
     *
     * @return java.lang.String
     *
     */
    public static <T> String commonSearch(T model, Set<String> ignoreQueryFieldSet, String alias, Boolean isEmptyNotQuery) {
        if (model == null) {
            return "";
        }
        if (ignoreQueryFieldSet == null) {
            ignoreQueryFieldSet = new HashSet<>();
        }
        if (DatabaseUtils.isNullStr(alias)) {
            alias = "";
        } else {
            alias = alias.concat(".");
        }
        Class cls = model.getClass();
        List<String> fields = DatabaseUtils.getDatabaseFieldList(cls);
        List<String> commonSearchKeyValueList = new ArrayList<>();

        for (String str : fields) {
            if (ignoreQueryFieldSet.contains(str)) {
                continue;
            }
            String commonSearchValue = DatabaseUtils.getBeanSQLValue(str, model);
            String keyValue = alias.concat(str).concat("=").concat(commonSearchValue);
            if (isEmptyNotQuery) {
                if (!DatabaseUtils.isNullStr(commonSearchValue)) {
                    commonSearchKeyValueList.add(keyValue);
                }
            } else {
                commonSearchKeyValueList.add(keyValue);
            }
        }

        String commonSearchClause = "";
        commonSearchClause = commonSearchKeyValueList.size() > 0 ? StringUtils.join(commonSearchKeyValueList, " && ") : "";
        return commonSearchClause;
    }

    /***
     *
     * 获取map普通的查询条件
     *
     * @param cls 数据表对应的model的Class对象
     * @param queryMap 查询条件
     * @param ignoreQueryFieldSet 一般会结合isEmptyNotQuery，如果不是非空查询，则如果想忽略某个字段，则可将该字段添加到集合中
     * @param alias 别名
     * @param isEmptyNotQuery 是否非空查询
     *
     * @author wengym
     *
     * @date 2022/4/11 13:38
     *
     * @return java.lang.String
     *
     */
    public static String commonSearch(Class<?> cls, Map<String, Object> queryMap, Set<String> ignoreQueryFieldSet, String alias, Boolean isEmptyNotQuery) {
        if (cls == null || queryMap == null) {
            return "";
        }
        if (ignoreQueryFieldSet == null) {
            ignoreQueryFieldSet = new HashSet<>();
        }
        if (DatabaseUtils.isNullStr(alias)) {
            alias = "";
        } else {
            alias = alias.concat(".");
        }
        List<String> fields = DatabaseUtils.getDatabaseFieldList(cls);
        List<String> commonSearchKeyValueList = new ArrayList<>();

        for (String str : fields) {
            if (ignoreQueryFieldSet.contains(str)) {
                continue;
            }
            String commonSearchValue = DatabaseUtils.getMapSQLValue(cls, str, queryMap);
            String keyValue = alias.concat(str).concat("=").concat(commonSearchValue);
            if (isEmptyNotQuery) {
                if (!DatabaseUtils.isNullStr(commonSearchValue)) {
                    commonSearchKeyValueList.add(keyValue);
                }
            } else {
                commonSearchKeyValueList.add(keyValue);
            }
        }

        String commonSearchClause = "";
        commonSearchClause = commonSearchKeyValueList.size() > 0 ? StringUtils.join(commonSearchKeyValueList, " && ") : "";
        return commonSearchClause;
    }

    /***
     *
     * 根据数据类型获取默认值
     *
     * @param dataType
     *
     * @author wengym
     *
     * @date 2022/3/17 15:04
     *
     * @return java.lang.String
     *
     */
    public static String getDefaultValueByDataType(String dataType) {
        if (dataType == null || dataType.isEmpty()) {
            return "''";
        }
        if ("Short".equals(dataType) || "java.lang.Short".equals(dataType)) {
            return "0";
        }
        if ("Integer".equals(dataType) || "java.lang.Integer".equals(dataType)) {
            return "0";
        }
        if ("Long".equals(dataType) || "java.lang.Long".equals(dataType)) {
            return "0";
        }
        if ("Boolean".equals(dataType) || "java.lang.Boolean".equals(dataType)) {
            return "false";
        }
        if ("Float".equals(dataType) || "java.lang.Float".equals(dataType)) {
            return "0.0";
        }
        if ("Double".equals(dataType) || "java.lang.Double".equals(dataType)) {
            return "0.0";
        }
        return "''";
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

    /***
     *
     * 去掉字符串的后缀：如果字符串str以suffix结尾，则去掉str结尾处的suffix部分，并返回结果，
     * 反之，如果字符串str不以suffix结尾，则直接返回str，不做处理
     *
     * @param str
     * @param suffix
     *
     * @author wengym
     *
     * @date 2022/3/11 9:33
     *
     * @return java.lang.String
     *
     */
    public static String removeSuffix(String str, String suffix) {
        if (str == null || str.isEmpty() || suffix == null || suffix.isEmpty()) {
            return str;
        }
        String result = "";
        if (str.endsWith(suffix)) {
            result = str.substring(0, str.length() - suffix.length());
        } else {
            return str;
        }
        return result;
    }

    /***
     *
     * 获取value的SQL形式的字符串值，如果是String、Date类型，则加单引号，否则就不加
     *
     * @param value
     *
     * @author wengym
     *
     * @date 2022/3/17 15:30
     *
     * @return java.lang.String
     *
     */
    public static String getSQLStrValueByObject(Object value, String fieldType) {
        // short,int,long,float,double是存在默认值的，所以即使没有初始化，也会有值，而不会为null或为''
        String result = "null";
        if (value == null) {
            return result;
        }
        if ("java.lang.String".equals(fieldType) || "String".equals(fieldType)) {
            result = "'" + value + "'";
        } else if ("java.util.Date".equals(fieldType) || "Date".equals(fieldType)) {
            result = "'" + value.toString() + "'";
        } else {
            result = value + "";
        }
        return result;
    }

    /***
     *
     * 获取bean中的属性为attributeName的SQL形式的值
     *
     * @param attributeName
     *
     * @param bean
     *
     * @author wengym
     *
     * @date 2022/4/11 14:00
     *
     * @return java.lang.Object
     *
     */
    public static <T> String getBeanSQLValue(String attributeName, T bean) {
        if (DatabaseUtils.isNullStr(attributeName) || DatabaseUtils.isNullStr(bean)) {
            return null;
        }
        Class cls = bean.getClass();
        String resultValue = null;
        try {
            Field filed = cls.getDeclaredField(attributeName);
            String fieldName = filed.getName();
            String fieldType = filed.getType().getTypeName();
            String fieldGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method fieldGetMethod = cls.getMethod(fieldGetName);
            Object value = fieldGetMethod.invoke(bean);
            resultValue = DatabaseUtils.getSQLStrValueByObject(value, fieldType);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return resultValue;
    }

    /***
     *
     * 获取Map中的键值为attributeName的SQL形式的值
     *
     * @param attributeName
     *
     * @param cls
     *
     * @param paramMap
     *
     * @author wengym
     *
     * @date 2022/4/20 14:04
     *
     * @return java.lang.Object
     *
     */
    public static <T> String getMapSQLValue(Class<?> cls, String attributeName, Map<String, Object> paramMap) {
        if (DatabaseUtils.isNullStr(cls) || DatabaseUtils.isNullStr(attributeName) || DatabaseUtils.isNullStr(paramMap)) {
            return null;
        }
        String resultValue = null;
        try {
            Field filed = cls.getDeclaredField(attributeName);
            String fieldName = filed.getName();
            String fieldType = filed.getType().getTypeName();
            resultValue = DatabaseUtils.getSQLStrValueByObject(paramMap.get(fieldName), fieldType);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return resultValue;
    }

    /***
     *
     * 获取beanClass对应的类中的数据库字段名称列表（List）（由注解@DatabaseField标识的字段）
     *
     * @param beanClass
     *
     * @author wengym
     *
     * @date 2022/3/10 15:18
     *
     * @return java.lang.String[]
     *
     */
    public static List<String> getDatabaseFieldList(Class<?> beanClass) {
        // 获取本类中定义的字段，不包括继承的
        Field[] fields = beanClass.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            for (Annotation an : field.getAnnotations()) {
                if ("DatabaseField".equals(an.annotationType().getSimpleName())) {
                    list.add(field.getName());
                }
            }
        }
        return list;
    }

    /***
     *
     * 获取beanClass对应的类中的带有别名的数据库字段名称列表（List）（由注解@DatabaseField标识的字段）
     *
     * @param beanClass
     *
     * @author wengym
     *
     * @date 2022/3/10 15:18
     *
     * @return java.lang.String[]
     *
     */
    public static List<String> getDatabaseFieldList(Class<?> beanClass, String alias) {
        if (DatabaseUtils.isNullStr(alias)) {
            alias = "";
        } else {
            alias = alias.concat(".");
        }
        List<String> list = DatabaseUtils.getDatabaseFieldList(beanClass);
        List<String> aliasList = new ArrayList<>();
        for (String str : list) {
            aliasList.add(alias.concat(str));
        }
        return aliasList;
    }

    /***
     *
     * 获取beanClass对应的类中的数据库字段名称列表（List）（由注解@DatabaseField标识的字段），但是ignoreFieldSet集合中的除外
     *
     * @param beanClass
     *
     * @param ignoreFieldSet
     *
     * @author wengym
     *
     * @date 2022/4/26 10:15
     *
     * @return java.util.List<java.lang.String>
     *
     */
    public static List<String> getDatabaseFieldList(Class<?> beanClass, Set<String> ignoreFieldSet, String alias) {
        if (ignoreFieldSet == null) {
            ignoreFieldSet = new HashSet<>();
        }
        List<String> list = DatabaseUtils.getDatabaseFieldList(beanClass, alias);
        list.removeAll(ignoreFieldSet);
        return list;
    }

    /***
     *
     * 获取beanClass对应的类中的带有别名数据库字段名称数据（Array）（由注解@DatabaseField标识的字段）
     *
     * @param beanClass
     *
     * @author wengym
     *
     * @date 2022/4/26 15:18
     *
     * @return java.lang.String[]
     *
     */
    public static String[] getDatabaseFieldArr(Class<?> beanClass, String alias) {
        List<String> list = DatabaseUtils.getDatabaseFieldList(beanClass, alias);
        return list.toArray(new String[]{});
    }

    /***
     *
     * 获取beanClass对应的类中的数据库字段名称数据（Array）（由注解@DatabaseField标识的字段），但是ignoreFieldSet集合中的除外
     *
     * @param beanClass
     *
     * @author wengym
     *
     * @date 2022/4/26 15:18
     *
     * @return java.lang.String[]
     *
     */
    public static String[] getDatabaseFieldArr(Class<?> beanClass, Set<String> ignoreFieldSet, String alias) {
        List<String> list = DatabaseUtils.getDatabaseFieldList(beanClass, ignoreFieldSet, alias);
        return list.toArray(new String[]{});
    }
}
