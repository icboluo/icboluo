package com.icboluo.strategy.sell;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.util.MathUtil;
import com.icboluo.util.SellUtil;
import com.icboluo.strategy.SellStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 下跌卖出策略（止损）：持仓收益率跌破阈值时清仓。
 */
@Component
public class StopLossSellStrategy implements SellStrategy {
    private static final String STRATEGY_ID = "STOP_LOSS_SELL";
    private static final String STRATEGY_NAME = "止损卖出";
    private static final String STRATEGY_DESCRIPTION = "持仓收益率为负且低于阈值时卖出全部持仓止损";
    private static final String PARAM_DROP_THRESHOLD = "dropThreshold";
    private static final BigDecimal DEFAULT_DROP_THRESHOLD = new BigDecimal("-5");

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
        return List.of(new StrategyParamMeta(PARAM_DROP_THRESHOLD, "止损阈值%", "decimal", DEFAULT_DROP_THRESHOLD, "持仓收益率低于此阈值时清仓，默认-5%"));
    }

    @Override
    public void execute(BotExecutionContext context) {
        BigDecimal threshold = SellUtil.getDecimalParam(context, PARAM_DROP_THRESHOLD, DEFAULT_DROP_THRESHOLD);
        for (QuoteVo quote : SellUtil.filterHeldQuotes(context)) {
            BigDecimal cost = SellUtil.costPriceOf(context, quote.getStockCode());
            if (cost == null || quote.getClosePrice() == null) {
                continue;
            }
            BigDecimal profitRate = MathUtil.divide(
                    quote.getClosePrice().subtract(cost).multiply(BigDecimal.valueOf(100)),
                    cost, 2, RoundingMode.HALF_UP);
            if (profitRate.compareTo(threshold) <= 0) {
                SellUtil.sellAll(context, quote.getStockCode());
            }
        }
    }
}
