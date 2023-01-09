package com.ncepu.model;

/**
 * @author wengym
 * @version 1.0
 * @desc 通用请求DTO
 * @date 2021/11/17 9:53
 */
public class ReqDTO {
    // 通用模糊查询字段
    private String param;

    // 页数
    private Integer pageIndex = 0;

    // 分页大小
    private Integer pageSize = 0;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
