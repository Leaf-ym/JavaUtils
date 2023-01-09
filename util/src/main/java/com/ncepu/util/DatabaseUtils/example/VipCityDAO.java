package com.ncepu.util.DatabaseUtils.example;

import com.ncepu.util.DatabaseUtils.BaseDaoProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 市级护理学会 - DAO
 * @date 2022/7/6 17:10
 */
//@Repository
public interface VipCityDAO {

    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/7/6 17:10
     *
     * @return java.lang.Integer
     *
     */
    @InsertProvider(type = VipTypeDaoProvider.class, method = "insert")
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
     * @date 2022/7/6 17:10
     *
     * @return java.lang.Integer
     *
     */
    @UpdateProvider(type = VipTypeDaoProvider.class, method = "update")
    Integer update(VipCityModel model, Set<String> whereFieldSet);

    /***
     *
     * 根据查询条件获取一条数据记录
     *
     * @param queryMap
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/6/8 10:33
     *
     * @return VipCityModel
     *
     */
    @SelectProvider(type = VipTypeDaoProvider.class, method = "getObject")
    VipCityModel getObject(Map<String, Object> queryMap, Class<VipCityModel> cls);

    /***
     *
     * 根据查询条件获取数据记录列表
     *
     * @param queryMap
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/6/8 10:38
     *
     * @return java.util.List<com.nursehealth.model.vip.VipCityModel>
     *
     */
    @SelectProvider(type = VipTypeDaoProvider.class, method = "listObjects")
    List<VipCityModel> listObjects(Map<String, Object> queryMap, Class<VipCityModel> cls);

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
     * @date 2022/6/8 16:56
     *
     * @return Integer
     *
     */
    @SelectProvider(type = VipTypeDaoProvider.class, method = "countListObjects")
    Integer countListObjects(Map<String, Object> queryMap, Class<VipCityModel> cls);

    class VipTypeDaoProvider extends BaseDaoProvider {

        public String insert(VipCityModel model) {
            String sql = super.commonInsert(model);
            return sql;
        }

        public String update(VipCityModel model, Set<String> whereFieldSet) {
            String sql = super.commonUpdate(model, whereFieldSet);
            return sql;
        }

        public String getObject(Map<String, Object> queryMap, Class<VipCityModel> cls) {
            String sql = super.commonGetObject(queryMap, cls);
            return sql;
        }

        public String listObjects(Map<String, Object> queryMap, Class<VipCityModel> cls) {
            String sql = super.commonListObjects(queryMap, cls);
            return sql;
        }

        public String countListObjects(Map<String, Object> queryMap, Class<VipCityModel> cls) {
            String sql = super.commonCountListObjects(queryMap, cls);
            return sql;
        }
    }
}
