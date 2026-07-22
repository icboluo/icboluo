package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 股票行情响应对象
 */
@Data
public class QuoteVo {
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 开盘价
     */
    private BigDecimal openPrice;
    /**
     * 收盘价
     */
    private BigDecimal closePrice;
    /**
     * 最高价
     */
    private BigDecimal highPrice;
    /**
     * 最低价
     */
    private BigDecimal lowPrice;
    /**
     * 成交量
     */
    private Long volume;
    /**
     * 日涨跌幅
     */
    private BigDecimal increaseRateDay;
}
