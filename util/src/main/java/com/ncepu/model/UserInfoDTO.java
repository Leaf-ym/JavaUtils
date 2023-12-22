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
public class UserInfoDTO {
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
    @ExcelProperty(value = "姓名")
    private String name;
    /**
     * 照片
     */
    @ExcelIgnore
    private String photo;
    /**
     * 性别
     */
    @ExcelProperty(value = "性别")
    private String gender;
    /**
     * 民族
     */
    @ExcelProperty(value = "民族")
    private String nation;
    /**
     * 政治面貌
     */
    @ExcelProperty(value = "政治面貌")
    private String political;
    /**
     * 是否连任
     */
    @ExcelProperty(value = "是否连任")
    private String isReElection;
    /**
     * 连任届期
     */
    @ExcelIgnore
    private String reElection;
    /**
     * 原学会任职
     */
    @ExcelIgnore
    private String pnaPost;
    /**
     * 拟任专委会
     */
    @ExcelProperty(value = "专委会")
    private String designatedSpecialCommittee;
    /**
     * 拟任职位
     */
    @ExcelProperty(value = "任职")
    private String designatedPosition;
    /**
     * 出生年月
     */
    @ExcelProperty(value = "出生年月")
    private String birth;
    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String idCard;
    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private String age;
    /**
     * 学历
     */
    @ExcelProperty(value = "学历")
    private String education;
    /**
     * 最高学历毕业院校
     */
    @ExcelProperty(value = "最高学历毕业院校")
    private String school;
    /**
     * 邮箱地址
     */
    @ExcelProperty(value = "邮箱地址")
    private String email;
    /**
     * 工作单位
     */
    @ExcelProperty(value = "工作单位")
    private String workUnit;
    /**
     * 办公电话
     */
    @ExcelProperty(value = "办公电话")
    private String officeTel;
    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String tel;
    /**
     * 工作部门
     */
    @ExcelProperty(value = "工作部门")
    private String department;
    /**
     * 职务
     */
    @ExcelProperty(value = "职务")
    private String workPost;
    /**
     * 职称
     */
    @ExcelProperty(value = "职称")
    private String positionTitle;
    /**
     * 通讯地址
     */
    @ExcelProperty(value = "通讯地址")
    private String address;
    /**
     * 邮编
     */
    @ExcelIgnore
    private String zipCode;
    /**
     * 单位属性
     */
    @ExcelProperty(value = "单位属性")
    private String property;
    /**
     * 是否党政机关处级以上领导干部兼职
     */
    @ExcelProperty(value = "是否党政机关处级以上领导干部兼职")
    private String isLeadingCadre;
    /**
     * 是否人大代表或政协委员
     */
    @ExcelProperty(value = "是否人大代表或政协委员")
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
    @ExcelProperty(value = "学术成绩")
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
    @ExcelProperty(value = "数据来源")
    private String dataFrom;

    @ExcelProperty(value = "名称")
    private String name11;
    @ExcelProperty(value = "任职")
    private String designatedPosition11;
    @ExcelProperty(value = "性别")
    private String gender11;
    @ExcelProperty(value = "出生年月")
    private String birth11;
    @ExcelProperty(value = "民族")
    private String nation11;
    @ExcelProperty(value = "政治面貌")
    private String political11;
    @ExcelProperty(value = "工作单位")
    private String workUnit11;
    @ExcelProperty(value = "工作部门")
    private String department11;
    @ExcelProperty(value = "职务")
    private String workPost11;
    @ExcelProperty(value = "职称")
    private String positionTitle11;
    @ExcelProperty(value = "学历")
    private String education11;
    @ExcelProperty(value = "工作单位")
    private String officeTel11;
    @ExcelProperty(value = "手机号")
    private String tel11;
    @ExcelProperty(value = "邮箱地址")
    private String email11;
    @ExcelProperty(value = "专委会")
    private String designatedSpecialCommittee11;
}
