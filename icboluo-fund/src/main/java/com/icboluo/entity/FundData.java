package com.icboluo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * (FundData)实体类
 *
 * @author icboluo
 * @since 2021-07-08 20:38:57
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
    private BigDecimal increaseRateDay;
    /**
     * 申购状态
     */
    private String subscribeStatus;
    /**
     * 净值日期
     */
    private LocalDate netValueDate;
    /**
     * 单位净值
     */
    private BigDecimal netAssetValue;
    /**
     * 累计净值
     */
    private BigDecimal netValueCumulative;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 932315031704088938L;
}
