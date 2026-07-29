package com.icboluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.*;
import com.icboluo.mapper.*;
import com.icboluo.object.vo.*;
import com.icboluo.service.StockAccountService;
import com.icboluo.util.I18nException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 股票账户服务实现
 */
@Service
@RequiredArgsConstructor
public class StockAccountServiceImpl implements StockAccountService {
    private final StockAccountMapper stockAccountMapper;
    private final StockPositionMapper stockPositionMapper;
    private final StockSeasonMapper stockSeasonMapper;
    private final StockSeasonQuoteMapper stockSeasonQuoteMapper;
    private final StockDailyMapper stockDailyMapper;
    private final StockInfoMapper stockInfoMapper;
    private final StockTradeRecordMapper stockTradeRecordMapper;

    @Override
    public AccountVo getAccount(Integer seasonId, String playerName) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        StockAccount account = getOrCreateAccount(seasonId, playerName, season.getInitialFund());
// 查询持仓
        List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>().eq(StockPosition::getAccountId, account.getId()));
// 获取当前交易日对应的日期
        LocalDate tradeDate = getCurrentTradeDate(seasonId, season.getCurrentTradeDay());
// 查询所有持仓股票的当前收盘价
        Map<String, BigDecimal> closePriceMap = getClosePriceMap(positions, tradeDate);
// 查询股票信息（名称）
        Map<String, String> stockNameMap = getStockNameMap(positions);
// 计算持仓市值和盈亏
        BigDecimal positionMarketValue = BigDecimal.ZERO;
        List<PositionVo> positionVOs = new ArrayList<>();
// 按股票代码聚合持仓
        Map<String, List<StockPosition>> groupedByCode = positions.stream().collect(Collectors.groupingBy(StockPosition::getStockCode));
        for (Map.Entry<String, List<StockPosition>> entry : groupedByCode.entrySet()) {
            String stockCode = entry.getKey();
            List<StockPosition> batchList = entry.getValue();
            int totalQuantity = batchList.stream().mapToInt(StockPosition::getQuantity).sum();
            BigDecimal currentPrice = closePriceMap.getOrDefault(stockCode, BigDecimal.ZERO);
            BigDecimal marketValue = currentPrice.multiply(BigDecimal.valueOf(totalQuantity));
            positionMarketValue = positionMarketValue.add(marketValue);
// 计算加权平均买入价
            BigDecimal totalCost = BigDecimal.ZERO;
            for (StockPosition pos : batchList) {
                BigDecimal posClosePrice = getClosePriceForPosition(pos, seasonId);
                totalCost = totalCost.add(posClosePrice.multiply(BigDecimal.valueOf(pos.getQuantity())));
            }
            BigDecimal avgBuyPrice = totalQuantity > 0 ? totalCost.divide(BigDecimal.valueOf(totalQuantity), 4, RoundingMode.HALF_UP) : BigDecimal.ZERO;
            BigDecimal profitLoss = currentPrice.subtract(avgBuyPrice).multiply(BigDecimal.valueOf(totalQuantity));
            PositionVo vo = new PositionVo();
            vo.setStockCode(stockCode);
            vo.setStockName(stockNameMap.getOrDefault(stockCode, ""));
            vo.setQuantity(totalQuantity);
            vo.setCurrentPrice(currentPrice);
            vo.setMarketValue(marketValue);
            vo.setProfitLoss(profitLoss);
            positionVOs.add(vo);
        }
// 计算总资产和收益率
        BigDecimal totalAsset = account.getAvailableFund().add(positionMarketValue);
        BigDecimal initialFund = season.getInitialFund();
        BigDecimal profitRate = totalAsset.subtract(initialFund).divide(initialFund, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        AccountVo accountVO = new AccountVo();
        accountVO.setId(account.getId());
        accountVO.setPlayerName(account.getPlayerName());
        accountVO.setAvailableFund(account.getAvailableFund());
        accountVO.setTotalAsset(totalAsset);
        accountVO.setProfitRate(profitRate);
        accountVO.setInitialFund(initialFund);
        accountVO.setPositions(positionVOs);
        return accountVO;
    }

    @Override
    public List<RankVo> getRank(Integer seasonId) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
// 查询赛季所有账户
        List<StockAccount> accounts = stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>().eq(StockAccount::getSeasonId, seasonId));
        if (accounts.isEmpty()) {
            return Collections.emptyList();
        }
        LocalDate tradeDate = getCurrentTradeDate(seasonId, season.getCurrentTradeDay());
        List<RankVo> rankList = new ArrayList<>();
        for (StockAccount account : accounts) { // 查询该账户所有持仓
            List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>().eq(StockPosition::getAccountId, account.getId()));
