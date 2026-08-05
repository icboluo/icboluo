package com.icboluo.strategy.sell;

import com.icboluo.entity.StockPosition;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.SellStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import com.icboluo.util.MathUtil;
import com.icboluo.util.SellUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 止盈止损策略：持仓收益率达到止盈线清仓，跌破止损线清仓。
 */
@Component
public class TakeProfitStopLossStrategy implements SellStrategy {
    private static final String STRATEGY_ID = "TAKE_PROFIT_STOP_LOSS";
    private static final String STRATEGY_NAME = "止盈止损";
    private static final String STRATEGY_DESCRIPTION = "持仓收益率达到止盈线或跌破止损线时清仓";
    private static final String PARAM_TAKE_PROFIT = "takeProfit";
    private static final String PARAM_STOP_LOSS = "stopLoss";
    private static final BigDecimal DEFAULT_TAKE_PROFIT = new BigDecimal("10");
    private static final BigDecimal DEFAULT_STOP_LOSS = new BigDecimal("-5");

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
        return List.of(
                new StrategyParamMeta(PARAM_TAKE_PROFIT, "止盈%", "decimal", DEFAULT_TAKE_PROFIT, "持仓收益率达到此值清仓，默认10%"),
                new StrategyParamMeta(PARAM_STOP_LOSS, "止损%", "decimal", DEFAULT_STOP_LOSS, "持仓收益率跌破此值清仓，默认-5%"));
    }

    @Override
    public void execute(BotExecutionContext context) {
        BigDecimal takeProfit = SellUtil.getDecimalParam(context, PARAM_TAKE_PROFIT, DEFAULT_TAKE_PROFIT);
        BigDecimal stopLoss = SellUtil.getDecimalParam(context, PARAM_STOP_LOSS, DEFAULT_STOP_LOSS);
        int currentTradeDay = context.getSeason().getCurrentTradeDay();
        Map<String, QuoteVo> quoteMap = context.getQuotes()
                .stream()
                .collect(Collectors.toMap(QuoteVo::getStockCode, q -> q, (a, b) -> a));
        for (StockPosition pos : context.getPositions()) {
            // T+1：当天买入的股票不可卖出
            if (pos.getBuyTradeDay() >= currentTradeDay) {
                continue;
            }
            var quote = quoteMap.get(pos.getStockCode());
            if (quote == null || quote.getClosePrice() == null) {
                continue;
            }
            BigDecimal cost = SellUtil.costPriceOf(context, pos.getStockCode());
            if (cost == null) {
                continue;
            }
            BigDecimal profitRate = MathUtil.divide(
                    quote.getClosePrice().subtract(cost).multiply(BigDecimal.valueOf(100)),
                    cost, 2, RoundingMode.HALF_UP);
            if (profitRate.compareTo(takeProfit) >= 0 || profitRate.compareTo(stopLoss) <= 0) {
                SellUtil.sellAll(context, pos.getStockCode());
            }
        }
    }
}
