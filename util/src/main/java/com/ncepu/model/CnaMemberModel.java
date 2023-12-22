package com.ncepu.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ncepu.util.DatabaseUtils.annotation.DatabaseField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (CnaMember)实体类
 *
 * @author makejava
 * @since 2019-10-22 22:05:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CnaMemberModel {

    /**
     * Excel导入，标记行数
     */
    
    private String numIndex;

    /**
     * Excel导出，标记行数
     */
    @ExcelProperty("index")
    private String index;

    /**
     * 所属系统
     */
    
    private String sysId;

    
    @DatabaseField
    private Long id;

    /**
     * 注册表唯一ID ym
     */
    
    @DatabaseField
    private Long orgUserId;

    /**
     * 所属专业委员会或分会
     */
    @ExcelProperty("professionalCommittee")
    @ColumnWidth(30)
    @DatabaseField
    private String professionalCommittee;

    /***
     * 会员登记号（会员号）
     */
    @ExcelProperty("cnaNumber")
    @ColumnWidth(15)
    @DatabaseField
    private String cnaNumber;

    /**
     * 会员姓名
     */
    @ColumnWidth(12)
    @ExcelProperty("userName")
    @DatabaseField
    private String userName;
    /**
     * <p>
     * /**
     * 所在地区：省市区连接字符串
     */
    @ExcelProperty("provinceCityTown")
    @ColumnWidth(12)
    private String provinceCityTown;

    /**
     * 省份
     */
    
    @DatabaseField
    private String province;

    @ExcelProperty("provinceLabel")
    private String provinceLabel;

    /**
     * 城市
     */
    
    @DatabaseField
    private String city;

    @ExcelProperty("cityLabel")
    private String cityLabel;

    /**
     * 区县
     */
    
    @DatabaseField
    private String town;

    @ExcelProperty("townLabel")
    private String townLabel;

    /**
     * 性别：男即是男，女即是女，不论1和2
     */
    @ExcelProperty(value = "gender")
    @DatabaseField
    private String gender;

    
    @DatabaseField
    private String nation;

    @ExcelProperty("nationLabel")
    @DatabaseField
    private String nationLabel;


    /**
     * 政治面貌
     */
    
    @DatabaseField
    private String politic;

    @ExcelProperty("politicLabel")
    @ColumnWidth(12)
    @DatabaseField
    private String politicLabel;

    /**
     * 宗教信仰
     */
    @ExcelProperty("faith")
    @ColumnWidth(12)
    @DatabaseField
    private String faith;

    /**
     * 出生年
     */
    @ExcelProperty("yearOfBirth")
    @DatabaseField
    private String yearOfBirth;

    /**
     * 出生月
     */
    @ExcelProperty("monthOfBirth")
    @DatabaseField
    private String monthOfBirth;

    /**
     * 出生日
     */
    @ExcelProperty("dayOfBirth")
    @DatabaseField
    private String dayOfBirth;

    /**
     * 证件类别
     */
    @ExcelProperty("cardType")
    @ColumnWidth(12)
    @DatabaseField
    private String cardType;

    /**
     * 身份证号
     */
    @ExcelProperty("idCard")
    @ColumnWidth(15)
    @DatabaseField
    private String idCard;

    /**
     * 联系邮箱
     */
    @ExcelProperty("email")
    @ColumnWidth(12)
    @DatabaseField
    private String email;

    
    @DatabaseField
    private String education;

    @ExcelProperty("educationLabel")
    @DatabaseField
    private String educationLabel;

    /**
     * 学位
     */
    @ExcelProperty("academicDegree")
    @DatabaseField
    private String academicDegree;

    /**
     * 专业
     */
    @ExcelProperty("major")
    @DatabaseField
    private String major;

    /**
     * 职称
     */
    @ExcelProperty("positionTitle")
    @DatabaseField
    private String positionTitle;

    /**
     * 职务
     */
    @ExcelProperty("job")
    @DatabaseField
    private String job;

    /**
     * 科室
     */
    @ExcelProperty("department")
    @DatabaseField
    private String department;

    /**
     * 所在学会任职
     */
    @ExcelProperty("renzhiOfXuehui")
    @ColumnWidth(18)
    @DatabaseField
    private String renzhiOfXuehui;

    /**
     * 总会任职
     */
    @ExcelProperty("renzhiOfZonghui")
    @ColumnWidth(12)
    @DatabaseField
    private String renzhiOfZonghui;

    /**
     * 人大、政协任职
     */
    @ExcelProperty("renzhiOfRendazhengxie")
    @ColumnWidth(21)
    @DatabaseField
    private String renzhiOfRendazhengxie;

    /**
     * 民主党派任职
     */
    @ExcelProperty("renzhiOfMinzhudangpai")
    @ColumnWidth(18)
    @DatabaseField
    private String renzhiOfMinzhudangpai;


    /**
     * 工作单位
     */
    
    @DatabaseField
    private String workUnitCode;

    /**
     * 工作单位
     */
    @ExcelProperty("workUnit")
    @ColumnWidth(12)
    @DatabaseField
    private String workUnit;

    /**
     * 通讯地址
     */
    @ExcelProperty("address")
    @ColumnWidth(12)
    @DatabaseField
    private String address;
    /**
     * /**
     * 邮政编码
     */
    @ExcelProperty("postalCode")
    @ColumnWidth(12)
    @DatabaseField
    private String postalCode;

    /**
     * 单位联系电话
     */
    @ExcelProperty("tel")
    @ColumnWidth(12)
    @DatabaseField
    private String tel;

    /**
     * 联系手机号
     */
    @ExcelProperty("cellPhone")
    @ColumnWidth(12)
    @DatabaseField
    private String cellPhone;

    /**
     * 护士证号
     */
    @ExcelProperty("nurseNumber")
    @ColumnWidth(12)
    @DatabaseField
    private String nurseNumber;

    /**
     * 状态 1注册会员 M普通会员 S资深会员 4过期会员
     */
    @ExcelProperty("memberType")
    @DatabaseField
    private String memberType;

    /**
     * 状态 1注册会员 M普通会员 S资深会员 4过期会员
     */
    @ExcelProperty("memberTypeName")
    @ColumnWidth(12)
    private String memberTypeName;

    /***
     * 会员有效考试日期
     */
    @ExcelProperty("startDate")
    @ColumnWidth(12)
    @DatabaseField
    private String startDate;

    /***
     * 会员有效结束日期
     */
    @ExcelProperty("endDate")
    @ColumnWidth(18)
    @DatabaseField
    private String endDate;

    /**
     * 已缴费年度
     */
    @ExcelProperty("paidYear")
    @ColumnWidth(15)
    @DatabaseField
    private String paidYear;

    /**
     * 2寸照片地址
     */
    
    @DatabaseField
    private String imageUrl;

    
    @DatabaseField
    private String certificateUrl;

    
    @DatabaseField
    private String certificateDate;

    
    private String fileKey;

    /**
     * 学生会员的学生证到期日期
     */
    
    @DatabaseField
    private String studentOverDate;

    /**
     * 紧急联系人相关信息
     */
    
    private String emergencyName;
    
    private String emergencyTel;
    
    private String emergencyRelation;

    
    @DatabaseField
    private String fillDate;

    
    @DatabaseField
    private String updateDate;

    /**
     * 是否删除：0正常， 记录ID删除
     */
    
    @DatabaseField
    private Long isDelete;

    /**
     * 1审核中 2通过 3不通过
     */
    
    @DatabaseField
    private String verifyStatus;

    /**
     * 是否省直属单位 1不是 2是
     */
    
    private String isProvinceUnit;

    /**
     * 学生证图像
     */
    
    @DatabaseField
    private String studentUrl;

    /**
     * 不通过原因
     */
    
    @DatabaseField
    private String reason;

    /**
     * 会员注册来源
     */
    
    @DatabaseField
    private String fromSys;

    /**
     * 所属专业委员会
     */
    @ExcelProperty("所属委员会")
    private String specialName;

    /**
     * 是否专业委员会 委员、副主委、主任委员
     */
    @ExcelProperty("委员会任职")
    private String specialJob;

    /**
     * 团体会员标识：1团体会员中的普通会员，３团体会员中的资深会员，２非团体会员
     */
    
    @DatabaseField
    private String isTeamMember;

    /**
     * 是否已经上报 ym
     */
    
    @DatabaseField
    private String zhUpStatus;

    /**
     * 上报中科协状态
     */
    
    @DatabaseField
    private String sciMallUploadStatus;

    /**
     * 上报中科协时间
     */
    
    @DatabaseField
    private String sciMallUploadDate;

    
    private String status;

    
    private String checkMsg;
}
