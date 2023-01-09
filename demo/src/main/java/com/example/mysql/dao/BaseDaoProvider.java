package com.example.mysql.dao;

import com.ncepu.util.DatabaseUtils.DatabaseUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 数据库操作基础类
 * @date 2022/6/8 10:58
 */
public class BaseDaoProvider {

    /***
     *
     * 通用的插入语句构造方法
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/6/8 11:43
     *
     * @return java.lang.String
     *
     */
    public <T> String commonInsert(T model) {
        String tableName = DatabaseUtils.getTableName(model.getClass());
        Class cls = model.getClass();
        Set<String> ignoreFieldSet = new HashSet<>();
        ignoreFieldSet.add(DatabaseUtils.getPrimaryKey(model.getClass()));
        Map<String, Object> databaseValue = new HashMap<>();
        databaseValue.put("fillDate", "now()");
        String sql = DatabaseUtils.insert(tableName, cls, model, ignoreFieldSet, databaseValue);
        return sql;
    }

    /***
     *
     * 通用的更新语句构造方法
     *
     * @param model
     *
     * @param whereFieldSet
     *
     * @author wengym
     *
     * @date 2022/6/8 11:44
     *
     * @return java.lang.String
     *
     */
    public <T> String commonUpdate(T model, Set<String> whereFieldSet) {
        String tableName = DatabaseUtils.getTableName(model.getClass());
        Class cls = model.getClass();
        Set<String> ignoreFieldSet = new HashSet<>();
        Map<String, Object> databaseValue = new HashMap<>();
        databaseValue.put("updateDate", "now()");
        if (whereFieldSet == null) {
            whereFieldSet = new HashSet<>();
        }
        Boolean isEmptyNotUpdate = true;
        String sql = DatabaseUtils.update(tableName, cls, model, ignoreFieldSet, databaseValue, whereFieldSet, isEmptyNotUpdate);
        return sql;
    }

    /**
     * 通用的删除语句构造方法
     *
     * @param
     *
     * @author wengym
     *
     * @date 2022/7/26 14:29
     *
     * @return java.lang.String
     */
    public <T> String commonDelete(Class<?> cls, Map<String, Object> queryMap, Set<String> ignoreQueryFieldSet) {
        String tableName = DatabaseUtils.getTableName(cls);
        if (ignoreQueryFieldSet == null) {
            ignoreQueryFieldSet = new HashSet<>();
        }
        Boolean isEmptyNotUpdate = true;
        String sql = DatabaseUtils.delete(tableName, cls, queryMap, ignoreQueryFieldSet, isEmptyNotUpdate);
        return sql;
    }

    /***
     *
     * 通用的单条记录查询语句构造方法
     *
     * @param queryMap
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/6/8 11:44
     *
     * @return java.lang.String
     *
     */
    public <T> String commonGetObject(Map<String, Object> queryMap, Class<T> cls) {
        String tableName = DatabaseUtils.getTableName(cls);
        Set<String> ignoreSelectFieldSet = new HashSet<>();
        Set<String> ignoreQueryFieldSet = new HashSet<>();
        Boolean isEmptyNotQuery = true;
        String sql = DatabaseUtils.getObject(tableName, cls, queryMap, ignoreSelectFieldSet, ignoreQueryFieldSet, isEmptyNotQuery);
        return sql;
    }

    /***
     *
     * 通用的多条记录查询语句构造方法
     *
     * @param queryMap
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/6/8 11:45
     *
     * @return java.lang.String
     *
     */
    public <T> String commonListObjects(Map<String, Object> queryMap, Class<T> cls) {
        String tableName = DatabaseUtils.getTableName(cls);
        Set<String> ignoreSelectFieldSet = new HashSet<>();
        Set<String> ignoreQueryFieldSet = new HashSet<>();
        String orderByStr = "ORDER BY fillDate DESC";
        Boolean isEmptyNotQuery = true;
        String sql = DatabaseUtils.listObjects(tableName, cls, queryMap, ignoreSelectFieldSet, ignoreQueryFieldSet, orderByStr, isEmptyNotQuery);
        return sql;
    }

    /***
     *
     * 通用的多条记录数目查询语句构造方法
     *
     * @param
     *
     * @author wengym
     *
     * @date 2022/6/8 16:52
     *
     * @return java.lang.String
     *
     */
    public <T> String commonCountListObjects(Map<String, Object> queryMap, Class<T> cls) {
        String tableName = DatabaseUtils.getTableName(cls);
        String statisticalField = "*";
        Set<String> ignoreQueryFieldSet = new HashSet<>();
        Boolean isEmptyNotQuery = true;
        String sql = DatabaseUtils.countListObjects(tableName, cls, statisticalField, queryMap, ignoreQueryFieldSet, isEmptyNotQuery);
        return sql;
    }
}
