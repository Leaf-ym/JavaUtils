package com.example.mysql.model;

import com.ncepu.util.DatabaseUtils.annotation.DatabaseField;
import com.ncepu.util.DatabaseUtils.annotation.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc 会员类别
 * @date 2022/6/8 10:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DatabaseTable(tableName = "vip_type", primaryKey = "id")
public class VipTypeModel {
    /**
     * 主键ID
     */
    @DatabaseField
    private Integer id;
    /**
     * 会员类别编码，唯一
     */
    @DatabaseField
    private String vipType;
    /**
     * 会员类别名称
     */
    @DatabaseField
    private String vipTypeName;
    /**
     * 会员等级，暂定1个级别
     */
    @DatabaseField
    private String vipLevel;
    /**
     * 会员费用
     */
    @DatabaseField
    private String money;
    /**
     * 会员时间
     */
    @DatabaseField
    private Integer vipTime;
    /**
     * 会员时间单位：YEAR年度，QUARTER季度，MONTH月度，DAY日度
     */
    @DatabaseField
    private String vipTimeUnit;
    /**
     * 是否有折扣：Y有，N无
     */
    @DatabaseField
    private String hasDiscount;
    /**
     * 折后金额
     */
    @DatabaseField
    private String discountMoney;
    /**
     * 折扣说明
     */
    @DatabaseField
    private String discountDesc;
    /**
     * 折扣截止时间
     */
    @DatabaseField
    private String discountEndDate;
    /**
     * 会员权益
     */
    @DatabaseField
    private String vipRight;
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
     * 更新时间
     */
    @DatabaseField
    private String updateDate;
    /**
     * 更新人
     */
    @DatabaseField
    private String updateBy;
}
