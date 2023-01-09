package com.ncepu.util.DatabaseUtils.example;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 城市护理学会服务接口实现类
 * @date 2022/7/6 17:16
 */
//@Service
public class VipCityServiceImpl implements VipCityService {
    @Resource
    private VipCityDAO vipCityDAO;

    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/7/6 17:16
     *
     * @return java.lang.Integer
     *
     */
    @Override
    public Integer insert(VipCityModel model) {
        //model.setIsDelete(0);
        Integer cnt = this.vipCityDAO.insert(model);
        return cnt;
    }

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
     * @date 2022/7/6 17:16
     *
     * @return java.lang.Integer
     *
     */
    @Override
    public Integer update(VipCityModel model, Set<String> whereFieldSet) {
        Integer cnt = this.vipCityDAO.update(model, whereFieldSet);
        return cnt;
    }

    /***
     *
     * 更新
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/7/6 17:16
     *
     * @return java.lang.Integer
     *
     */
    @Override
    public Integer updateById(VipCityModel model) {
        Set<String> whereFieldSet = new HashSet<>();
        whereFieldSet.add("id");
        Integer cnt = this.update(model, whereFieldSet);
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
     * @date 2022/7/6 17:16
     *
     * @return VipPayModel
     *
     */
    @Override
    public VipCityModel getObject(Map<String, Object> queryMap) {
        //queryMap.put("isDelete", 0);
        VipCityModel model = this.vipCityDAO.getObject(queryMap, VipCityModel.class);
        return model;
    }

    /***
     *
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     *
     * @author wengym
     *
     * @date 2022/7/6 17:16
     *
     * @return java.util.List<com.nursehealth.model.vip.VipPayModel>
     *
     */
    @Override
    public List<VipCityModel> listObjects(Map<String, Object> queryMap) {
        //queryMap.put("isDelete", 0);
        List<VipCityModel> list = this.vipCityDAO.listObjects(queryMap, VipCityModel.class);
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
     * @date 2022/7/6 17:16
     *
     * @return Integer
     *
     */
    @Override
    public Integer countListObjects(Map<String, Object> queryMap) {
        //queryMap.put("isDelete", 0);
        Integer cnt = this.vipCityDAO.countListObjects(queryMap, VipCityModel.class);
        return cnt;
    }
}
