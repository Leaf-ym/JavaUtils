package com.ncepu.util.DatabaseUtils.example;

import com.ncepu.util.DatabaseUtils.annotation.DatabaseField;
import com.ncepu.util.DatabaseUtils.annotation.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc 用户 - model
 * @date 2022/6/21 11:43
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DatabaseTable(tableName = "user", primaryKey = "id")
public class UserModel {
    @DatabaseField
    private String id;
    @DatabaseField
    private String userName;
    @DatabaseField
    private String password;
    @DatabaseField
    private String content;
}
