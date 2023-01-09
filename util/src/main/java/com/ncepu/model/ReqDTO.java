package com.ncepu.model;

import lombok.Data;

/**
 * @author wengym
 * @version 1.0
 * @desc 通用请求DTO
 * @date 2021/11/17 9:53
 */
@Data
public class ReqDTO extends ParentClass {
    /**
     * 通用模糊查询字段
     */
    private String param;

    // 页数
    private Integer pageIndex = 0;

    // 分页大小
    private Integer pageSize = 0;
}
