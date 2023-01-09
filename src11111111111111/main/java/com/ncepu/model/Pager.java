package com.ncepu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc 分页信息
 * @date 2022/3/19 8:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pager {
    // 页码
    int pageIndex;
    // 每页大小
    int pageSize;
}
