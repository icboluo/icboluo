package com.icboluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.StockAccount;
import com.icboluo.entity.StockDaily;
import com.icboluo.entity.StockSeason;
import com.icboluo.entity.StockSeasonQuote;
import com.icboluo.mapper.*;
import com.icboluo.object.co.AdvanceDayCo;
import com.icboluo.object.co.SeasonCreateCo;
import com.icboluo.object.co.SeasonJoinCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.SeasonVo;
import com.icboluo.service.SeasonService;
import com.icboluo.service.StockTradeService;
import com.icboluo.util.I18nException;
import com.icboluo.websocket.StockWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 赛季服务实现
 */
@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {
    @Lazy
    private final SeasonServiceImpl self;
    private static final int REQUIRED_TRADE_DAYS = 120;
    private static final int MAX_TRADE_DAYS = 300;
    private final StockSeasonMapper stockSeasonMapper;
    private final StockSeasonQuoteMapper stockSeasonQuoteMapper;
    private final StockAccountMapper stockAccountMapper;
    private final StockDailyMapper stockDailyMapper;
    private final StockInfoMapper stockInfoMapper;
    private final StockWebSocketHandler stockWebSocketHandler;
    private final StockPositionMapper stockPositionMapper;
    private final StockTradeRecordMapper stockTradeRecordMapper;
    private final StockTradeService stockTradeService;
    private final StrategyRegistry strategyRegistry;
    private final StockBotConfigMapper stockBotConfigMapper;

    @Override
    @Transactional
    public SeasonVo createSeason(SeasonCreateCo co) {
        // 创建赛季
        StockSeason season = new StockSeason();
        season.setName(co.getName());
        season.setStatus("PREPARING");
        season.setInitialFund(co.getInitialFund());
        season.setCurrentTradeDay(0);
        season.setHistoryRevealed(false);
        stockSeasonMapper.insert(season);
        // 查询所有不同交易日期，按日期排序
        List<LocalDate> allTradeDates = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>().select(StockDaily::getTradeDate).groupBy(StockDaily::getTradeDate).orderByAsc(StockDaily::getTradeDate)).stream().map(StockDaily::getTradeDate).toList();
        // 确保有足够的交易日
        if (allTradeDates.isEmpty()) {
            throw new I18nException("stock_daily表无数据，请先通过 /stockQuote/import 导入CSV行情数据");
        }
        if (allTradeDates.size() < REQUIRED_TRADE_DAYS) {
            throw new I18nException("历史行情数据不足，当前" + allTradeDates.size() + "个交易日，至少需要" + REQUIRED_TRADE_DAYS + "个");
        }
        // 随机确定赛季长度 [120, 300]，不超过可用交易日总数
        int tradeDays = REQUIRED_TRADE_DAYS + (int) (Math.random() * (MAX_TRADE_DAYS - REQUIRED_TRADE_DAYS + 1));
        tradeDays = Math.min(tradeDays, allTradeDates.size());
        int maxStartIndex = allTradeDates.size() - tradeDays;
        int startIndex = maxStartIndex > 0 ? (int) (Math.random() * (maxStartIndex + 1)) : 0;
        List<LocalDate> selectedDates = allTradeDates.subList(startIndex, startIndex + tradeDays);
        // 将选取的交易日序列写入 stock_season_quote
        List<StockSeasonQuote> quoteList = new ArrayList<>();
        for (int i = 0; i < selectedDates.size(); i++) {
            StockSeasonQuote quote = new StockSeasonQuote();
            quote.setSeasonId(season.getId());
            quote.setTradeDay(i + 1);
            quote.setTradeDate(selectedDates.get(i));
            quoteList.add(quote);
        }
        // 批量插入
        for (StockSeasonQuote quote : quoteList) {
            stockSeasonQuoteMapper.insert(quote);
        }
        // 设置赛季的历史日期范围和总交易日数
        season.setHistoryStartDate(selectedDates.getFirst());
        season.setHistoryEndDate(selectedDates.getLast());
        season.setTotalTradeDays(selectedDates.size());
        stockSeasonMapper.updateById(season);
        return toSeasonVo(season);
    }

    @Override
    @Transactional
    public SeasonVo joinSeason(SeasonJoinCo co) {
        // 校验赛季存在
        StockSeason season = stockSeasonMapper.selectById(co.getSeasonId());
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        // 校验玩家未重复加入
        Long count = stockAccountMapper.selectCount(new LambdaQueryWrapper<StockAccount>().eq(StockAccount::getSeasonId, co.getSeasonId()).eq(StockAccount::getPlayerName, co.getPlayerName()));
        if (count > 0) {
            return toSeasonVo(season);
        }
        // 创建账户
        StockAccount account = new StockAccount();
        account.setSeasonId(co.getSeasonId());
        account.setPlayerName(co.getPlayerName());
        account.setAvailableFund(season.getInitialFund());
        stockAccountMapper.insert(account);
        return toSeasonVo(season);
    }

    @Override
    public SeasonVo startSeason(Integer seasonId) {
        return null;
    }

    @Override
    public List<SeasonVo> listSeasons() {
        return List.of();
    }

    @Override
    public List<QuoteVo> advanceDay(AdvanceDayCo co) {
        return List.of();
    }

    @Override
    public void deleteSeason(Integer seasonId) {

    }

    @Override
    public void finishSeason(Integer seasonId) {

    }

    @Override
    public SeasonVo createBotMatch(SeasonCreateCo co) {
        return null;
    }

    private SeasonVo toSeasonVo(StockSeason season) {
        return null;
    }
}
