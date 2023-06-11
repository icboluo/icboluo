package com.icboluo.common;

import java.time.LocalDateTime;

/**
 * 创建人，创建时间
 *
 * @author icboluo
 * @since 2023-06-07 0:56
 */
public interface Create {

    /**
     * 获取创建人
     *
     * @return 创建人
     */
    String getCreateBy();

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    void setCreateBy(String createdBy);

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    LocalDateTime getCreateTime();

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    void setCreateTime(LocalDateTime createTime);

    /**
     * 设置创建信息
     */
    default void setCreateInfo() {
        setCreateTime(LocalDateTime.now());
    }
}
