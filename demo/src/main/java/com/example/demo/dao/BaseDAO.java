package com.example.demo.dao;

import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc DAO层的基础接口
 * @date 2022/5/13 15:26
 */
@Repository
public interface BaseDAO<T> {
    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/5/13 15:33
     *
     * @return java.lang.Integer
     *
     */
    @InsertProvider(type = BaseDaoProvider.class, method = "insert")
    Integer insert(T model);

    /***
     *
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/5/17 9:19
     *
     * @return T
     *
     */
    @SelectProvider(type = BaseDaoProvider.class, method = "getObject")
    Map<String, Object> getObject(Map<String, Object> queryMap, Class<T> cls);

    class BaseDaoProvider<T> {

        public String insert(T model) {
            Class cls = model.getClass();
            String tableName = DatabaseUtils.getTableName(cls);
            String primaryKey = DatabaseUtils.getPrimaryKey(cls);
            Set<String> ignoreFieldSet = new HashSet<>();
            Map<String, Object> databaseValue = new HashMap<>();
            String sql = DatabaseUtils.insert(tableName, cls, model, ignoreFieldSet, databaseValue);
            return sql;
        }

        /***
         *
         * 根据查询条件获取一条数据记录
         *
         * @param queryMap
         *
         * @author wengym
         *
         * @date 2022/5/17 9:19
         *
         * @return T
         *
         */
        public String getObject(Map<String, Object> queryMap, Class<T> cls) {
            String tableName = DatabaseUtils.getTableName(cls);
            Set<String> ignoreSelectFieldSet = new HashSet<>();
            Set<String> ignoreQueryFieldSet = new HashSet<>();
            Boolean isEmptyNotQuery = true;
            String sql = DatabaseUtils.getObject(tableName, cls, queryMap, ignoreSelectFieldSet, ignoreQueryFieldSet, isEmptyNotQuery);
            return sql;
        }
    }
}
