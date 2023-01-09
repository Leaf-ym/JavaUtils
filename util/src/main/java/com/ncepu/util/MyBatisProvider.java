package com.ncepu.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * MyBatis经典代码记录
 *
 * @author: wengym
 * @date: 2021-11-22
 */
public interface MyBatisProvider {

    //@SelectProvider(type = MobileUserDAOProvider.class, method = "validateFieldValueUnique")
    int validateFieldValueUnique(Map<String, Object> queryMap);

    class MobileUserDAOProvider {

        /**
         * 用户注册信息表
         */
        public static final String TABLE_M_USER_REGISTER = "m_user_register";

        /**
         * 用户注册信息表，字段
         */
        public static final String[] TABLE_M_USER_REGISTER_COLUMN = {"id", "userId", "password", "sysId as organize", "updateDate as lastLoginDate", "registerFrom", "activeStatus", "teamCode"};

        public static final String[] TABLE_M_USER_REGISTER_COLUMN_A = {"a.id", "a.userId", "a.password", "a.sysId as organize", "a.updateDate as lastLoginDate", "a.registerFrom", "a.activeStatus", "a.teamCode"};

        public String validateFieldValueUnique(Map<String, Object> queryMap) {
            Set<String> keySet = queryMap.keySet();
            StringBuilder sb = new StringBuilder();
            for (String str : keySet) {
                if (StringUtils.equals(str, "tableName")) {
                    continue;
                }
                sb.append(" and ".concat(str).concat("=#{").concat(str).concat("}"));
            }
            /*return new SQL() {{
                SELECT("count(*)");
                FROM("${tableName}");
                WHERE("1 = 1 ".concat(sb.toString()));
            }}.toString();*/
            return null;
        }
    }

}
