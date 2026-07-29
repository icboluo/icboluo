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
     * 该玩家在该股票上的累计投入（买入总金额 - 卖出回笼金额）
     */
    private BigDecimal stockTotalInvest;

    /**
     * 该玩家在该股票上的总收益
     */
    private BigDecimal stockProfit;
    /**
     * 该玩家在该股票上的收益率（%）
     */
    private BigDecimal stockProfitRate;

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
        /**
         * 当日持仓市值（仅当玩家持有该股票时有值）
         */
        private BigDecimal holdMarketValue;
        /**
         * 当日累计投入成本
         */
        private BigDecimal holdCost;
        /**
         * 当日持仓收益
         */
        private BigDecimal holdProfit;
        /**
         * 当日持仓收益率（%）
         */
        private BigDecimal holdProfitRate;
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
