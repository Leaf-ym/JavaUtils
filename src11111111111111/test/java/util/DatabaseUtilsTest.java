package util;

import com.ncepu.model.NewUserBean;
import com.ncepu.util.DatabaseUtils;
import com.ncepu.util.PrintUtils;

import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 数据库工具类 - 测试类
 * @date 2022/4/21 10:00
 */
public class DatabaseUtilsTest {

    /***
     *
     * 测试批量更新
     *
     * @author wengym
     *
     * @date 2022/3/17 20:13
     *
     * @return void
     *
     */
    public static void batchUpdateTest() {
        NewUserBean newUserBean1 = new NewUserBean();
        newUserBean1.setId(100);
        newUserBean1.setMlId(1000);
        newUserBean1.setUserName("wengym");
        newUserBean1.setPassword("123456");
        newUserBean1.setUpdateDate(new Date());
        NewUserBean newUserBean2 = new NewUserBean();
        newUserBean2.setId(200);
        //newUserBean2.setMlId(2000);
        newUserBean2.setUserName("wangcl");
        //newUserBean2.setPassword("654321");
        List<NewUserBean> list = new ArrayList<>();
        list.add(newUserBean1);
        list.add(newUserBean2);
        Set<String> ignoreFieldSet = new HashSet<>();
        //ignoreFieldSet.add("id");
        Map<String, Object> databaseValue = new HashMap<>();
        databaseValue.put("updateDate", "now()");
        String tableName = "vip_hospital";
        Set<String> whereSet = new HashSet<>();
        whereSet.add("id");
        whereSet.add("mlId");
        String result = DatabaseUtils.batchUpdate(tableName, NewUserBean.class, list, ignoreFieldSet, databaseValue, whereSet, true);
        PrintUtils.println(result);
    }


    /***
     *
     * 测试批量插入
     *
     * @author wengym
     *
     * @date 2022/3/17 14:14
     *
     * @return void
     *
     */
    public static void batchInsertTest() {
        NewUserBean newUserBean1 = new NewUserBean();
        newUserBean1.setId(100);
        newUserBean1.setMlId(1000);
        newUserBean1.setUserName("wengym");
        newUserBean1.setPassword("123456");
        newUserBean1.setUpdateDate(new Date());
        NewUserBean newUserBean2 = new NewUserBean();
        newUserBean2.setId(200);
        //newUserBean2.setMlId(2000);
        newUserBean2.setUserName("wangcl");
        //newUserBean2.setPassword("654321");
        List<NewUserBean> list = new ArrayList<>();
        list.add(newUserBean1);
        list.add(newUserBean2);
        Set<String> ignoreFieldSet = new HashSet<>();
        ignoreFieldSet.add("id");
        Map<String, Object> databaseValue = new HashMap<>();
        databaseValue.put("fillDate", "now()");
        String tableName = "vip_hospital";
        String result = DatabaseUtils.batchInsert(tableName, NewUserBean.class, list, ignoreFieldSet, databaseValue);
        PrintUtils.println(result);
    }

    /***
     *
     * 测试删除数据库记录的SQL语句构造
     *
     * @author wengym
     *
     * @date 2022/4/24 10:23
     *
     * @return void
     *
     */
    public static void deleteTest() {
        String tableName = "user";
        Class cls = NewUserBean.class;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("id", 10);
        queryMap.put("iiid", 20);
        queryMap.put("userName", "wengym");
        Set<String> ignoreQueryFieldSet = new HashSet<>();
        ignoreQueryFieldSet.add("updateDate");
        Boolean isEmptyNotQuery = true;
        String sql = DatabaseUtils.delete(tableName, cls, queryMap, ignoreQueryFieldSet, isEmptyNotQuery);
        PrintUtils.println(sql);
    }

    /***
     *
     * 测试获取通用搜索条件语句
     *
     * @author wengym
     *
     * @date 2022/3/17 14:14
     *
     * @return void
     *
     */
    public static void commonSearchTest() {
        NewUserBean newUserBean1 = new NewUserBean();
        newUserBean1.setId(100);
        newUserBean1.setMlId(1000);
        newUserBean1.setUserName("wengym");
        newUserBean1.setPassword("123456");
        newUserBean1.setUpdateDate(new Date());

        Set<String> ignoreFieldSet = new HashSet<>();
        //ignoreFieldSet.add("id");
        String alias = "ab";
        Boolean isEmptyNotUpdate = true;
        String result = DatabaseUtils.commonSearch(newUserBean1, ignoreFieldSet, alias, isEmptyNotUpdate);
        PrintUtils.println(result);
    }

    /***
     *
     * 测试获取一个记录的SQL语句构造
     *
     * @author wengym
     *
     * @date 2022/4/20 14:14
     *
     * @return void
     *
     */
    public static void getObjectTest() {
        String tableName = "user";
        Class cls = NewUserBean.class;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("id", 10);
        queryMap.put("iiid", 20);
        queryMap.put("userName", "wengym");
        Set<String> ignoreSelectFieldSet = new HashSet<>();
        Set<String> ignoreQueryFieldSet = new HashSet<>();
        Boolean isEmptyNotQuery = false;
        String sql = DatabaseUtils.getObject(tableName, cls, queryMap, ignoreSelectFieldSet, ignoreQueryFieldSet, isEmptyNotQuery);
        PrintUtils.println(sql);
    }

