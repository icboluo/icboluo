package com.icboluo.common;

/**
 * 数据是否合法：目前用作校验Excel单条数据是否需要保留
 *
 * @author icboluo
 * @since 2023-06-30 18:45
 */
public interface DataIsLegality {
    /**
     * 是否合法
     *
     * @return 如果数据合法，返回true，否则返回false
     */
    boolean isLegality();
}
