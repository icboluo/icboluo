package com.icboluo.object.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @date 2021-00-08 1:00
 */
@Data
public class FundDataVO {
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

    private String fundName;

    private static final long serialVersionUID = 153984695114811407L;
}
