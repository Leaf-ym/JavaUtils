package com.ncepu.model;

import com.ncepu.util.DatabaseUtils.annotation.DatabaseField;
import com.ncepu.util.DatabaseUtils.annotation.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc 用户注册model
 * @date 2022/5/13 15:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DatabaseTable(tableName = "mm_user_register", primaryKey = "id")
public class UserRegisterModel {
    /**
     * 32位uuid
     */
    @DatabaseField(dataType = "BIGINT(20)", constraint = "PRIMARY KEY", comment = "主键ID")
    private String id;
    /**
     * 手机号
     */
    @DatabaseField(dataType = "VARCHAR(255)", constraint = "NOT NULL", defaultValue = "''", comment = "用户ID")
    private String userId;
    /**
     * 密码
     */
    @DatabaseField(dataType = "VARCHAR(64)", constraint = "NOT NULL", defaultValue = "''", comment = "密码")
    private String password;
    /**
     * 插入时间
     */
    @DatabaseField(dataType = "DATETIME", constraint = "NOT NULL", defaultValue = "now()", comment = "插入时间")
    private String fillDate;
    /**
     * 更新时间
     */
    @DatabaseField(dataType = "DATETIME", constraint = "NOT NULL", defaultValue = "''", comment = "更新时间")
    private String updateDate;

}
