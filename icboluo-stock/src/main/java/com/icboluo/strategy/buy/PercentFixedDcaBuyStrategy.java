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

/**
 * 按比例定投买入策略（PercentFixedDca）
 * <p>每个交易日按可用资金的固定百分比（默认 10%）计算本期投入金额，再均分到当日所有有效股票买入，
 * 长期坚持以固定比例投入摊平成本，不择时。
 */
@Component
public class PercentFixedDcaBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "PERCENT_FIXED_DCA";
    private static final String STRATEGY_NAME = "按比例定投";
    private static final String STRATEGY_DESCRIPTION = "每个交易日按可用资金的固定比例均分买入所有股票";
    private static final String PARAM_INVEST_RATIO = "investRatio";
    private static final BigDecimal DEFAULT_INVEST_RATIO = new BigDecimal("0.1");

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
        return List.of(new StrategyParamMeta(PARAM_INVEST_RATIO, "每期投入比例", "decimal", DEFAULT_INVEST_RATIO, "每期投入资金占可用资金的比例，默认0.1"));
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
        // 按可用资金的固定比例计算本期投入金额
        BigDecimal investFund = availableFund.multiply(ratio).setScale(2, RoundingMode.DOWN);
        // 将本期投入资金均分至所有股票买入
        BigDecimal fundPerStock = MathUtil.divide(investFund, validQuotes.size(), 2, RoundingMode.DOWN);
        for (QuoteVo quote : validQuotes) {
            BuyUtil.buyStock(context, quote.getStockCode(), quote.getClosePrice(), fundPerStock);
        }
    }
}
