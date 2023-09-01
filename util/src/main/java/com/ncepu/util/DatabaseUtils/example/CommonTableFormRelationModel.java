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
 * @desc 通用的数据库表和表单字段关联信息 - Model
 * @date 2023/8/25 13:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@DatabaseTable(tableName = "common_table_form_relation", primaryKey = "id")
public class CommonTableFormRelationModel {
    /**
     * 主键ID
     */
    @DatabaseField
    private Integer id;
    /**
     * 流程ID
     */
    @DatabaseField
    private Integer processId;
    /**
     * 数据库表ID
     */
    @DatabaseField
    private Integer tableId;
    /**
     * 表单ID
     */
    @DatabaseField
    private Integer formId;
    /**
     * 关联数据
     */
    @DatabaseField
    private String data;
    /**
     * 创建人
     */
    @DatabaseField
    private String createBy;
    /**
     * 创建时间
     */
    @DatabaseField
    private String fillDate;
    /**
     * 更新人
     */
    @DatabaseField
    private String updateBy;
    /**
     * 更新时间
     */
    @DatabaseField
    private String updateDate;
    /**
     * 是否删除：0正常，记录ID删除
     */
    @DatabaseField
    private Integer isDelete;
}
