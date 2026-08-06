package com.icboluo.strategy.buy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.StockTradeRecord;
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
 * 定额建仓买入策略（FixedScaleIn）
 * <p>将初始资金分成 totalShares 份，每个交易日投入 1 份，均分至所有股票买入。
 * 已用份数 = 执行了买入操作的不同交易日数（通过统计该账户 BUY 记录涉及的不同交易日得出），
 * 建仓完成后不再买入。
 */
@Component
public class FixedScaleInBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "FIXED_SCALE_IN";
    private static final String STRATEGY_NAME = "定额建仓";
    private static final String STRATEGY_DESCRIPTION = "将初始资金均分为多份，每个交易日投入1份均分买入，建满即止";
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
        return List.of(new StrategyParamMeta(PARAM_TOTAL_SHARES, "建仓份数", "integer", DEFAULT_TOTAL_SHARES, "初始资金均分为多少份逐步买入，默认5"));
    }

    @Override
    public void execute(BotExecutionContext context) {
        int totalShares = BuyUtil.getIntParam(context, PARAM_TOTAL_SHARES, DEFAULT_TOTAL_SHARES);
        if (totalShares <= 0) {
            totalShares = DEFAULT_TOTAL_SHARES;
        }
        // 已用份数 = 该账户 BUY 记录涉及的不同交易日数
        List<Integer> usedTradeDays = context.getStockTradeRecordMapper().selectList(
                        new LambdaQueryWrapper<StockTradeRecord>()
                                .eq(StockTradeRecord::getAccountId, context.getAccount().getId())
                                .eq(StockTradeRecord::getTradeType, "BUY"))
                .stream()
                .map(StockTradeRecord::getTradeDay)
                .distinct()
                .collect(Collectors.toList());
        int usedShares = usedTradeDays.size();
        // 建仓完成则不再买入
        if (usedShares >= totalShares) {
            return;
        }
        BigDecimal initialFund = context.getSeason().getInitialFund();
        if (initialFund == null || initialFund.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        // 每份投入额 = 初始资金 / 建仓份数（定额，不随持仓或回款变化）
        BigDecimal perShare = MathUtil.divide(initialFund, BigDecimal.valueOf(totalShares), 2, RoundingMode.DOWN);
        if (perShare.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        List<QuoteVo> validQuotes = BuyUtil.filterValidQuotes(context.getQuotes());
        if (validQuotes.isEmpty()) {
            return;
        }
        // 将本份资金均分至所有股票买入
        BigDecimal fundPerStock = MathUtil.divide(perShare, validQuotes.size(), 2, RoundingMode.DOWN);
        for (QuoteVo quote : validQuotes) {
            BuyUtil.buyStock(context, quote.getStockCode(), quote.getClosePrice(), fundPerStock);
        }
    }
}
