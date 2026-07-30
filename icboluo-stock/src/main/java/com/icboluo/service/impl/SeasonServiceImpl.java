package com.icboluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.*;
import com.icboluo.mapper.*;
import com.icboluo.object.co.AdvanceDayCo;
import com.icboluo.object.co.SeasonCreateCo;
import com.icboluo.object.co.SeasonJoinCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.SeasonVo;
import com.icboluo.service.SeasonService;
import com.icboluo.service.StockTradeService;
import com.icboluo.strategy.StrategyRegistry;
import com.icboluo.util.I18nException;
import com.icboluo.websocket.StockWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
        Long count = stockAccountMapper.selectCount(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, co.getSeasonId())
                .eq(StockAccount::getPlayerName, co.getPlayerName()));
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
    @Transactional
    public SeasonVo startSeason(Integer seasonId) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        if (!"PREPARING".equals(season.getStatus())) {
            throw new I18nException("赛季状态不是PREPARING，无法开始");
        }
        season.setStatus("PLAYING");
        season.setCurrentTradeDay(1);
        stockSeasonMapper.updateById(season);
        // 注册预置机器人（自动创建stock_bot_config和stock_account）
        registerPresetBots(seasonId, season.getInitialFund());
        // 机器人执行首日策略
        executeBotStrategies(seasonId, season);
        // 推送首日进度
        broadcastProgress(seasonId, season);
        // 只有机器人的赛季，事务提交后异步推进后续交易日
        if (isBotOnlySeason(seasonId)) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    self.asyncAdvanceBotSeason(seasonId);
                }
            });
        }
        return toSeasonVo(season);
    }


    @Override
    public List<SeasonVo> listSeasons() {
        List<StockSeason> seasons = stockSeasonMapper.selectList(null);
        return seasons.stream().map(this::toSeasonVo).toList();
    }

    @Override
    @Transactional
    public void deleteSeason(Integer seasonId) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        // 查询该赛季所有账户ID
        List<StockAccount> accounts = stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId));
        List<Integer> accountIds = accounts.stream().map(StockAccount::getId).toList();
        // 删除机器人配置
        stockBotConfigMapper.delete(new LambdaQueryWrapper<StockBotConfig>()
                .eq(StockBotConfig::getSeasonId, seasonId));
        // 删除持仓和交易记录
        if (!accountIds.isEmpty()) {
            stockPositionMapper.delete(new LambdaQueryWrapper<StockPosition>()
                    .in(StockPosition::getAccountId, accountIds));
            stockTradeRecordMapper.delete(new LambdaQueryWrapper<StockTradeRecord>()
                    .in(StockTradeRecord::getAccountId, accountIds));
        }
        // 删除账户
        stockAccountMapper.delete(new LambdaQueryWrapper<StockAccount>().eq(StockAccount::getSeasonId, seasonId));
        // 删除交易日映射
        stockSeasonQuoteMapper.delete(new LambdaQueryWrapper<StockSeasonQuote>().eq(StockSeasonQuote::getSeasonId, seasonId));
        // 删除赛季
        stockSeasonMapper.deleteById(seasonId);
    }

    @Override
    @Transactional
    public void finishSeason(Integer seasonId) {
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        if ("FINISHED".equals(season.getStatus())) {
            throw new I18nException("赛季已结束");
        }
        // 赛季结束前强制清仓所有持仓
        forceLiquidateSeason(seasonId, season);
        season.setStatus("FINISHED");
        season.setHistoryRevealed(true);
        stockSeasonMapper.updateById(season);
    }

    @Override
    @Transactional
    public SeasonVo createBotMatch(SeasonCreateCo co) {
        // 创建赛季
        SeasonVo seasonVo = createSeason(co);
        // 自动开始（机器人会在startSeason中自动加入）
        return startSeason(seasonVo.getId());
    }

    @Override
    @Transactional
    public List<QuoteVo> advanceDay(AdvanceDayCo co) {
        StockSeason season = stockSeasonMapper.selectById(co.getSeasonId());
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        if (!"PLAYING".equals(season.getStatus())) {
            throw new RuntimeException("赛季状态不是PLAYING，无法推进交易日");
        }
        int nextTradeDay = season.getCurrentTradeDay() + 1;
        // 赛季结束
        if (nextTradeDay > season.getTotalTradeDays()) {
            // 赛季结束前强制清仓所有持仓
            forceLiquidateSeason(co.getSeasonId(), season);
            season.setStatus("FINISHED");
            season.setHistoryRevealed(true);
            stockSeasonMapper.updateById(season);
            return Collections.emptyList();
        }
        // 正常推进
        season.setCurrentTradeDay(nextTradeDay);
        stockSeasonMapper.updateById(season);
        // 查询当日对应的实际交易日期
        StockSeasonQuote seasonQuote = stockSeasonQuoteMapper.selectOne(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, co.getSeasonId())
                .eq(StockSeasonQuote::getTradeDay, nextTradeDay));
        LocalDate tradeDate = seasonQuote.getTradeDate();
        // 查询当日行情
        List<QuoteVo> quotes = getQuotesByDate(tradeDate);
        // WebSocket 推送行情变化
        stockWebSocketHandler.broadcastToSeason(co.getSeasonId(), "quote", quotes);
        // 机器人执行策略
        executeBotStrategies(co.getSeasonId(), season);
        // 推送进度
        broadcastProgress(co.getSeasonId(), season);
        // 只有机器人的赛季，策略执行完后自动推进下一天（由 asyncAdvanceBotSeason 处理）
        return quotes;
    }

    private SeasonVo toSeasonVo(StockSeason season) {
        return null;
    }
}
