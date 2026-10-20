package com.icboluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.*;
import com.icboluo.mapper.*;
import com.icboluo.object.vo.*;
import com.icboluo.service.StockAccountService;
import com.icboluo.util.I18nException;
import com.icboluo.util.MathUtil;
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
        BigDecimal initialFund = season.getInitialFund();
        StockAccount account = findAccount(seasonId, playerName);
        if (account == null) {
            // 未加入赛季：返回"初始资金"口径的空账户（不落库），保持前端展示一致
            AccountVo empty = new AccountVo();
            empty.setPlayerName(playerName);
            empty.setAvailableFund(initialFund);
            empty.setTotalAsset(initialFund);
            empty.setProfitRate(BigDecimal.ZERO);
            empty.setInitialFund(initialFund);
            empty.setPositions(Collections.emptyList());
            return empty;
        }
        // 查询持仓
        List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>()
                .eq(StockPosition::getAccountId, account.getId()));
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
        Map<String, List<StockPosition>> groupedByCode = positions
                .stream()
                .collect(Collectors.groupingBy(StockPosition::getStockCode));
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
            BigDecimal avgBuyPrice = MathUtil.divide(totalCost, totalQuantity, 4, RoundingMode.HALF_UP);
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
        BigDecimal profitRate = MathUtil.divide(totalAsset.subtract(initialFund), initialFund, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
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
        List<StockAccount> accounts = stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId));
        if (accounts.isEmpty()) {
            return Collections.emptyList();
        }
        LocalDate tradeDate = getCurrentTradeDate(seasonId, season.getCurrentTradeDay());
        List<RankVo> rankList = new ArrayList<>();
        for (StockAccount account : accounts) { // 查询该账户所有持仓
            List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>()
                    .eq(StockPosition::getAccountId, account.getId()));
            // 计算持仓市值
            Map<String, BigDecimal> closePriceMap = getClosePriceMap(positions, tradeDate);
            BigDecimal positionMarketValue = positions
                    .stream()
                    .map(p -> closePriceMap.getOrDefault(p.getStockCode(), BigDecimal.ZERO)
                            .multiply(BigDecimal.valueOf(p.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalAsset = account.getAvailableFund().add(positionMarketValue);
            BigDecimal initialFund = season.getInitialFund();
            BigDecimal profitRate = MathUtil.divide(totalAsset.subtract(initialFund), initialFund, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            // 持仓股票种数（按股票代码去重）
            long positionCount = positions
                    .stream()
                    .map(StockPosition::getStockCode)
                    .distinct()
                    .count();
            RankVo rankVO = new RankVo();
            rankVO.setPlayerName(account.getPlayerName());
            rankVO.setTotalAsset(totalAsset);
            rankVO.setProfitRate(profitRate);
            rankVO.setPositionCount((int) positionCount);
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
        BigDecimal initialFund = season.getInitialFund();
        StockAccount account = findAccount(seasonId, playerName);
        if (account == null) {
            // 未加入赛季：返回平坦曲线（不落库）
            return buildFlatCurve(seasonId, initialFund, season.getCurrentTradeDay());
        }
        // 查询该账户所有交易记录，按交易日分组计算每日资金变动
        List<StockTradeRecord> tradeRecords = stockTradeRecordMapper.selectList(new LambdaQueryWrapper<StockTradeRecord>()
                .eq(StockTradeRecord::getAccountId, account.getId())
                .orderByAsc(StockTradeRecord::getTradeDay));
        // 查询赛季所有交易日行情
        List<StockSeasonQuote> seasonQuotes = stockSeasonQuoteMapper.selectList(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId)
                .orderByAsc(StockSeasonQuote::getTradeDay));
        // 预加载行情：仅加载本账户交易过的股票在赛季日期内的日线，建立 (股票代码|日期) -> 收盘价 映射
        Set<String> tradedCodes = tradeRecords.stream().map(StockTradeRecord::getStockCode).collect(Collectors.toSet());
        Set<LocalDate> seasonDates = seasonQuotes.stream()
                .map(StockSeasonQuote::getTradeDate)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<String, BigDecimal> closePriceByCodeDate = Collections.emptyMap();
        if (!tradedCodes.isEmpty() && !seasonDates.isEmpty()) {
            List<StockDaily> dailies = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>()
                    .in(StockDaily::getStockCode, new ArrayList<>(tradedCodes))
                    .in(StockDaily::getTradeDate, new ArrayList<>(seasonDates)));
            closePriceByCodeDate = dailies.stream()
                    .collect(Collectors.toMap(
                            d -> d.getStockCode() + "|" + d.getTradeDate(),
                            StockDaily::getClosePrice,
                            (a, b) -> a));
        }
        // 按交易日回放交易记录：维护现金与每只股票的持仓数量。
        // 不能用"当前持仓快照"回溯历史，否则已卖出的股票在持有期间的市值会被算成 0，导致曲线断层/跳变。
        Map<Integer, List<StockTradeRecord>> recordsByDay = tradeRecords.stream()
                .collect(Collectors.groupingBy(StockTradeRecord::getTradeDay));
        BigDecimal cash = initialFund;
        Map<String, Integer> holdQty = new HashMap<>();
        List<ProfitPointVo> curve = new ArrayList<>();
        int currentTradeDay = season.getCurrentTradeDay() != null ? season.getCurrentTradeDay() : 0;
        for (StockSeasonQuote sq : seasonQuotes) {
            if (sq.getTradeDay() > currentTradeDay) {
                break;
            }
            // 应用当日交易：买入扣减现金并增加持仓，卖出回笼现金并减少持仓
            for (StockTradeRecord record : recordsByDay.getOrDefault(sq.getTradeDay(), Collections.emptyList())) {
                Integer qtyObj = record.getQuantity();
                int qty = qtyObj == null ? 0 : qtyObj;
                if ("SELL".equals(record.getTradeType())) {
                    cash = cash.add(record.getAmount());
                    holdQty.merge(record.getStockCode(), -qty, Integer::sum);
                } else {
                    cash = cash.subtract(record.getAmount());
                    holdQty.merge(record.getStockCode(), qty, Integer::sum);
                }
            }
            // 当日持仓市值（缺行情记 0，与原逻辑一致）
            BigDecimal positionMarketValue = BigDecimal.ZERO;
            for (Map.Entry<String, Integer> entry : holdQty.entrySet()) {
                int qty = entry.getValue() == null ? 0 : entry.getValue();
                if (qty <= 0) {
                    continue;
                }
                BigDecimal price = closePriceByCodeDate.getOrDefault(
                        entry.getKey() + "|" + sq.getTradeDate(), BigDecimal.ZERO);
                positionMarketValue = positionMarketValue.add(price.multiply(BigDecimal.valueOf(qty)));
            }
            BigDecimal totalAsset = cash.add(positionMarketValue);
            BigDecimal profitRate = MathUtil.divide(totalAsset.subtract(initialFund),
                    initialFund, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            ProfitPointVo point = new ProfitPointVo();
            point.setTradeDay(sq.getTradeDay());
            point.setTotalAsset(totalAsset);
            point.setProfitRate(profitRate);
            curve.add(point);
        }
        return curve;
    }

    @Override
    public List<String> listPlayers(Integer seasonId) {
        if (stockSeasonMapper.selectById(seasonId) == null) {
            throw new I18nException("赛季不存在");
        }
        return stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId))
                .stream()
                .map(StockAccount::getPlayerName)
                .distinct()
                .sorted()
                .toList();
    }

    @Override
    public List<PositionDistributionVo> getPositionDistribution(Integer seasonId, String playerName) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        StockAccount account = findAccount(seasonId, playerName);
        if (account == null) {
            return Collections.emptyList();
        }
        List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>()
                .eq(StockPosition::getAccountId, account.getId()));
        if (positions.isEmpty()) {
            return Collections.emptyList();
        }
        LocalDate tradeDate = getCurrentTradeDate(seasonId, season.getCurrentTradeDay());
        Map<String, BigDecimal> closePriceMap = getClosePriceMap(positions, tradeDate);
        Map<String, String> stockNameMap = getStockNameMap(positions);
        // 按股票代码聚合
        Map<String, List<StockPosition>> groupedByCode = positions
                .stream()
                .collect(Collectors.groupingBy(StockPosition::getStockCode));
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
            vo.setProportion(MathUtil.divide(vo.getMarketValue(), totalMarketValue, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100)));
        }
        return voList;
    }

    /**
     * 查询账户（只读）。不存在时返回 null，<b>不自动创建</b>。
     * <p>只读接口若自动建账户，任何拼写错误的玩家名都会凭空产生一个账户，
     * 并以"总资产=初始资金、收益率0"进入玩家列表与榜单，造成数据污染。
     * 账户只应由 {@code joinSeason}（真人）与机器人注册流程创建。
     */
    private StockAccount findAccount(Integer seasonId, String playerName) {
        return stockAccountMapper.selectOne(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId)
                .eq(StockAccount::getPlayerName, playerName));
    }

    /**
     * 未加入赛季时的平坦收益曲线（总资产恒为初始资金、收益率 0），不落库。
     * 用于保持与"自动建空账户"时一致的展示口径。
     */
    private List<ProfitPointVo> buildFlatCurve(Integer seasonId, BigDecimal initialFund, Integer currentTradeDay) {
        List<StockSeasonQuote> quotes = stockSeasonQuoteMapper.selectList(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId)
                .orderByAsc(StockSeasonQuote::getTradeDay));
        int day = currentTradeDay == null ? 0 : currentTradeDay;
        List<ProfitPointVo> curve = new ArrayList<>();
        for (StockSeasonQuote sq : quotes) {
            if (sq.getTradeDay() > day) {
                break;
            }
            ProfitPointVo point = new ProfitPointVo();
            point.setTradeDay(sq.getTradeDay());
            point.setTotalAsset(initialFund);
            point.setProfitRate(BigDecimal.ZERO);
            curve.add(point);
        }
        return curve;
    }

    /**
     * 根据 seasonId 和 tradeDay 获取对应的交易日期
     */
    private LocalDate getCurrentTradeDate(Integer seasonId, Integer tradeDay) {
        if (tradeDay == null) {
            return null;
        }
        StockSeasonQuote quote = stockSeasonQuoteMapper.selectOne(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId).eq(StockSeasonQuote::getTradeDay, tradeDay));
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
        List<StockDaily> dailies = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>()
                .in(StockDaily::getStockCode, stockCodes)
                .eq(StockDaily::getTradeDate, tradeDate));
        return dailies
                .stream()
                .collect(Collectors.toMap(StockDaily::getStockCode, StockDaily::getClosePrice, (a, b) -> a));
    }

    /**
     * 获取某条持仓记录买入时的收盘价（用于计算买入均价）
     */
    private BigDecimal getClosePriceForPosition(StockPosition position, Integer seasonId) {
        LocalDate buyDate = getCurrentTradeDate(seasonId, position.getBuyTradeDay());
        if (buyDate == null) {
            return BigDecimal.ZERO;
        }
        StockDaily daily = stockDailyMapper.selectOne(new LambdaQueryWrapper<StockDaily>()
                .eq(StockDaily::getStockCode, position.getStockCode())
                .eq(StockDaily::getTradeDate, buyDate));
        return daily != null ? daily.getClosePrice() : BigDecimal.ZERO;
    }

    /**
     * 批量查询股票名称
     */
    private Map<String, String> getStockNameMap(List<StockPosition> positions) {
        if (positions.isEmpty()) {
            return Collections.emptyMap();
        }
        List<String> stockCodes = positions.stream()
                .map(StockPosition::getStockCode)
                .distinct()
                .toList();
        List<StockInfo> stockInfos = stockInfoMapper.selectList(new LambdaQueryWrapper<StockInfo>()
                .in(StockInfo::getStockCode, stockCodes));
        return stockInfos
                .stream()
                .collect(Collectors.toMap(StockInfo::getStockCode, StockInfo::getStockName, (a, b) -> a));
    }
}


