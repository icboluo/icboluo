package com.icboluo.strategy.buy;

import com.icboluo.entity.StockSeason;
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
 * 定额定投买入策略（FixedDca）
 * <p>按「初始资金 / 总交易日」计算每日固定投入额，每天投入额恒定、不受已有持仓影响，
 * 再将当日投入额均分到所有有效股票买入，属于定额式定投。
 */
@Component
public class FixedDcaBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "FIXED_DCA";
    private static final String STRATEGY_NAME = "定额定投";
    private static final String STRATEGY_DESCRIPTION = "按初始资金/总交易日计算每日固定投入额，均分买入所有股票";

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
        StockSeason season = context.getSeason();
        BigDecimal initialFund = season.getInitialFund();
        int totalTradeDays = season.getTotalTradeDays();
        if (initialFund == null || initialFund.compareTo(BigDecimal.ZERO) <= 0 || totalTradeDays <= 0) {
            return;
        }
        // 每日固定投入额 = 初始资金 / 总交易日（定额式，不随持仓变化）
        BigDecimal dailyAmount = MathUtil.divide(initialFund, BigDecimal.valueOf(totalTradeDays), 2, RoundingMode.DOWN);
        if (dailyAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        List<QuoteVo> validQuotes = BuyUtil.filterValidQuotes(context.getQuotes());
        if (validQuotes.isEmpty()) {
            return;
        }
        // 将当日固定投入额均分至所有股票买入
        BigDecimal fundPerStock = MathUtil.divide(dailyAmount, validQuotes.size(), 2, RoundingMode.DOWN);
        for (QuoteVo quote : validQuotes) {
            BuyUtil.buyStock(context, quote.getStockCode(), quote.getClosePrice(), fundPerStock);
        }
    }
}
