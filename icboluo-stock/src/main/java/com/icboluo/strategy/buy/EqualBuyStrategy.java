package com.icboluo.strategy.buy;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.BuyStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import com.icboluo.util.BuyUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 均仓买入策略
 * <p>把可用资金按固定比例（默认 20%）均分到当日所有有效股票，建立分散持仓，
 * 配合止盈止损卖出策略使用。
 */
@Component
public class EqualBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "EQUAL_BUY";
    private static final String STRATEGY_NAME = "均仓买入";
    private static final String STRATEGY_DESCRIPTION = "按固定比例资金均分买入所有股票，建立分散持仓";
    private static final String PARAM_INVEST_RATIO = "investRatio";
    private static final BigDecimal DEFAULT_INVEST_RATIO = new BigDecimal("0.2");

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
        return List.of(new StrategyParamMeta(PARAM_INVEST_RATIO, "每期投入比例", "decimal", DEFAULT_INVEST_RATIO, "每期投入资金占可用资金的比例，默认0.2"));
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
        List<QuoteVo> validQuotes = BuyUtil.filterValidQuotes(context.getQuotes());
        if (validQuotes.isEmpty()) {
            return;
        }
        BigDecimal investFund = availableFund.multiply(ratio).setScale(2, java.math.RoundingMode.DOWN);
        BuyUtil.buyEqually(context, validQuotes, investFund);
    }
}
