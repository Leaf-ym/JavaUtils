package com.ncepu.util.DatabaseUtils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc 通用参数model
 * @date 2022/7/20 9:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SQLParam<T> {
    /**
     * 数据表对应的model的Class对象
     */
    private Class<?> cls;
}
