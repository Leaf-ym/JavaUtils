package com.example.mysql.dao;

import com.example.mysql.model.VipTypeModel;
import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wengym
 * @version 1.0
 * @desc 会员类别 - DAO
 * @date 2022/6/8 10:33
 */
@Repository
public interface VipTypeDAO {

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
    @InsertProvider(type = VipTypeDaoProvider.class, method = "insert")
    Integer insert(VipTypeModel model);

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
     * @return VipTypeModel
     *
     */
    @SelectProvider(type = VipTypeDaoProvider.class, method = "getObject")
    VipTypeModel getObject(Map<String, Object> queryMap, Class<VipTypeModel> cls);

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
     * @return java.util.List<com.nursehealth.model.vip.VipTypeModel>
     *
     */
    @SelectProvider(type = VipTypeDaoProvider.class, method = "listObjects")
    List<VipTypeModel> listObjects(Map<String, Object> queryMap, Class<VipTypeModel> cls);

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
    @DeleteProvider(type = VipTypeDaoProvider.class, method = "delete")
    Integer delete(Integer id);

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
    Integer countListObjects(Map<String, Object> queryMap, Class<VipTypeModel> cls);

    class VipTypeDaoProvider extends BaseDaoProvider {

        public String delete(Integer id) {
            return new SQL() {{
                DELETE_FROM(DatabaseUtils.getTableName(VipTypeModel.class));
                WHERE("id = #{id}");
            }}.toString();
        }

        public String insert(VipTypeModel model) {
            String sql = super.commonInsert(model);
            return sql;
        }

        public String getObject(Map<String, Object> queryMap, Class<VipTypeModel> cls) {
            String sql = super.commonGetObject(queryMap, cls);
            return sql;
        }

        public String listObjects(Map<String, Object> queryMap, Class<VipTypeModel> cls) {
            String sql = super.commonListObjects(queryMap, cls);
            return sql;
        }

        public String countListObjects(Map<String, Object> queryMap, Class<VipTypeModel> cls) {
            String sql = super.commonCountListObjects(queryMap, cls);
            return sql;
        }
    }
}
