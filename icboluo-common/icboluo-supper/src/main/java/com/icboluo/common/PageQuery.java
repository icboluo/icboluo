package com.icboluo.common;

import com.github.pagehelper.IPage;
import lombok.Data;

/**
 * @author icboluo
 */
@Data
public class PageQuery implements IPage {
    /**
     * 当前页 默认1
     */
    private Integer pageNum;
    /**
     * 每页行数 默认10
     */
    private Integer pageSize;

    @Override
    public Integer getPageNum() {
        pageNum = pageNum == null ? 1 : pageNum;
        return pageNum;
    }

    @Override
    public Integer getPageSize() {
        pageSize = pageSize == null ? 10 : pageSize;
        return pageSize;
    }

    @Override
    public String getOrderBy() {
        return null;
    }
}