    /***
     *
     * 测试获取多条记录的SQL语句构造
     *
     * @author wengym
     *
     * @date 2022/4/20 14:14
     *
     * @return void
     *
     */
    public static void listObjectsTest() {
        String tableName = "user";
        Class cls = NewUserBean.class;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("id", 10);
        queryMap.put("iiid", 20);
        queryMap.put("userName", "wengym");
        //queryMap.put("pageIndex", 5);
        //queryMap.put("pageSize", 100);
        Set<String> ignoreSelectFieldSet = new HashSet<>();
        ignoreSelectFieldSet.add("mlId");
        Set<String> ignoreQueryFieldSet = new HashSet<>();
        ignoreQueryFieldSet.add("updateDate");
        String orderByStr = "ORDER BY fillDate DESC";
        Boolean isEmptyNotQuery = false;
        String sql = DatabaseUtils.listObjects(tableName, cls, queryMap, ignoreSelectFieldSet, ignoreQueryFieldSet, orderByStr, isEmptyNotQuery);
        PrintUtils.println(sql);
    }

    /***
     *
     * 测试统计符合条件的记录数目的SQL语句构造
     *
     * @author wengym
     *
     * @date 2022/4/20 14:14
     *
     * @return void
     *
     */
    public static void countListObjectsTest() {
        String tableName = "user";
        Class cls = NewUserBean.class;
        String statisticalField = "*";
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("id", 10);
        queryMap.put("iiid", 20);
        queryMap.put("userName", "wengym");
        queryMap.put("pageIndex", 5);
        queryMap.put("pageSize", 100);
        Set<String> ignoreQueryFieldSet = new HashSet<>();
        ignoreQueryFieldSet.add("updateDate");
        Boolean isEmptyNotQuery = false;
        String sql = DatabaseUtils.countListObjects(tableName, cls, statisticalField, queryMap, ignoreQueryFieldSet, isEmptyNotQuery);
        PrintUtils.println(sql);
    }

    /***
     *
     * 测试插入一条记录的SQL语句构造
     *
     * @author wengym
     *
     * @date 2022/4/21 11:11
     *
     * @return void
     *
     */
    public static void insertTest() {
        String tableName = "user";
        Class cls = NewUserBean.class;
        NewUserBean newUserBean1 = new NewUserBean();
        newUserBean1.setId(100);
        newUserBean1.setMlId(1000);
        newUserBean1.setUserName("wengym");
        newUserBean1.setPassword("123456");
        Set<String> ignoreFieldSet = new HashSet<>();
        ignoreFieldSet.add("id");
        Map<String, Object> databaseValue = new HashMap<>();
        databaseValue.put("fillDate", "now()");
        String sql = DatabaseUtils.insert(tableName, cls, newUserBean1, ignoreFieldSet, databaseValue);
        PrintUtils.println(sql);
    }

    /***
     *
     * 测试更新一条记录的SQL语句构造
     *
     * @author wengym
     *
     * @date 2022/4/21 11:11
     *
     * @return void
     *
     */
    public static void updateTest() {
        String tableName = "user";
        Class cls = NewUserBean.class;
        NewUserBean model = new NewUserBean();
        model.setId(100);
        model.setMlId(1000);
        model.setUserName("wengym");
        model.setPassword("123456");
        Set<String> ignoreFieldSet = new HashSet<>();
        ignoreFieldSet.add("fillDate");
        Map<String, Object> databaseValue = new HashMap<>();
        databaseValue.put("updateDate", "now()");
        databaseValue.put("password", null);
        Set<String> whereFieldSet = new HashSet<>();
        whereFieldSet.add("id");
        Boolean isEmptyNotUpdate = true;
        String sql = DatabaseUtils.update(tableName, cls, model, ignoreFieldSet, databaseValue, whereFieldSet, isEmptyNotUpdate);
        PrintUtils.println(sql);
    }

    /***
     *
     * 生成数据库表字段的数组
     *
     * @author wengym
     *
     * @date 2022/4/26 10:12
     *
     * @return void
     *
     */
    public static void getDatabaseFieldArrTest() {
        Class cls = NewUserBean.class;
        String[] arr = DatabaseUtils.getDatabaseFieldArr(cls, null);
        Set<String> ignoreFieldSet = new HashSet<>();
        ignoreFieldSet.add("mlId");
        String[] arr1 = DatabaseUtils.getDatabaseFieldArr(cls, ignoreFieldSet, "t1");
        PrintUtils.println(arr);
        PrintUtils.println(arr1);
    }

    public static void main(String[] args) {
        //PrintUtils.println(DatabaseUtils.getSelectSubStatement(NewUserBean.class, null));
        //DatabaseUtilsTest.insertTest();
        //DatabaseUtilsTest.batchInsertTest();
        //DatabaseUtilsTest.deleteTest();
        DatabaseUtilsTest.updateTest();
        //DatabaseUtilsTest.batchUpdateTest();
        //DatabaseUtilsTest.commonSearchTest();
        //DatabaseUtilsTest.getObjectTest();
        //DatabaseUtilsTest.listObjectsTest();
        //DatabaseUtilsTest.countListObjectsTest();
        //DatabaseUtilsTest.getDatabaseFieldArrTest();
    }
}
