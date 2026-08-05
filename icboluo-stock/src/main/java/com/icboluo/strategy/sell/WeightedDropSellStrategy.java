package com.icboluo.strategy.sell;

import com.icboluo.entity.StockPosition;
import com.icboluo.object.co.TradeCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.SellStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import com.icboluo.util.MathUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 追跌卖出策略
 * <p>按跌幅加权卖出持仓，跌幅越大的持仓卖出越多。
 */
@Component
public class WeightedDropSellStrategy implements SellStrategy {
    private static final String STRATEGY_ID = "WEIGHTED_WEIGHTED_DROP_SELL";
    String STRATEGY_NAME = "追跌卖出";
    String STRATEGY_DESCRIPTION = "按跌幅加权卖出持仓";

    @Override
    public String getId() {
        return STRATEGY_ID;
    }

    @Override
    public String getName() {
        return STRATEGY_NAME;
    }

    @Override
    public String getDescription() {
        return STRATEGY_DESCRIPTION;
    }

    @Override
    public List<StrategyParamMeta> getParamMetas() {
        return List.of();
    }

    @Override
    public void execute(BotExecutionContext context) {
        var positions = context.getPositions();
        if (positions == null || positions.isEmpty()) {
            return;
        }
        // 构建 stockCode -> QuoteVo 映射
        var quoteMap = context.getQuotes()
                .stream()
                .collect(Collectors.toMap(QuoteVo::getStockCode, q -> q, (a, b) -> a)); // 筛选当日涨幅 < 0 且满足T+1的持仓
        var sellPositions = positions
                .stream()
                .filter(p -> p.getBuyTradeDay() < context.getSeason().getCurrentTradeDay()) // T+1
                .filter(p -> {
                    var quote = quoteMap.get(p.getStockCode());
                    return quote != null && quote.getIncreaseRateDay() != null && quote.getIncreaseRateDay().compareTo(BigDecimal.ZERO) < 0;
                })
                .toList();
        if (sellPositions.isEmpty()) {
            return;
        }
        // 按跌幅加权：跌幅绝对值越大，卖出越多
        var totalDropAbs = sellPositions
                .stream()
                .map(p -> quoteMap.get(p.getStockCode()).getIncreaseRateDay().abs())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        for (StockPosition pos : sellPositions) {
            var dropAbs = quoteMap.get(pos.getStockCode()).getIncreaseRateDay().abs();
            BigDecimal ratio = MathUtil.divide(dropAbs, totalDropAbs, 4, RoundingMode.DOWN);
            int sellQty = ratio.multiply(BigDecimal.valueOf(pos.getQuantity())).setScale(0, RoundingMode.DOWN).intValue();
            sellQty = (sellQty / 100) * 100;
            // 100股取整
            if (sellQty < 100) {
                continue;
            }
            try {
                TradeCo tradeCo = new TradeCo();
                tradeCo.setSeasonId(context.getSeasonId());
                tradeCo.setPlayerName(context.getPlayerName());
                tradeCo.setStockCode(pos.getStockCode());
                tradeCo.setQuantity(sellQty);
                context.getTradeService().sell(tradeCo, context.getPlayerName());
            } catch (Exception e) {
                // 机器人卖出失败不影响正常流程
            }
        }
        // 卖出后刷新账户余额
        context.setAccount(context.getStockAccountMapper().selectById(context.getAccount().getId()));
    }
}
