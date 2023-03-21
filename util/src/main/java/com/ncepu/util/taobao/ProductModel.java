package com.ncepu.util.taobao;

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
    private String raw_title;
    /**
     * 销量
     */
    private String view_sales;
    /**
     * 处理后的销量
     */
    private Integer view_sales_num;
}
