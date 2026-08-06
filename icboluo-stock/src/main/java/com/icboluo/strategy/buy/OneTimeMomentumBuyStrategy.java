package com.icboluo.strategy.buy;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.BuyStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import com.icboluo.util.BuyUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一次性动量买入策略
 * <p>将可用资金均分至所有股票，仅对当日涨幅 &gt; 0 的强势股执行买入，弱势股（下跌）不买。
 */
@Component
public class OneTimeMomentumBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "ONE_TIME_MOMENTUM_BUY";
    private static final String STRATEGY_NAME = "一次性动量买入";
    private static final String STRATEGY_DESCRIPTION = "可用资金均分，仅对当日上涨的股票买入";

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
        BigDecimal availableFund = context.getAccount().getAvailableFund();
        if (availableFund.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        // 仅当日涨幅 > 0 的股票参与买入
        List<QuoteVo> momentumQuotes = BuyUtil.filterValidQuotes(context.getQuotes()).stream()
                .filter(q -> q.getIncreaseRateDay() != null && q.getIncreaseRateDay().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
        if (momentumQuotes.isEmpty()) {
            return;
        }
        // 将可用资金均分至这些强势股买入
        BuyUtil.buyEqually(context, momentumQuotes, availableFund);
    }
}
