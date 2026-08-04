package com.icboluo.strategy.sell;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.EqualSellStrategy;
import com.icboluo.strategy.SellStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 分批卖出策略：按当日涨幅分档卖出部分持仓，越涨卖越多。
 */
@Component
public class TieredSellStrategy implements SellStrategy {
    private static final String STRATEGY_ID = "TIERED_SELL";
    private static final String STRATEGY_NAME = "分批卖出";
    private static final String STRATEGY_DESCRIPTION = "按当日涨幅分档卖出部分持仓（3%卖1/3，5%卖2/3，8%清仓）";

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
        for (QuoteVo quote : EqualSellStrategy.filterHeldQuotes(context)) {
            if (quote.getIncreaseRateDay() == null) {
                continue;
            }
            BigDecimal rate = quote.getIncreaseRateDay();
            int held = EqualSellStrategy.heldQuantity(context, quote.getStockCode());
            if (held <= 0) {
                continue;
            }
            int sellPart;
            if (rate.compareTo(new BigDecimal("8")) >= 0) {
                sellPart = held; // 清仓
            } else if (rate.compareTo(new BigDecimal("5")) >= 0) {
                sellPart = held * 2 / 3; // 卖 2/3
            } else if (rate.compareTo(new BigDecimal("3")) >= 0) {
                sellPart = held / 3; // 卖 1/3
            } else {
                continue;
            }
            if (sellPart > 0) {
                EqualSellStrategy.sellStock(context, quote.getStockCode(), sellPart);
            }
        }
    }
}
