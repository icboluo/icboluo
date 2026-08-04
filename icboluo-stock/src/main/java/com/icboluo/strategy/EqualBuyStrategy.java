package com.icboluo.strategy;

import com.icboluo.object.co.TradeCo;
import com.icboluo.object.vo.QuoteVo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 买入策略通用工具：提供按金额买入股票、筛选有效行情等共享逻辑。
 */
public final class EqualBuyStrategy {

    private static final int LOT_SIZE = 100;

    private EqualBuyStrategy() {
    }

    /**
     * 按金额买入指定股票：将 fund 折算为 100 股整数倍的数量后买入。
     *
     * @param context   执行上下文
     * @param stockCode 股票代码
     * @param price     买入价（通常为当日收盘价）
     * @param fund      本次买入可用金额
     */
    public static void buyStock(BotExecutionContext context, String stockCode, BigDecimal price, BigDecimal fund) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        BigDecimal qty = fund.divide(price, 0, RoundingMode.DOWN);
        int lot = (qty.intValue() / LOT_SIZE) * LOT_SIZE;
        if (lot < LOT_SIZE) {
            return;
        }
        TradeCo co = new TradeCo();
        co.setSeasonId(context.getSeasonId());
        co.setPlayerName(context.getPlayerName());
        co.setStockCode(stockCode);
        co.setQuantity(lot);
        context.getTradeService().buy(co, context.getPlayerName());
        // 买入后刷新账户余额
        context.setAccount(context.getStockAccountMapper().selectById(context.getAccount().getId()));
    }

    /**
     * 过滤出有有效收盘价的行情（无价格无法成交）。
     */
    public static List<QuoteVo> filterValidQuotes(List<QuoteVo> quotes) {
        if (quotes == null) {
            return List.of();
        }
        return quotes.stream()
                .filter(q -> q.getClosePrice() != null && q.getClosePrice().compareTo(BigDecimal.ZERO) > 0)
                .toList();
    }
}
