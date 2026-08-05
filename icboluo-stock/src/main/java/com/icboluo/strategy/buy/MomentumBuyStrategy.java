package com.icboluo.strategy.buy;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.BuyStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import com.icboluo.util.BuyUtil;
import com.icboluo.util.MathUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 趋势买入策略
 * <p>当日涨幅为正的股票视为处于上升趋势，把资金均分到这些强势股买入，跟随趋势。
 * 若当日无上涨股票则不操作。
 */
@Component
public class MomentumBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "MOMENTUM_BUY";
    private static final String STRATEGY_NAME = "趋势买入";
    private static final String STRATEGY_DESCRIPTION = "当日上涨的股票视为强势股，均分资金买入";
    private static final String PARAM_INVEST_RATIO = "investRatio";
    private static final BigDecimal DEFAULT_INVEST_RATIO = new BigDecimal("0.3");

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
        return List.of(new StrategyParamMeta(PARAM_INVEST_RATIO, "每期投入比例", "decimal", DEFAULT_INVEST_RATIO, "每期投入资金占可用资金的比例，默认0.3"));
    }

    @Override
    public void execute(BotExecutionContext context) {
        BigDecimal ratio = BuyUtil.getDecimalParam(context, PARAM_INVEST_RATIO, DEFAULT_INVEST_RATIO);
        if (ratio.compareTo(BigDecimal.ZERO) <= 0 || ratio.compareTo(BigDecimal.ONE) > 0) {
            ratio = DEFAULT_INVEST_RATIO;
        }
        BigDecimal availableFund = context.getAccount().getAvailableFund();
        if (availableFund.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        List<QuoteVo> momentumQuotes = BuyUtil.filterValidQuotes(context.getQuotes()).stream()
                .filter(q -> q.getIncreaseRateDay() != null && q.getIncreaseRateDay().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
        if (momentumQuotes.isEmpty()) {
            return;
        }
        BigDecimal investFund = availableFund.multiply(ratio).setScale(2, RoundingMode.DOWN);
        BigDecimal fundPerStock = MathUtil.divide(investFund, momentumQuotes.size(), 2, RoundingMode.DOWN);
        for (QuoteVo quote : momentumQuotes) {
            BuyUtil.buyStock(context, quote.getStockCode(), quote.getClosePrice(), fundPerStock);
        }
    }
}
