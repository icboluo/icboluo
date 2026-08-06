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
     * 收盘价
     */
    private BigDecimal closePrice;
    /**
     * 日涨跌幅
     */
    private BigDecimal increaseRateDay;
}
