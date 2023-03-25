package com.ncepu.util.taobao;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc chanpinmodel
 * @date 2023/3/21 8:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {
    /**
     * 产品标题
     */
    @ExcelProperty("标题")
    private String raw_title;
    /**
     * 销量
     */
    @ExcelIgnore
    private String view_sales;
    /**
     * 处理后的销量
     */
    @ExcelProperty("销量")
    private Integer view_sales_num;

    /**
     * 尾销
     */
    private Integer tail_pin_num;
}
