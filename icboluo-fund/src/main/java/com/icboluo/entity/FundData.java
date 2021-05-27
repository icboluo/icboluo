package com.icboluo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;

/**
 * (FundData)实体类
 *
 * @author icboluo
 * @since 2021-05-28 00:12:35
 */
@Data
public class FundData implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 基金id
     */
    private String fundId;
    /**
     * 日增长率
     */
    private String increaseRateDay;
    /**
     * 申购状态
     */
    private String subscribeStatus;
    /**
     * 净值日期
     */
    private LocalDateTime netValueDate;
    /**
     * 单位净值
     */
    private String netAssetValue;
    /**
     * 累计净值
     */
    private String netValueCumulative;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 153984695114811407L;
}
