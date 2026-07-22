package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 持仓响应对象
 */
@Data
public class PositionVo {
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
     * 当前价格
     */
    private BigDecimal currentPrice;
    /**
     * 市值 = quantity × currentPrice
     */
    private BigDecimal marketValue;
    /**
     * 盈亏 = quantity × (currentPrice - avgBuyPrice)
     */
    private BigDecimal profitLoss;
}
