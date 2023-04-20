package com.ncepu.util.ValidateUtils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc 参数model
 * @date 2023/4/19 13:36
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParamModel {
    /**
     * 字段值
     */
    private Object fieldValue;
    /**
     * 参数字符串，可以是普通字符串，也可以是JSON字符串，在校验阶段已经做了去除两端空字符的处理
     */
    private String param;
}
