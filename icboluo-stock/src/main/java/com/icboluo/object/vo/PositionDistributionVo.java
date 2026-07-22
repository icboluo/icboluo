package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 持仓分布响应对象
 */
@Data
public class PositionDistributionVo {
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 持仓数量
     */
    private Integer quantity;
    /**
     * 市值
     */
    private BigDecimal marketValue;
    /**
     * 占比（百分比）
     */
    private BigDecimal proportion;
}
