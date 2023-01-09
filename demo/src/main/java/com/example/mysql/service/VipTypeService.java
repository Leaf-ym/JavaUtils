package com.example.mysql.service;

import com.example.mysql.model.VipTypeModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 会员类别服务接口
 * @date 2022/6/8 11:36
 */
public interface VipTypeService {

    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/6/8 10:53
     *
     * @return java.lang.Integer
     *
     */
    Integer insert(VipTypeModel model);

    /***
     *
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/6/8 10:53
     *
     * @return VipTypeModel
     *
     */
    VipTypeModel getObject(Map<String, Object> queryMap);

    /***
     *
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/6/8 10:53
     *
     * @return java.util.List<com.nursehealth.model.vip.VipTypeModel>
     *
     */
    List<VipTypeModel> listObjects(Map<String, Object> queryMap);

    /***
     *
     * 根据查询条件获取数据记录列表数目
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/6/8 16:56
     *
     * @return Integer
     *
     */
    Integer countListObjects(Map<String, Object> queryMap);

    /**
     * 删除
     *
     * @param id
     *
     * @author wengym
     *
     * @date 2022/10/26 15:44
     *
     * @return java.lang.Integer
     */
    Integer delete(Integer id);
}
