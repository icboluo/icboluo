package com.icboluo.util;

import com.icboluo.entity.StockPosition;
import com.icboluo.entity.StockSeason;
import com.icboluo.object.co.TradeCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 卖出策略通用工具：提供按数量卖出股票、筛选持仓股票行情等共享逻辑。
 * <p>类比买入侧的 {@code BuyUtil}。
 */
@Slf4j
public final class SellUtil {

    private static final int LOT_SIZE = 100;

    private SellUtil() {
    }

    /**
     * 卖出指定数量的股票（数量向下取整到 1 手=100 股，至少 100 股）。
     *
     * @param context   执行上下文
     * @param stockCode 股票代码
     * @param quantity  期望卖出数量（会被规整为 100 的整数倍）
     */
    public static void sellStock(BotExecutionContext context, String stockCode, int quantity) {
        // T+1 兜底：卖出数量不得超过可卖（非当日买入）数量，避免触发交易层 T+1 异常
        int sellable = heldQuantity(context, stockCode);
        int expect = Math.min(quantity, sellable);
        int lot = (expect / LOT_SIZE) * LOT_SIZE;
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
     * 统计该账户持有某股票的<b>可卖</b>数量（已满足 T+1：买入交易日早于当前交易日）。
     * <p>当日买入的批次不计入，因此所有卖出策略基于该方法即自动遵守 T+1 规则。
     */
    public static int heldQuantity(BotExecutionContext context, String stockCode) {
        return context.getPositions().stream()
                .filter(p -> p.getStockCode().equals(stockCode))
                .filter(p -> isSellable(context, p))
                .mapToInt(p -> p.getQuantity() == null ? 0 : p.getQuantity())
                .sum();
    }

    /**
     * 判断某持仓批次是否满足 T+1（买入交易日 &lt; 当前交易日）可卖出。
     * <p>供按持仓批次遍历的策略直接过滤使用。
     */
    public static boolean isSellable(BotExecutionContext context, StockPosition position) {
        if (position.getBuyTradeDay() == null) {
            return true;
        }
        StockSeason season = context.getSeason();
        if (season == null || season.getCurrentTradeDay() == null) {
            return true;
        }
        return position.getBuyTradeDay() < season.getCurrentTradeDay();
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
                log.warn("策略参数解析失败，使用默认值: key={}, value={}, default={}",
                        key, context.getParams().get(key), defaultValue);
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
        BigDecimal qty = MathUtil.divide(amount, price, 0, RoundingMode.DOWN);
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
        return MathUtil.divide(totalAmount, totalQty, 4, RoundingMode.HALF_UP);
    }
}
