package com.ncepu.util.DatabaseUtils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * @author wengym
 * @version 1.0
 * @desc 通用参数model
 * @date 2022/7/20 9:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchMapParam<T> extends SQLParam<T> {
    /**
     * 数据表对应的model
     */
    private T model;
    /**
     * 查询条件
     */
    private Map<String, Object> queryMap;
    /**
     * 如果想把某个字段作为查询条件，可将该字段添加到集合中
     */
    private Set<String> whereFieldSet;
    /**
     * 一般会结合isEmptyNotQuery，如果不是非空查询，则如果想忽略某个字段，则可将该字段添加到集合中
     */
    private Set<String> ignoreQueryFieldSet;
    /**
     * 别名
     */
    private String alias;
    /**
     * 是否非空查询
     */
    private Boolean isEmptyNotQuery;
}
