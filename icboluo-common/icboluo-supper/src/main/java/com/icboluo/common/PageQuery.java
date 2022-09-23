package com.icboluo.common;

import lombok.Data;

/**
 * 禁止实现IPage接口，会和全局配置一样检测到参数中实现这个接口自动分页；全局配置自动分页是support-method-arguments：true
 *
 * @author icboluo
 */
@Data
@SuppressWarnings("unused")
public class PageQuery {
    /**
     * 当前页 默认1
     */
    private Integer pageNum;
    /**
     * 每页行数 默认10
     */
    private Integer pageSize;

    public Integer getPageNum() {
        pageNum = pageNum == null ? 1 : pageNum;
        return pageNum;
    }

    public Integer getPageSize() {
        pageSize = pageSize == null ? 10 : pageSize;
        return pageSize;
    }
}
