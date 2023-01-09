package com.example.demo.service.impl;

import com.example.demo.dao.BaseDAO;
import com.example.demo.service.BaseService;
import com.ncepu.util.BeanUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 基础服务接口实现类
 * @date 2022/5/13 16:22
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Resource
    private BaseDAO<T> baseDAO;

    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/5/13 16:22
     *
     * @return java.lang.Integer
     *
     */
    @Override
    public Integer insert(T model) {
        Integer cnt = this.baseDAO.insert(model);
        if (cnt == null) {
            return 0;
        }
        return cnt;
    }

    /***
     *
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/5/17 9:33
     *
     * @return T
     *
     */
    @Override
    public T getObject(Map<String, Object> queryMap, Class<T> cls) {
        Map<String, Object> map = this.baseDAO.getObject(queryMap, cls);
        T model = BeanUtils.mapToBean(map, cls);
        return model;
    }
}
