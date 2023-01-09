package com.ncepu.util.DatabaseUtils.example;

import com.ncepu.util.DatabaseUtils.annotation.DatabaseField;
import com.ncepu.util.DatabaseUtils.annotation.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 
 * @author wengym
 * @version 1.0
 * @desc 市级护理学会账户model
 * @date 2022/7/6 16:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DatabaseTable(tableName = "vip_city", primaryKey = "id")
public class VipCityModel {
    /**
     * 主键ID
     */
    @DatabaseField
    private Integer id;
    /**
     * 市级护理学会代码
     */
    @DatabaseField
    private String cityCode;
    /**
     * 市级护理学会名称
     */
    @DatabaseField
    private String cityName;
    /**
     * 学会账号
     */
    @DatabaseField
    private String account;
    /**
     * 省份
     */
    @DatabaseField
    private String province;
    /**
     * 城市
     */
    @DatabaseField
    private String city;
    /**
     * 区县
     */
    @DatabaseField
    private String town;
    /**
     * 联系地址
     */
    @DatabaseField
    private String contactAddress;
    /**
     * 邮编
     */
    @DatabaseField
    private String zipCode;
    /**
     * 公共电话
     */
    @DatabaseField
    private String publicCell;
    /**
     * 公共邮箱
     */
    @DatabaseField
    private String publicEmail;
    /**
     * 联系人姓名
     */
    @DatabaseField
    private String contactPerson;
    /**
     * 联系人手机号
     */
    @DatabaseField
    private String contactTel;
    /**
     * 税号
     */
    @DatabaseField
    private String taxNo;
    /**
     * 地址
     */
    @DatabaseField
    private String address;
    /**
     * 电话
     */
    @DatabaseField
    private String cell;
    /**
     * 开户行
     */
    @DatabaseField
    private String bank;
    /**
     * 开户行账号
     */
    @DatabaseField
    private String cardNo;
    /**
     * 抬头
     */
    @DatabaseField
    private String invoiceTitle;
    /**
     * 插入时间
     */
    @DatabaseField
    private Date fillDate;
    /**
     * 更新时间
     */
    @DatabaseField
    private Date updateDate;
}