package com.icboluo.util;

import com.icboluo.object.co.TradeCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 买入策略通用工具：提供按金额买入股票、筛选有效行情等共享逻辑。
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BuyUtil {

    private static final int LOT_SIZE = 100;

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
        BigDecimal qty = MathUtil.divide(fund, price, 0, RoundingMode.DOWN);
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

    /**
     * 将资金均分至股票列表并逐个买入。
     *
     * @param context 执行上下文
     * @param quotes  要买入的股票行情列表
     * @param fund    总投入资金
     */
    public static void buyEqually(BotExecutionContext context, List<QuoteVo> quotes, BigDecimal fund) {
        if (quotes.isEmpty() || fund.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        BigDecimal fundPerStock = MathUtil.divide(fund, quotes.size(), 2, RoundingMode.DOWN);
        for (QuoteVo quote : quotes) {
            buyStock(context, quote.getStockCode(), quote.getClosePrice(), fundPerStock);
        }
    }

    /**
     * 从参数中读取 BigDecimal 类型参数，缺省使用默认值。
     */
    public static BigDecimal getDecimalParam(BotExecutionContext context, String key, BigDecimal defaultValue) {
        if (context.getParams() != null && context.getParams().containsKey(key)) {
            try {
                return new BigDecimal(context.getParams().get(key).toString());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 从参数中读取整数类型参数，缺省使用默认值。
     */
    public static int getIntParam(BotExecutionContext context, String key, int defaultValue) {
        if (context.getParams() != null && context.getParams().containsKey(key)) {
            try {
                return Integer.parseInt(context.getParams().get(key).toString());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

}
