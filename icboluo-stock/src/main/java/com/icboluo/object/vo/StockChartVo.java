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
     * 该玩家在该股票上的累计投入（买入总金额 - 卖出回笼金额，取非负值）
     */
    private BigDecimal stockTotalInvest;

    /**
     * 该玩家在该股票上的投资过程中最大净投入金额（持仓占用资金的峰值，取非负值）
     */
    private BigDecimal stockMaxInvest;

    /**
     * 该玩家在该股票上的总收益
     */
    private BigDecimal stockProfit;
    /**
     * 该玩家在该股票上的收益率（%）
     */
    private BigDecimal stockProfitRate;
    /**
     * 该股票对应的原始可操作金额（赛季初始资金按股票个数平均分配得到，用于参考基准）
     */
    private BigDecimal stockInitInvest;
    /**
     * 收益率（按原始可操作金额计算，%）
     */
    private BigDecimal stockProfitRateByInit;
    /**
     * 收益率（按投资中最大投入金额计算，%）
     */
    private BigDecimal stockProfitRateByMax;

    /**
     * 价格走势：每个交易日的收盘价等数据
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
        private BigDecimal closePrice;
        private BigDecimal increaseRateDay;
        /**
         * 当日持仓数量（仅当玩家持有该股票时有值，否则 0）
         */
        private Integer holdQty;
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
