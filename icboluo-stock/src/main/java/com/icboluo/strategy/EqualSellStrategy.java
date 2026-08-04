package com.icboluo.strategy;

import com.icboluo.object.co.TradeCo;
import com.icboluo.object.vo.QuoteVo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 卖出策略通用工具：提供按数量卖出股票、筛选持仓股票行情等共享逻辑。
 * <p>类比买入侧的 {@code EqualBuyStrategy}。
 */
public final class EqualSellStrategy {

    private static final int LOT_SIZE = 100;

    private EqualSellStrategy() {
    }

    /**
     * 卖出指定数量的股票（数量向下取整到 1 手=100 股，至少 100 股）。
     *
     * @param context   执行上下文
     * @param stockCode 股票代码
     * @param quantity  期望卖出数量（会被规整为 100 的整数倍）
     */
    public static void sellStock(BotExecutionContext context, String stockCode, int quantity) {
        int lot = (quantity / LOT_SIZE) * LOT_SIZE;
        if (lot < LOT_SIZE) {
            return;
        }
        TradeCo co = new TradeCo();
        co.setSeasonId(context.getSeasonId());
        co.setPlayerName(context.getPlayerName());
        co.setStockCode(stockCode);
        co.setQuantity(lot);
        context.getTradeService().sell(co, context.getPlayerName());
        // 刷新账户余额（卖出后可用资金变化）
        context.setAccount(context.getStockAccountMapper().selectById(context.getAccount().getId()));
    }

    /**
     * 卖出该账户持有的指定股票的全部数量。
     */
    public static void sellAll(BotExecutionContext context, String stockCode) {
        int held = heldQuantity(context, stockCode);
        if (held > 0) {
            sellStock(context, stockCode, held);
        }
    }

    /**
     * 统计该账户持有某股票的总数量。
     */
    public static int heldQuantity(BotExecutionContext context, String stockCode) {
        return context.getPositions().stream()
                .filter(p -> p.getStockCode().equals(stockCode))
                .mapToInt(p -> p.getQuantity() == null ? 0 : p.getQuantity())
                .sum();
    }

    /**
     * 筛选当前持仓中、且当日有行情的股票行情列表。
     */
    public static List<QuoteVo> filterHeldQuotes(BotExecutionContext context) {
        return context.getQuotes().stream()
                .filter(q -> heldQuantity(context, q.getStockCode()) > 0)
                .toList();
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
     * 将金额按手数折算为可买数量（向下取整到 100 股）。
     */
    public static int amountToLot(BigDecimal amount, BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            return 0;
        }
        BigDecimal qty = amount.divide(price, 0, RoundingMode.DOWN);
        return (qty.intValue() / LOT_SIZE) * LOT_SIZE;
    }

    /**
     * 计算该账户持有某股票的加权买入成本价（无持仓返回 null）。
     */
    public static BigDecimal costPriceOf(BotExecutionContext context, String stockCode) {
        List<com.icboluo.entity.StockTradeRecord> buys = context.getStockTradeRecordMapper().selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<com.icboluo.entity.StockTradeRecord>()
                        .eq(com.icboluo.entity.StockTradeRecord::getAccountId, context.getAccount().getId())
                        .eq(com.icboluo.entity.StockTradeRecord::getStockCode, stockCode)
                        .eq(com.icboluo.entity.StockTradeRecord::getTradeType, "BUY"));
        BigDecimal totalAmount = BigDecimal.ZERO;
        int totalQty = 0;
        for (com.icboluo.entity.StockTradeRecord r : buys) {
            totalAmount = totalAmount.add(r.getAmount());
            totalQty += r.getQuantity();
        }
        if (totalQty <= 0) {
            return null;
        }
        return totalAmount.divide(BigDecimal.valueOf(totalQty), 4, RoundingMode.HALF_UP);
    }
}
