package com.example.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveViewModel {
    @ExcelProperty("序号")
    private Integer index;
    @ExcelProperty("直播间名称")
    private String liveName;
    @ExcelProperty("姓名")
    private String userName;
    @ExcelProperty("手机号")
    private String cellPhone;
    @ExcelProperty("进入直播间时间")
    private String time1;
    @ExcelProperty("进入直播间单次观看时长")
    private String time2;
    @ExcelProperty("观看总时长")
    private String totalLiveViewTime;
}
