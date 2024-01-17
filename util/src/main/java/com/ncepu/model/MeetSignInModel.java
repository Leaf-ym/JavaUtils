package com.ncepu.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * 现场签到记录表
 * <p>
 * Created by Demon on 2019/9/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ContentRowHeight(25)
public class MeetSignInModel {

    @ExcelIgnore
    private String id;

    @ExcelProperty("序号")
    private String index;

    @ExcelIgnore
    private String meetId;

    @ExcelProperty("会议名称")
    private String meetName;

    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("年龄")
    private String age;

    @ExcelProperty("职称")
    private String positionTitle;

    @ExcelProperty("工作单位")
    @ColumnWidth(25)
    private String workUnit;

    @ExcelProperty("科室")
    private String department;

    @ExcelProperty("手机号")
    @ColumnWidth(12)
    private String cellPhone;

    @ExcelIgnore
    private String idCard;

    @ExcelIgnore
    private String idCardImg;

    @ExcelIgnore
    private String signInImg;

    @ExcelProperty("手动签名")
    @ColumnWidth(15)
    private File signImg;

    @ExcelProperty("签到时间")
    @ColumnWidth(20)
    private String fillDate;

}
