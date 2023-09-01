package com.ncepu.util.DatabaseUtils.base.dao;

import com.ncepu.util.DatabaseUtils.BaseDaoProvider;
import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 数据层基础接口
 * T：服务层对应的数据库表的实体Model类型
 * F：字段类型
 * 接口是基本的增删改查
 * @date 2023/8/25 14:05
 */
//@Repository
public interface DatabaseBaseDAO<T, F> {
    /**
     * 插入
     *
     * @param model
     * @return java.lang.Integer
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @InsertProvider(type = CommonBaseDaoProvider.class, method = "insert")
    Integer insert(T model);

    /**
     * 删除
     *
     * @param id
     * @param cls
     * @return java.lang.Integer
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @DeleteProvider(type = CommonBaseDaoProvider.class, method = "delete")
    Integer delete(F id, Class<T> cls);

    /**
     * 更新
     *
     * @param model
     * @param whereFieldSet
     * @return java.lang.Integer
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @UpdateProvider(type = CommonBaseDaoProvider.class, method = "update")
    Integer update(T model, Set<String> whereFieldSet);

    /**
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     * @param cls
     * @return T
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @SelectProvider(type = CommonBaseDaoProvider.class, method = "getObject")
    T getObject(Map<String, Object> queryMap, Class<T> cls);

    /**
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     * @param cls
     * @return java.util.List<T>
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @SelectProvider(type = CommonBaseDaoProvider.class, method = "listObjects")
    List<T> listObjects(Map<String, Object> queryMap, Class<T> cls);

    /***
     *
     * 根据查询条件获取数据记录列表数目
     *
     * @param queryMap
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return Integer
     *
     */
    @SelectProvider(type = CommonBaseDaoProvider.class, method = "countListObjects")
    Integer countListObjects(Map<String, Object> queryMap, Class<T> cls);

    class CommonBaseDaoProvider<T, F> extends BaseDaoProvider {

        public String insert(T model) {
            String sql = super.commonInsert(model);
            return sql;
        }

        public String delete(F id, Class<T> cls) {
            return new SQL() {{
                DELETE_FROM(DatabaseUtils.getTableName(cls));
                WHERE("id = #{id}");
            }}.toString();
        }

        public String update(T model, Set<String> whereFieldSet) {
            String sql = super.commonUpdate(model, whereFieldSet);
            return sql;
        }

        public String getObject(Map<String, Object> queryMap, Class<T> cls) {
            String sql = super.commonGetObject(queryMap, cls);
            return sql;
        }

        public String listObjects(Map<String, Object> queryMap, Class<T> cls) {
            String sql = super.commonListObjects(queryMap, cls);
            return sql;
        }

        public String countListObjects(Map<String, Object> queryMap, Class<T> cls) {
            String sql = super.commonCountListObjects(queryMap, cls);
            return sql;
        }
    }
}
