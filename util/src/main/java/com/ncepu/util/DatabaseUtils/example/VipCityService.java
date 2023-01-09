package com.ncepu.util.DatabaseUtils.example;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 市级护理学会服务接口
 * @date 2022/7/6 17:14
 */
public interface VipCityService {

    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/7/6 17:14
     *
     * @return java.lang.Integer
     *
     */
    Integer insert(VipCityModel model);

    /***
     *
     * 更新
     *
     * @param model
     *
     * @param whereFieldSet
     *
     * @author wengym
     *
     * @date 2022/7/6 17:14
     *
     * @return java.lang.Integer
     *
     */
    Integer update(VipCityModel model, Set<String> whereFieldSet);

    /***
     *
     * 更新
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/6/8 16:28
     *
     * @return java.lang.Integer
     *
     */
    Integer updateById(VipCityModel model);

    /***
     *
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/7/6 17:14
     *
     * @return VipCityModel
     *
     */
    VipCityModel getObject(Map<String, Object> queryMap);

    /***
     *
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/7/6 17:14
     *
     * @return java.util.List<com.nursehealth.model.vip.VipCityModel>
     *
     */
    List<VipCityModel> listObjects(Map<String, Object> queryMap);

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
}
