package com.icboluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.StockAccount;
import com.icboluo.entity.StockDaily;
import com.icboluo.entity.StockInfo;
import com.icboluo.entity.StockSeason;
import com.icboluo.entity.StockSeasonQuote;
import com.icboluo.entity.StockTradeRecord;
import com.icboluo.mapper.StockAccountMapper;
import com.icboluo.mapper.StockDailyMapper;
import com.icboluo.mapper.StockInfoMapper;
import com.icboluo.mapper.StockSeasonMapper;
import com.icboluo.mapper.StockSeasonQuoteMapper;
import com.icboluo.mapper.StockTradeRecordMapper;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.StockChartVo;
import com.icboluo.service.StockQuoteService;
import com.icboluo.util.MathUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 股票行情服务实现
 */
@Service
@RequiredArgsConstructor
public class StockQuoteServiceImpl implements StockQuoteService {

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    private final StockSeasonQuoteMapper stockSeasonQuoteMapper;
    private final StockSeasonMapper stockSeasonMapper;
    private final StockDailyMapper stockDailyMapper;
    private final StockInfoMapper stockInfoMapper;
    private final StockAccountMapper stockAccountMapper;
    private final StockTradeRecordMapper stockTradeRecordMapper;

    @Override
    public List<QuoteVo> getCurrentQuotes(Integer seasonId) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            return new ArrayList<>();
        }
        // 取赛季当前交易日对应的真实交易日期
        StockSeasonQuote current = stockSeasonQuoteMapper.selectOne(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId)
                .eq(StockSeasonQuote::getTradeDay, season.getCurrentTradeDay()));
        if (current == null) {
            return new ArrayList<>();
        }
        return toQuoteVo(stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>()
                .eq(StockDaily::getTradeDate, current.getTradeDate())));
    }

    @Override
    public StockChartVo getStockChart(Integer seasonId, String stockCode, String playerName) {
        StockChartVo vo = new StockChartVo();
        vo.setStockCode(stockCode);
        StockInfo info = stockInfoMapper.selectOne(new LambdaQueryWrapper<StockInfo>()
                .eq(StockInfo::getStockCode, stockCode));
        vo.setStockName(info != null ? info.getStockName() : stockCode);

        // 该赛季所有交易日，按序号升序
        List<StockSeasonQuote> quotes = stockSeasonQuoteMapper.selectList(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId)
                .orderByAsc(StockSeasonQuote::getTradeDay));

        // 该股票每日行情，按交易日期索引
        Map<java.time.LocalDate, StockDaily> dailyMap = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>()
                        .eq(StockDaily::getStockCode, stockCode))
                .stream()
                .collect(Collectors.toMap(StockDaily::getTradeDate, Function.identity(), (a, b) -> a, LinkedHashMap::new));

        // 该玩家该股票的买卖记录，按交易日升序
        List<StockTradeRecord> records = playerRecords(seasonId, playerName, stockCode);

        // 回放计算每日持仓/成本/收益
        int holdQty = 0;
        BigDecimal netCost = BigDecimal.ZERO;
        // 累计买入金额 - 累计卖出金额（净投入）
        List<StockChartVo.PricePoint> prices = new ArrayList<>();
        for (StockSeasonQuote q : quotes) {
            StockDaily daily = dailyMap.get(q.getTradeDate());
            StockChartVo.PricePoint point = new StockChartVo.PricePoint();
            point.setTradeDay(q.getTradeDay());
            if (daily != null) {
                point.setClosePrice(daily.getClosePrice());
                point.setIncreaseRateDay(daily.getIncreaseRateDay());
            }
            // 处理该交易日发生的买卖
            for (StockTradeRecord r : records) {
                if (!r.getTradeDay().equals(q.getTradeDay())) {
                    continue;
                }
                if ("BUY".equals(r.getTradeType())) {
                    holdQty += r.getQuantity();
                    netCost = netCost.add(r.getAmount());
                } else if ("SELL".equals(r.getTradeType())) {
                    holdQty -= r.getQuantity();
                    netCost = netCost.subtract(r.getAmount());
                }
            }
            // 当日持仓市值与收益
            if (daily != null && daily.getClosePrice() != null && holdQty > 0) {
                BigDecimal marketValue = daily.getClosePrice()
                        .multiply(BigDecimal.valueOf(holdQty)).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal profit = marketValue.subtract(netCost).setScale(2, BigDecimal.ROUND_HALF_UP);
                point.setHoldMarketValue(marketValue);
                point.setHoldCost(netCost.setScale(2, BigDecimal.ROUND_HALF_UP));
                point.setHoldProfit(profit);
                point.setHoldProfitRate(MathUtil.divide(profit.multiply(HUNDRED), netCost, 2, RoundingMode.HALF_UP));
            } else {
                point.setHoldMarketValue(BigDecimal.ZERO);
                point.setHoldCost(netCost.setScale(2, BigDecimal.ROUND_HALF_UP));
                point.setHoldProfit(BigDecimal.ZERO);
                point.setHoldProfitRate(BigDecimal.ZERO);
            }
            prices.add(point);
        }
        vo.setPrices(prices);

        // 买卖标记点：价格统一取当日收盘价，确保与走势图收盘价完全一致
        Map<Integer, StockDaily> quoteDailyMap = dailyMap.values().stream()
                .collect(Collectors.toMap(d -> tradeDayByDate(quotes, d.getTradeDate()), Function.identity(), (a, b) -> a));
        List<StockChartVo.TradeMarker> markers = records.stream().map(r -> {
            StockChartVo.TradeMarker m = new StockChartVo.TradeMarker();
            m.setTradeDay(r.getTradeDay());
            m.setTradeType(r.getTradeType());
            // 以当日收盘价为买卖点，保证与收盘价线重合
            StockDaily d = quoteDailyMap.get(r.getTradeDay());
            m.setPrice(d != null && d.getClosePrice() != null ? d.getClosePrice() : r.getPrice());
            m.setQuantity(r.getQuantity());
            m.setAmount(r.getAmount());
            return m;
        }).toList();
        vo.setTrades(markers);

        // 该股票整体累计投入与收益（以最后一日视角）
        BigDecimal totalInvest = netCost.max(BigDecimal.ZERO);
        BigDecimal totalProfit = netCost.compareTo(BigDecimal.ZERO) >= 0
                ? BigDecimal.ZERO
                : netCost.negate();
        vo.setStockTotalInvest(totalInvest.setScale(2, BigDecimal.ROUND_HALF_UP));
        vo.setStockProfit(totalProfit.setScale(2, BigDecimal.ROUND_HALF_UP));
        vo.setStockProfitRate(MathUtil.divide(totalProfit.multiply(HUNDRED), totalInvest, 2, RoundingMode.HALF_UP));
        return vo;
    }

    @Override
    public List<StockChartVo> getPlayerCharts(Integer seasonId, String playerName) {
        return getTradedStockCodes(seasonId, playerName).stream()
                .map(code -> getStockChart(seasonId, code, playerName))
                .toList();
    }

    @Override
    public List<String> getTradedStockCodes(Integer seasonId, String playerName) {
        StockAccount account = stockAccountMapper.selectOne(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId)
                .eq(StockAccount::getPlayerName, playerName));
        if (account == null) {
            return new ArrayList<>();
        }
        return stockTradeRecordMapper.selectList(new LambdaQueryWrapper<StockTradeRecord>()
                        .eq(StockTradeRecord::getAccountId, account.getId()))
                .stream().map(StockTradeRecord::getStockCode).distinct().toList();
    }

    /**
     * 根据交易日期反查赛季内交易日序号
     */
    private Integer tradeDayByDate(List<StockSeasonQuote> quotes, java.time.LocalDate date) {
        return quotes.stream()
                .filter(q -> q.getTradeDate().equals(date))
                .map(StockSeasonQuote::getTradeDay)
                .findFirst()
                .orElse(null);
    }

    private List<StockTradeRecord> playerRecords(Integer seasonId, String playerName, String stockCode) {
        StockAccount account = stockAccountMapper.selectOne(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId)
                .eq(StockAccount::getPlayerName, playerName));
        if (account == null) {
            return new ArrayList<>();
        }
        return stockTradeRecordMapper.selectList(new LambdaQueryWrapper<StockTradeRecord>()
                .eq(StockTradeRecord::getAccountId, account.getId())
                .eq(StockTradeRecord::getStockCode, stockCode)
                .orderByAsc(StockTradeRecord::getTradeDay)
                .orderByAsc(StockTradeRecord::getId));
    }

    private List<QuoteVo> toQuoteVo(List<StockDaily> dailies) {
        if (dailies.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> codes = dailies.stream().map(StockDaily::getStockCode).distinct().toList();
        Map<String, StockInfo> infoMap = stockInfoMapper.selectList(new LambdaQueryWrapper<StockInfo>()
                        .in(StockInfo::getStockCode, codes)).stream()
                .collect(Collectors.toMap(StockInfo::getStockCode, Function.identity()));
        return dailies.stream().map(d -> {
            QuoteVo v = new QuoteVo();
            v.setStockCode(d.getStockCode());
            StockInfo info = infoMap.get(d.getStockCode());
            v.setStockName(info != null ? info.getStockName() : d.getStockCode());
            v.setClosePrice(d.getClosePrice());
            v.setIncreaseRateDay(d.getIncreaseRateDay());
            return v;
        }).toList();
    }
}
