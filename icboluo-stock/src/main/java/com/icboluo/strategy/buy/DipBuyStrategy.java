package com.icboluo.strategy.buy;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.util.BuyUtil;
import com.icboluo.util.MathUtil;
import com.icboluo.strategy.BuyStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 逢低买入策略
 * <p>将可用资金均分至所有股票，仅对当日涨幅 <= buyThreshold% 的股票执行买入。
 */
@Component
public class DipBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "DIP_BUY";
    private static final String STRATEGY_NAME = "逢低买入";
    private static final String STRATEGY_DESCRIPTION = "当日跌幅超过阈值时买入";
    private static final String PARAM_BUY_THRESHOLD = "buyThreshold";
    private static final BigDecimal DEFAULT_BUY_THRESHOLD = new BigDecimal("-2");

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
        return List.of(new StrategyParamMeta(PARAM_BUY_THRESHOLD, "买入阈值%", "decimal", DEFAULT_BUY_THRESHOLD, "当日涨幅低于此阈值时买入，默认-2%"));
    }

    @Override
    public void execute(BotExecutionContext context) {
        BigDecimal buyThreshold = BuyUtil.getDecimalParam(context, PARAM_BUY_THRESHOLD, DEFAULT_BUY_THRESHOLD);
        List<QuoteVo> validQuotes = BuyUtil.filterValidQuotes(context.getQuotes());
        if (validQuotes.isEmpty()) {
            return;
        }
        // 按股票数均分资金
        BigDecimal fundPerStock = MathUtil.divide(context.getAccount().getAvailableFund(), validQuotes.size(), 2, RoundingMode.DOWN);
        // 仅对符合跌幅条件的股票执行买入（buyStock 内部已校验价格与余额）
        for (QuoteVo quote : validQuotes) {
            if (quote.getIncreaseRateDay() != null && quote.getIncreaseRateDay().compareTo(buyThreshold) <= 0) {
                BuyUtil.buyStock(context, quote.getStockCode(), quote.getClosePrice(), fundPerStock);
            }
        }
    }
}
