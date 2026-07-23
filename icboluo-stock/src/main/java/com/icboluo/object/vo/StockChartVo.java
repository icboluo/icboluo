package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 股票走势图（含买卖标记）
 */
@Data
public class StockChartVo {
    private String stockCode;
    private String stockName;
    /**
     * 价格走势：每个交易日的OHLCV数据
     */
    private List<PricePoint> prices;
    /**
     * 买卖标记点
     */
    private List<TradeMarker> trades;

    @Data
    public static class PricePoint {
        /**
         * 赛季内第几个交易日（从1开始）
         */
        private int tradeDay;
        private BigDecimal openPrice;
        private BigDecimal closePrice;
        private BigDecimal highPrice;
        private BigDecimal lowPrice;
        private BigDecimal increaseRateDay;
    }

    @Data
    public static class TradeMarker {
        /**
         * 赛季内第几个交易日
         */
        private int tradeDay;
        /**
         * BUY / SELL
         */
        private String tradeType;
        private BigDecimal price;
        private int quantity;
        private BigDecimal amount;
    }
}
