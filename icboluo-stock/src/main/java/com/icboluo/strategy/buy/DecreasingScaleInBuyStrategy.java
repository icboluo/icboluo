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
 * 分批建仓买入策略
 * <p>将总资金分成 totalShares 批（默认 5 批），每批把等额资金均分到所有股票买入，
 * 在多个交易日平滑建仓，降低一次性买入的时点风险。
 */
@Component
public class DecreasingScaleInBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "SCALE_IN";
    private static final String STRATEGY_NAME = "分批建仓";
    private static final String STRATEGY_DESCRIPTION = "将资金分成多批逐批均分买入，平滑建仓";
    private static final String PARAM_TOTAL_SHARES = "totalShares";
    private static final int DEFAULT_TOTAL_SHARES = 5;

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
        return List.of(new StrategyParamMeta(PARAM_TOTAL_SHARES, "建仓批数", "integer", DEFAULT_TOTAL_SHARES, "资金均分为多少批逐步买入，默认5"));
    }

    @Override
    public void execute(BotExecutionContext context) {
        int totalShares = BuyUtil.getIntParam(context, PARAM_TOTAL_SHARES, DEFAULT_TOTAL_SHARES);
        if (totalShares <= 0) {
            totalShares = DEFAULT_TOTAL_SHARES;
        }
        BigDecimal availableFund = context.getAccount().getAvailableFund();
        if (availableFund.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        List<QuoteVo> validQuotes = BuyUtil.filterValidQuotes(context.getQuotes());
        if (validQuotes.isEmpty()) {
            return;
        }
        // 每批投入金额 = 当前可用资金 / 剩余批数，逐步递减以保证可分完
        BigDecimal perBatchFund = MathUtil.divide(availableFund, BigDecimal.valueOf(totalShares), 2, RoundingMode.DOWN);
        BigDecimal fundPerStock = MathUtil.divide(perBatchFund, validQuotes.size(), 2, RoundingMode.DOWN);
        for (QuoteVo quote : validQuotes) {
            BuyUtil.buyStock(context, quote.getStockCode(), quote.getClosePrice(), fundPerStock);
        }
    }
}
