package com.example.demo.service;

import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 基础的服务接口
 * @date 2022/5/13 16:21
 */
public interface BaseService<T> {
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
    Integer insert(T model);

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
    T getObject(Map<String, Object> queryMap, Class<T> cls);
}