// 计算持仓市值
            Map<String, BigDecimal> closePriceMap = getClosePriceMap(positions, tradeDate);
            BigDecimal positionMarketValue = positions.stream().map(p -> closePriceMap.getOrDefault(p.getStockCode(), BigDecimal.ZERO).multiply(BigDecimal.valueOf(p.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalAsset = account.getAvailableFund().add(positionMarketValue);
            BigDecimal initialFund = season.getInitialFund();
            BigDecimal profitRate = totalAsset.subtract(initialFund).divide(initialFund, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
// 持仓股票名称列表
            Map<String, String> stockNameMap = getStockNameMap(positions);
            List<String> positionStockNames = positions.stream().map(StockPosition::getStockCode).distinct().map(code -> stockNameMap.getOrDefault(code, code)).toList();
            RankVo rankVO = new RankVo();
            rankVO.setPlayerName(account.getPlayerName());
            rankVO.setTotalAsset(totalAsset);
            rankVO.setProfitRate(profitRate);
            rankVO.setPositionCount(positionStockNames.size());
            rankVO.setPositionStockNames(positionStockNames);
            rankList.add(rankVO);
        }
// 按 totalAsset 降序排列并设置排名
        rankList.sort(Comparator.comparing(RankVo::getTotalAsset).reversed());
        for (int i = 0; i < rankList.size(); i++) {
            rankList.get(i).setRank(i + 1);
        }
        return rankList;
    }

    @Override
    public List<ProfitPointVo> getProfitCurve(Integer seasonId, String playerName) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        StockAccount account = getOrCreateAccount(seasonId, playerName, season.getInitialFund());
// 查询该账户所有交易记录，按交易日分组计算每日资金变动
        List<StockTradeRecord> tradeRecords = stockTradeRecordMapper.selectList(new LambdaQueryWrapper<StockTradeRecord>().eq(StockTradeRecord::getAccountId, account.getId()).orderByAsc(StockTradeRecord::getTradeDay));
// 查询赛季所有交易日行情
        List<StockSeasonQuote> seasonQuotes = stockSeasonQuoteMapper.selectList(new LambdaQueryWrapper<StockSeasonQuote>().eq(StockSeasonQuote::getSeasonId, seasonId).orderByAsc(StockSeasonQuote::getTradeDay));
// 计算每个交易日的资金（从初始资金开始，逐日累加交易盈亏）
        BigDecimal fund = season.getInitialFund();
// 按交易日汇总交易金额
        Map<Integer, BigDecimal> tradeAmountByDay = new LinkedHashMap<>();
        for (StockTradeRecord record : tradeRecords) {
// BUY: amount 为负（花钱），SELL: amount 为正（收入）
            BigDecimal delta = "SELL".equals(record.getTradeType()) ? record.getAmount() : record.getAmount().negate();
            tradeAmountByDay.merge(record.getTradeDay(), delta, BigDecimal::add);
        }
        List<ProfitPointVo> curve = new ArrayList<>();
        int currentTradeDay = season.getCurrentTradeDay() != null ? season.getCurrentTradeDay() : 0;
        for (StockSeasonQuote sq : seasonQuotes) {
            if (sq.getTradeDay() > currentTradeDay) {
                break;
            }
// 累加该日的交易金额变动
            if (tradeAmountByDay.containsKey(sq.getTradeDay())) {
                fund = fund.add(tradeAmountByDay.get(sq.getTradeDay()));
            }
// 查询该日持仓市值
            List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>().eq(StockPosition::getAccountId, account.getId()));
            LocalDate tradeDate = sq.getTradeDate();
            BigDecimal positionMarketValue = BigDecimal.ZERO;
            if (!positions.isEmpty() && tradeDate != null) {
                Map<String, BigDecimal> closePriceMap = getClosePriceMap(positions, tradeDate);
                positionMarketValue = positions.stream().map(p -> closePriceMap.getOrDefault(p.getStockCode(), BigDecimal.ZERO).multiply(BigDecimal.valueOf(p.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            BigDecimal totalAsset = fund.add(positionMarketValue);
            BigDecimal profitRate = totalAsset.subtract(season.getInitialFund()).divide(season.getInitialFund(), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            ProfitPointVo point = new ProfitPointVo();
            point.setTradeDay(sq.getTradeDay());
            point.setTotalAsset(totalAsset);
            point.setProfitRate(profitRate);
            curve.add(point);
        }
        return curve;
    }

    @Override
    public List<PositionDistributionVo> getPositionDistribution(Integer seasonId, String playerName) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        StockAccount account = getOrCreateAccount(seasonId, playerName, season.getInitialFund());
        List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>().eq(StockPosition::getAccountId, account.getId()));
        if (positions.isEmpty()) {
            return Collections.emptyList();
        }
        LocalDate tradeDate = getCurrentTradeDate(seasonId, season.getCurrentTradeDay());
        Map<String, BigDecimal> closePriceMap = getClosePriceMap(positions, tradeDate);
        Map<String, String> stockNameMap = getStockNameMap(positions);
// 按股票代码聚合
        Map<String, List<StockPosition>> groupedByCode = positions.stream().collect(Collectors.groupingBy(StockPosition::getStockCode));
// 计算总市值
        BigDecimal totalMarketValue = BigDecimal.ZERO;
        List<PositionDistributionVo> voList = new ArrayList<>();
        for (Map.Entry<String, List<StockPosition>> entry : groupedByCode.entrySet()) {
            String stockCode = entry.getKey();
            int quantity = entry.getValue().stream().mapToInt(StockPosition::getQuantity).sum();
            BigDecimal currentPrice = closePriceMap.getOrDefault(stockCode, BigDecimal.ZERO);
            BigDecimal marketValue = currentPrice.multiply(BigDecimal.valueOf(quantity));
            totalMarketValue = totalMarketValue.add(marketValue);
            PositionDistributionVo vo = new PositionDistributionVo();
            vo.setStockCode(stockCode);
            vo.setStockName(stockNameMap.getOrDefault(stockCode, ""));
            vo.setQuantity(quantity);
            vo.setMarketValue(marketValue);
            voList.add(vo);
        }
// 计算占比
        for (PositionDistributionVo vo : voList) {
            if (totalMarketValue.compareTo(BigDecimal.ZERO) > 0) {
                vo.setProportion(vo.getMarketValue().divide(totalMarketValue, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
            } else {
                vo.setProportion(BigDecimal.ZERO);
            }
        }
        return voList;
    }

    /**
     * 查询账户，不存在则自动创建
     */
    private StockAccount getOrCreateAccount(Integer seasonId, String playerName, BigDecimal initialFund) {
        StockAccount account = stockAccountMapper.selectOne(new LambdaQueryWrapper<StockAccount>().eq(StockAccount::getSeasonId, seasonId).eq(StockAccount::getPlayerName, playerName));
        if (account == null) {
            account = new StockAccount();
            account.setSeasonId(seasonId);
            account.setPlayerName(playerName);
            account.setAvailableFund(initialFund);
            stockAccountMapper.insert(account);
        }
        return account;
    }

    /**
     * 根据 seasonId 和 tradeDay 获取对应的交易日期
     */
    private LocalDate getCurrentTradeDate(Integer seasonId, Integer tradeDay) {
        if (tradeDay == null) {
            return null;
        }
        StockSeasonQuote quote = stockSeasonQuoteMapper.selectOne(new LambdaQueryWrapper<StockSeasonQuote>().eq(StockSeasonQuote::getSeasonId, seasonId).eq(StockSeasonQuote::getTradeDay, tradeDay));
        return quote != null ? quote.getTradeDate() : null;
    }

    /**
     * 批量查询持仓股票的收盘价
     */
    private Map<String, BigDecimal> getClosePriceMap(List<StockPosition> positions, LocalDate tradeDate) {
        if (positions.isEmpty() || tradeDate == null) {
            return Collections.emptyMap();
        }
        List<String> stockCodes = positions.stream().map(StockPosition::getStockCode).distinct().toList();
        List<StockDaily> dailies = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>().in(StockDaily::getStockCode, stockCodes).eq(StockDaily::getTradeDate, tradeDate));
        return dailies.stream().collect(Collectors.toMap(StockDaily::getStockCode, StockDaily::getClosePrice, (a, b) -> a));
    }

    /**
     * 获取某条持仓记录买入时的收盘价（用于计算买入均价）
     */
    private BigDecimal getClosePriceForPosition(StockPosition position, Integer seasonId) {
        LocalDate buyDate = getCurrentTradeDate(seasonId, position.getBuyTradeDay());
        if (buyDate == null) {
            return BigDecimal.ZERO;
        }
        StockDaily daily = stockDailyMapper.selectOne(new LambdaQueryWrapper<StockDaily>().eq(StockDaily::getStockCode, position.getStockCode()).eq(StockDaily::getTradeDate, buyDate));
        return daily != null ? daily.getClosePrice() : BigDecimal.ZERO;
    }

    /**
     * 批量查询股票名称
     */
    private Map<String, String> getStockNameMap(List<StockPosition> positions) {
        if (positions.isEmpty()) {
            return Collections.emptyMap();
        }
        List<String> stockCodes = positions.stream().map(StockPosition::getStockCode).distinct().toList();
        List<StockInfo> stockInfos = stockInfoMapper.selectList(new LambdaQueryWrapper<StockInfo>().in(StockInfo::getStockCode, stockCodes));
        return stockInfos.stream().collect(Collectors.toMap(StockInfo::getStockCode, StockInfo::getStockName, (a, b) -> a));
    }
}


