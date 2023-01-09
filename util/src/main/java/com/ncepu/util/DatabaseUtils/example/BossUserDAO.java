package com.ncepu.util.DatabaseUtils.example;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import com.ncepu.util.DatabaseUtils.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 用户 - DAO
 * @date 2022/6/21 11:39
 */
//@Repository
public interface BossUserDAO {

    /***
     *
     * 插入
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/6/21 11:41
     *
     * @return java.lang.Integer
     *
     */
    @InsertProvider(type = UserDaoProvider.class, method = "insert")
    Integer insert(UserModel model);

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
     * @date 2022/6/21 11:41
     *
     * @return java.lang.Integer
     *
     */
    @InsertProvider(type = UserDaoProvider.class, method = "update")
    Integer update(UserModel model, Set<String> whereFieldSet);

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
     * @date 2022/6/21 11:41
     *
     * @return UserShareModel
     *
     */
    @SelectProvider(type = UserDaoProvider.class, method = "getObject")
    UserModel getObject(Map<String, Object> queryMap, Class<UserModel> cls);

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
     * @date 2022/6/21 11:41
     *
     * @return List<UserModel>
     *
     */
    @SelectProvider(type = UserDaoProvider.class, method = "listObjects")
    List<UserModel> listObjects(Map<String, Object> queryMap, Class<UserModel> cls);

    /***
     *
     * 根据查询条件获取数据记录数目
     *
     * @param queryMap
     *
     * @param cls
     *
     * @author wengym
     *
     * @date 2022/6/21 11:41
     *
     * @return Integer
     *
     */
    @SelectProvider(type = UserDaoProvider.class, method = "countListObjects")
    Integer countListObjects(Map<String, Object> queryMap, Class<UserModel> cls);

    class UserDaoProvider extends BaseDaoProvider {

        public String insert(UserModel model) {
            String sql = super.commonInsert(model);
            return sql;
        }

        public String update(UserModel model, Set<String> whereFieldSet) {
            String sql = super.commonUpdate(model, whereFieldSet);
            return sql;
        }

        public String getObject(Map<String, Object> queryMap, Class<UserModel> cls) {
            String sql = super.commonGetObject(queryMap, cls);
            return sql;
        }

        public String listObjects(Map<String, Object> queryMap, Class<UserModel> cls) {
            String sql = super.commonListObjects(queryMap, cls);
            return sql;
        }

        public String countListObjects(Map<String, Object> queryMap, Class<UserModel> cls) {
            String sql = super.commonCountListObjects(queryMap, cls);
            return sql;
        }
    }
}
