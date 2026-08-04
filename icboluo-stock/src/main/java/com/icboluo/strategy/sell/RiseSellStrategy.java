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
 * 上涨卖出策略：当日涨幅达到阈值时清仓止盈。
 */
@Component
public class RiseSellStrategy implements SellStrategy {
    private static final String STRATEGY_ID = "RISE_SELL";
    private static final String STRATEGY_NAME = "上涨卖出";
    private static final String STRATEGY_DESCRIPTION = "当日涨幅达到阈值时卖出全部持仓";
    private static final String PARAM_SELL_THRESHOLD = "sellThreshold";
    private static final BigDecimal DEFAULT_SELL_THRESHOLD = new BigDecimal("3");

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
        return List.of(new StrategyParamMeta(PARAM_SELL_THRESHOLD, "卖出阈值%", "decimal", DEFAULT_SELL_THRESHOLD, "当日涨幅达到此阈值时清仓，默认3%"));
    }

    @Override
    public void execute(BotExecutionContext context) {
        BigDecimal threshold = EqualSellStrategy.getDecimalParam(context, PARAM_SELL_THRESHOLD, DEFAULT_SELL_THRESHOLD);
        for (QuoteVo quote : EqualSellStrategy.filterHeldQuotes(context)) {
            if (quote.getIncreaseRateDay() != null && quote.getIncreaseRateDay().compareTo(threshold) >= 0) {
                EqualSellStrategy.sellAll(context, quote.getStockCode());
            }
        }
    }
}
