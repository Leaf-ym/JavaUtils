package com.ncepu.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoModel {
    /**
     * id
     */
    @ExcelIgnore
    private String id;
    /**
     * 换届信息id
     */
    @ExcelIgnore
    private String termId;
    /**
     * 所属二级单位
     */
    @ExcelIgnore
    private String sysUserId;
    @ExcelIgnore
    private String sysUserName;
    /**
     * 种类
     */
    @ExcelIgnore
    private String type;
    /**
     * 姓名
     */
    @ExcelProperty(value = "name")
    private String name;
    /**
     * 照片
     */
    @ExcelIgnore
    private String photo;
    /**
     * 性别
     */
    @ExcelProperty(value = "gender")
    private String gender;
    /**
     * 民族
     */
    @ExcelProperty(value = "nation")
    private String nation;
    /**
     * 政治面貌
     */
    @ExcelProperty(value = "political")
    private String political;
    /**
     * 是否连任
     */
    @ExcelProperty(value = "isReElection")
    private String isReElection;
    /**
     * 连任届期
     */
    @ExcelIgnore
    private String reElection;
    /**
     * 原学会任职
     */
    @ExcelProperty(value = "pnaPost")
    private String pnaPost;
    /**
     * 拟任专委会
     */
    @ExcelProperty(value = "designatedSpecialCommittee")
    private String designatedSpecialCommittee;
    /**
     * 拟任职位
     */
    @ExcelProperty(value = "designatedPosition")
    private String designatedPosition;
    /**
     * 出生年月
     */
    @ExcelProperty(value = "birth")
    private String birth;
    /**
     * 身份证号
     */
    @ExcelProperty(value = "idCard")
    private String idCard;
    /**
     * 年龄
     */
    @ExcelProperty(value = "age")
    private String age;
    /**
     * 学历
     */
    @ExcelProperty(value = "education")
    private String education;
    /**
     * 最高学历毕业院校
     */
    @ExcelProperty(value = "school")
    private String school;
    /**
     * 邮箱地址
     */
    @ExcelProperty(value = "email")
    private String email;
    /**
     * 工作单位
     */
    @ExcelProperty(value = "workUnit")
    private String workUnit;
    /**
     * 办公电话
     */
    @ExcelProperty(value = "officeTel")
    private String officeTel;
    /**
     * 手机号
     */
    @ExcelProperty(value = "tel")
    private String tel;
    /**
     * 工作部门
     */
    @ExcelProperty(value = "department")
    private String department;
    /**
     * 职务
     */
    @ExcelProperty(value = "workPost")
    private String workPost;
    /**
     * 职称
     */
    @ExcelProperty(value = "positionTitle")
    private String positionTitle;
    /**
     * 通讯地址
     */
    @ExcelProperty(value = "address")
    private String address;
    /**
     * 邮编
     */
    @ExcelIgnore
    private String zipCode;
    /**
     * 单位属性
     */
    @ExcelProperty(value = "property")
    private String property;
    /**
     * 是否党政机关处级以上领导干部兼职
     */
    @ExcelProperty(value = "isLeadingCadre")
    private String isLeadingCadre;
    /**
     * 是否人大代表或政协委员
     */
    @ExcelProperty(value = "isDeputy")
    private String isDeputy;
    /**
     * 其他社会任职
     */
    @ExcelIgnore
    private String socialPosition;
    /**
     * 主要教育经历
     */
    @ExcelIgnore
    private String learningExperience;
    /**
     * 本人主要简历
     */
    @ExcelIgnore
    private String resume;
    /**
     * 学术成绩
     */
    @ExcelProperty(value = "academicAchievement")
    private String academicAchievement;
    /**
     * 个人审核附件状态
     */
    @ExcelIgnore
    private String personalVerifyInclAttachment;
    /**
     * 个人审核附件地址
     */
    @ExcelIgnore
    private String personalVerifyAttachment;
    /**
     * 二级单位评审结果
     */
    @ExcelIgnore
    private String secondaryVerifyStatus;
    /**
     * 二级单位评审意见
     */
    @ExcelIgnore
    private String secondaryVerifyOpinion;
    /**
     * 二级单位评审附件上传状态
     */
    @ExcelIgnore
    private String secondaryVerifyInclAttachment;
    /**
     * 二级单位评审附件地址
     */
    @ExcelIgnore
    private String secondaryVerifyAttachment;
    /**
     * 二级单位上报中华护理学会状态
     */
    @ExcelIgnore
    private String secondaryUpStatus;
    /**
     * 二级单位评审时间
     */
    @ExcelIgnore
    private String secondaryVerifyTime;
    /**
     * 一级单位审核时间
     */
    @ExcelIgnore
    private String firstVerifyTime;
    /**
     * 一级单位评审结果
     */
    @ExcelIgnore
    private String firstVerifyStatus;
    /**
     * 一级单位评审意见
     */
    @ExcelIgnore
    private String firstVerifyOpinion;
    /**
     * 入库时间
     */
    @ExcelIgnore
    private String fillDate;
    /**
     * 届数
     */
    @ExcelIgnore
    private String termNo;
    /**
     * 上报单位
     */
    @ExcelIgnore
    private String unit;
    /**
     * 医院等级
     */
    @ExcelIgnore
    private String levelName;
    /**
     * 是否是主任委员：Y是，N否
     */
    @ExcelIgnore
    private String isChairman;
    /**
     * 登录手机号
     */
    @ExcelIgnore
    private String loginTel;
    /**
     * 数据来源
     */
    @ExcelProperty(value = "dataFrom")
    private String dataFrom;

    @ExcelProperty(value = "name11")
    private String name11;
    @ExcelProperty(value = "designatedPosition11")
    private String designatedPosition11;
    @ExcelProperty(value = "gender11")
    private String gender11;
    @ExcelProperty(value = "birth11")
    private String birth11;
    @ExcelProperty(value = "nation11")
    private String nation11;
    @ExcelProperty(value = "political11")
    private String political11;
    @ExcelProperty(value = "workUnit11")
    private String workUnit11;
    @ExcelProperty(value = "department11")
    private String department11;
    @ExcelProperty(value = "workPost11")
    private String workPost11;
    @ExcelProperty(value = "positionTitle11")
    private String positionTitle11;
    @ExcelProperty(value = "education11")
    private String education11;
    @ExcelProperty(value = "officeTel11")
    private String officeTel11;
    @ExcelProperty(value = "tel11")
    private String tel11;
    @ExcelProperty(value = "email11")
    private String email11;
    @ExcelProperty(value = "designatedSpecialCommittee11")
    private String designatedSpecialCommittee11;
}
