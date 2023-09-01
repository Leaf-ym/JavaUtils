package com.ncepu.util.DatabaseUtils.base.service.impl;

import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import com.ncepu.util.DatabaseUtils.base.dao.DatabaseBaseDAO;
import com.ncepu.util.DatabaseUtils.base.service.DatabaseBaseService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 服务层基础接口实现类
 * T：服务层对应的数据库表的实体Model类型
 * F：字段类型
 * 接口是基本的增删改查
 * @date 2023/8/25 14:05
 */
//@Service
public class DatabaseBaseServiceImpl<T, F> implements DatabaseBaseService<T, F> {
    @Resource
    protected DatabaseBaseDAO<T, F> databaseBaseDAO;

    /**
     * 插入
     *
     * @param model
     * @return java.lang.Integer
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Override
    public Integer insert(T model) {
        DatabaseUtils.setFieldValue("isDelete", "0", model);
        Integer cnt = this.databaseBaseDAO.insert(model);
        return cnt;
    }

    /**
     * 删除（真删）
     *
     * @param id
     * @return java.lang.Integer
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Override
    public Integer delete(F id) {
        Class<T> cls = DatabaseUtils.getActualTypeArgument(this.getClass(), 0);
        Integer cnt = this.databaseBaseDAO.delete(id, cls);
        return cnt;
    }

    /**
     * 更新
     *
     * @param model
     * @param whereFieldSet
     * @return java.lang.Integer
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Override
    public Integer update(T model, Set<String> whereFieldSet) {
        Integer cnt = this.databaseBaseDAO.update(model, whereFieldSet);
        return cnt;
    }

    /**
     * 更新
     *
     * @param model
     * @return java.lang.Integer
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Override
    public Integer updateById(T model) {
        Integer cnt = this.update(model, DatabaseUtils.asSet("id"));
        return cnt;
    }

    /**
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     * @return T
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Override
    public T getObject(Map<String, Object> queryMap) {
        queryMap.put("isDelete", 0);
        Class<T> cls = DatabaseUtils.getActualTypeArgument(this.getClass(), 0);
        T model = this.databaseBaseDAO.getObject(queryMap, cls);
        return model;
    }

    /**
     * 根据查询条件获取一条数据记录
     *
     * @param id
     * @return T
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Override
    public T getObjectById(F id) {
        T model = this.getObject(new HashMap<String, Object>() {{
            put("id", id);
        }});
        return model;
    }

    /**
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     * @return java.util.List<T>
     * @author wengym
     * @date 2023/8/25 14:09
     */
    @Override
    public List<T> listObjects(Map<String, Object> queryMap) {
        queryMap.put("isDelete", 0);
        Class<T> cls = DatabaseUtils.getActualTypeArgument(this.getClass(), 0);
        List<T> list = this.databaseBaseDAO.listObjects(queryMap, cls);
        return list;
    }

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
    @Override
    public Integer countListObjects(Map<String, Object> queryMap) {
        queryMap.put("isDelete", 0);
        Class<T> cls = DatabaseUtils.getActualTypeArgument(this.getClass(), 0);
        Integer cnt = this.databaseBaseDAO.countListObjects(queryMap, cls);
        return cnt;
    }
}
