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
public class CommonExcelDataModel {
    @ExcelProperty("文件名")
    private String fileName;
    @ExcelProperty("文件URL")
    private String fileUrl;
}
