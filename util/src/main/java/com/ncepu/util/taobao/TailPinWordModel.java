package com.ncepu.util.taobao;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc 尾销词产品model
 * @date 2023/3/25 8:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TailPinWordModel {
    /**
     * 搜索词
     */
    @ExcelProperty("搜索词")
    private String searchWord;
    /**
     * 关键词
     */
    @ExcelProperty("关键词")
    private String KeyWord;
    /**
     * 搜索人气
     */
    @ExcelProperty("搜索人气")
    private Integer searchPopularity;
    /**
     * 支付转化率
     */
    @ExcelProperty("支付转化率")
    private String paymentConversionRate;
    /**
     * 尾销
     */
    @ExcelProperty("尾销")
    private Integer tailPinNum;
}
