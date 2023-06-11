package com.icboluo.common;

import java.time.LocalDateTime;

/**
 * 创建人，创建时间，更新人，更新时间
 *
 * @author icboluo
 * @since 2023-06-07 0:59
 */
public interface CreateUpdate extends Create {
    /**
     * 获取更新人
     *
     * @return 更新人
     */
    String getUpdateBy();

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    void setUpdateBy(String updateBy);

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    LocalDateTime getUpdateTime();

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    void setUpdateTime(LocalDateTime updateTime);

    /**
     * 设置更新信息
     */
    default void setUpdateInfo() {
        setUpdateTime(LocalDateTime.now());
    }

    /**
     * 设置创建信息和更新信息
     */
    default void setCreateUpdate() {
        setCreateTime(LocalDateTime.now());
        setUpdateTime(LocalDateTime.now());
    }
}
