package com.icboluo.strategy.buy;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.BuyStrategy;
import com.icboluo.strategy.StrategyParamMeta;
import com.icboluo.util.BuyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 一次性买入策略
 * <p>首日将全部可用资金均分至所有股票一次性投入建仓；
 * 之后仅在卖出回收资金（可用资金回升）时，将回收资金再次均分投入，
 * 不额外追加原始本金。
 */
@Slf4j
@Component
public class OneTimeBuyStrategy implements BuyStrategy {
    private static final String STRATEGY_ID = "ONE_TIME_BUY";
    private static final String STRATEGY_NAME = "一次性买入";
    private static final String STRATEGY_DESCRIPTION = "首日满仓建仓，之后仅将卖出回收的资金再投入";

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
        List<QuoteVo> validQuotes = BuyUtil.filterValidQuotes(context.getQuotes());
        if (validQuotes.isEmpty()) {
            return;
        }
        Integer tradeDay = context.getTradeDate();
        if (tradeDay == null) {
            return;
        }
        if (tradeDay <= 1) {
            // 首日：全部可用资金一次性均分建仓（原始本金一次性投入）
            BuyUtil.buyEqually(context, validQuotes, availableFund);
        } else {
            // 之后：原始本金首日已花光，当前可用资金通常来自卖出回款，将回款再均分投入
            BuyUtil.buyEqually(context, validQuotes, availableFund);
        }
    }
}
