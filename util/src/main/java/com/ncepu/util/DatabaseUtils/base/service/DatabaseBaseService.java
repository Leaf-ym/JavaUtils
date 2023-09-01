package com.ncepu.util.DatabaseUtils.base.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 服务层基础接口
 * T：服务层对应的数据库表的实体Model类型
 * F：字段类型
 * 接口是基本的增删改查
 * @date 2023/8/25 14:05
 */
public interface DatabaseBaseService<T, F> {
    /**
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return java.lang.Integer
     */
    Integer insert(T model);

    /**
     * 删除（真删）
     *
     * @param id
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return java.lang.Integer
     */
    Integer delete(F id);

    /**
     * 更新
     *
     * @param model
     *
     * @param whereFieldSet
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return java.lang.Integer
     */
    Integer update(T model, Set<String> whereFieldSet);

    /**
     * 更新
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return java.lang.Integer
     */
    Integer updateById(T model);

    /**
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return T
     */
    T getObject(Map<String, Object> queryMap);

    /**
     * 根据查询条件获取一条数据记录
     *
     * @param id
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return T
     */
    T getObjectById(F id);

    /**
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return java.util.List<T>
     */
    List<T> listObjects(Map<String, Object> queryMap);

    /***
     *
     * 根据查询条件获取数据记录列表数目
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2023/8/25 14:09
     *
     * @return Integer
     *
     */
    Integer countListObjects(Map<String, Object> queryMap);
}
