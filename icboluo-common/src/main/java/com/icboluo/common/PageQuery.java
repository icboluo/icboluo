package com.icboluo.common;

import lombok.Data;

/**
 * @author lp
 */
@Data
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
