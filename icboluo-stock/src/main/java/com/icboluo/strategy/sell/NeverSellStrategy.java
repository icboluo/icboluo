package com.icboluo.strategy.sell;

import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.SellStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 永不卖出策略：持有至赛季结束强制清仓。
 */
@Component
public class NeverSellStrategy implements SellStrategy {
    private static final String STRATEGY_ID = "NEVER_SELL";
    private static final String STRATEGY_NAME = "永不卖出";
    private static final String STRATEGY_DESCRIPTION = "持有股票直到赛季结束强制清仓，期间不主动卖出";

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
        // 不执行任何卖出
    }
}
