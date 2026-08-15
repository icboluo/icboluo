package com.icboluo.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.*;
import com.icboluo.mapper.*;
import com.icboluo.object.co.AdvanceDayCo;
import com.icboluo.object.co.SeasonCreateCo;
import com.icboluo.object.co.SeasonJoinCo;
import com.icboluo.object.co.SeasonPrepareCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.SeasonVo;
import com.icboluo.service.SeasonService;
import com.icboluo.service.StockTradeService;
import com.icboluo.strategy.BotExecutionContext;
import com.icboluo.strategy.BuyStrategy;
import com.icboluo.strategy.SellStrategy;
import com.icboluo.strategy.StrategyRegistry;
import com.icboluo.util.I18nException;
import com.icboluo.websocket.StockWebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 赛季服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {
    /**
     * 自身代理，用于内部调用时让 @Async / @Transactional 切面生效。
     * 注意：必须用 @Autowired 字段注入 + @Lazy，不能用构造器注入，
     * 因为 Lombok 的 @RequiredArgsConstructor 不会把 @Lazy 复制到构造器参数上，
     * 会导致启动时报循环依赖。
     */
    @Autowired
    @Lazy
    private SeasonServiceImpl self;
    /**
     * 进程内串行推进锁。SQLite 仅允许单一写者，自动推进(asyncAdvanceBotSeason)与
     * 手动点击推进(SeasonController.advance)可能并发推进同一赛季，导致
     * [SQLITE_BUSY] database is locked。用全局锁保证任意时刻只有一个线程在写库推进。
     */
    private static final ReentrantLock ADVANCE_LOCK = new ReentrantLock();
    private static final int REQUIRED_TRADE_DAYS = 10;
    private static final int MAX_TRADE_DAYS = 200;
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
        List<LocalDate> allTradeDates = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>()
                        .select(StockDaily::getTradeDate)
                        .groupBy(StockDaily::getTradeDate)
                        .orderByAsc(StockDaily::getTradeDate))
                .stream()
                .map(StockDaily::getTradeDate)
                .toList();
        // 确保有足够的交易日
        if (allTradeDates.isEmpty()) {
            throw new I18nException("stock_daily表无数据，请先通过 /stockQuote/import 导入CSV行情数据");
        }
        if (allTradeDates.size() < REQUIRED_TRADE_DAYS) {
            throw new I18nException("历史行情数据不足，当前" + allTradeDates.size() + "个交易日，至少需要" + REQUIRED_TRADE_DAYS + "个");
        }
        // 随机确定赛季长度 [10, 200]，不超过可用交易日总数
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
        // 校验赛季处于准备阶段，开始后禁止玩家加入
        if (!"PREPARING".equals(season.getStatus())) {
            throw new I18nException("赛季已开始，无法加入");
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
        account.setReady(false);
        stockAccountMapper.insert(account);
        return toSeasonVo(season);
    }

    @Override
    @Transactional
    public SeasonVo prepare(SeasonPrepareCo co) {
        StockSeason season = stockSeasonMapper.selectById(co.getSeasonId());
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        if (!"PREPARING".equals(season.getStatus())) {
            throw new I18nException("赛季已开始，无法准备");
        }
        // 校验玩家已加入
        StockAccount account = stockAccountMapper.selectOne(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, co.getSeasonId())
                .eq(StockAccount::getPlayerName, co.getPlayerName()));
        if (account == null) {
            throw new I18nException("请先加入赛季");
        }
        // 标记玩家已准备
        account.setReady(true);
        stockAccountMapper.updateById(account);
        // 统计真人玩家（排除机器人）的准备情况
        List<String> botNames = stockBotConfigMapper.selectList(new LambdaQueryWrapper<StockBotConfig>()
                        .eq(StockBotConfig::getSeasonId, co.getSeasonId())).stream()
                .map(StockBotConfig::getBotName).toList();
        List<StockAccount> humanAccounts = stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>()
                        .eq(StockAccount::getSeasonId, co.getSeasonId())).stream()
                .filter(a -> !botNames.contains(a.getPlayerName()))
                .toList();
        boolean allReady = !humanAccounts.isEmpty()
                && humanAccounts.stream().allMatch(a -> Boolean.TRUE.equals(a.getReady()));
        // 所有玩家准备就绪，自动开始赛季并推进首日
        if (allReady) {
            self.startSeason(co.getSeasonId());
        }
        return toSeasonVo(stockSeasonMapper.selectById(co.getSeasonId()));
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
        // 机器人执行首日策略（通过代理调用，使 NOT_SUPPORTED 挂起外层事务）
        self.executeBotStrategies(seasonId, season);
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
    public List<QuoteVo> advanceDay(AdvanceDayCo co) {
        // 注意：本方法刻意不加 @Transactional。SQLite 在事务 BEGIN 后即持有写锁直到 COMMIT，
        // 若此处包长事务，executeBotStrategies 内 buy/sell 另起连接写库会被本连接持有的写锁阻塞，
        // 触发 [SQLITE_BUSY]。改用进程内串行锁(ADVANCE_LOCK)保证推进串行，买/卖各自短事务提交。
        // SQLite 仅允许单一写者，串行化推进避免并发触发 [SQLITE_BUSY] database is locked
        ADVANCE_LOCK.lock();
        try {
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
            // 机器人执行策略（通过代理调用，使 NOT_SUPPORTED 挂起外层事务，单笔买卖失败不影响赛季元数据）
            self.executeBotStrategies(co.getSeasonId(), season);
            // 推送进度
            broadcastProgress(co.getSeasonId(), season);
            // 只有机器人的赛季，策略执行完后自动推进下一天（由 asyncAdvanceBotSeason 处理）
            return quotes;
        } finally {
            ADVANCE_LOCK.unlock();
        }
    }

    /**
     * 异步推进机器人赛季的所有交易日，每步推送进度
     */
    @Async
    public void asyncAdvanceBotSeason(Integer seasonId) {
        while (true) {
            StockSeason season = stockSeasonMapper.selectById(seasonId);
            if (season == null || !"PLAYING".equals(season.getStatus())) {
                break;
            }
            AdvanceDayCo nextCo = new AdvanceDayCo();
            nextCo.setSeasonId(seasonId);
            try {
                List<QuoteVo> result = self.advanceDay(nextCo);
                if (result == null || result.isEmpty()) {
                    break;
                }
            } catch (Exception e) {
                log.error("机器人赛季推进失败，终止推进: seasonId={}", seasonId, e);
                break;
            }
        }
        // 最终推送完成状态
        StockSeason season = stockSeasonMapper.selectById(seasonId);
        if (season != null) {
            broadcastProgress(seasonId, season);
        }
    }

    /**
     * 推送赛季进度到 WebSocket
     */
    private void broadcastProgress(Integer seasonId, StockSeason season) {
        Map<String, Object> progress = new HashMap<>();
        progress.put("seasonId", seasonId);
        progress.put("currentTradeDay", season.getCurrentTradeDay());
        progress.put("totalTradeDays", season.getTotalTradeDays());
        progress.put("status", season.getStatus());
        progress.put("percent", season.getTotalTradeDays() > 0 ? (int) (season.getCurrentTradeDay() * 100.0 / season.getTotalTradeDays()) : 0);
        stockWebSocketHandler.broadcastToSeason(seasonId, "progress", progress);
    }

    /**
     * 根据交易日期查询行情并转为VO
     */
    private List<QuoteVo> getQuotesByDate(LocalDate tradeDate) {
        List<StockDaily> dailies = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>().eq(StockDaily::getTradeDate, tradeDate));
        if (dailies.isEmpty()) {
            return Collections.emptyList();
        }
        // 批量查询股票名称
        List<String> stockCodes = dailies.stream().map(StockDaily::getStockCode).distinct().toList();
        Map<String, StockInfo> infoMap = stockInfoMapper.selectList(new LambdaQueryWrapper<StockInfo>().in(StockInfo::getStockCode, stockCodes))
                .stream().collect(Collectors.toMap(StockInfo::getStockCode, Function.identity()));
        return dailies.stream().map(daily -> {
            QuoteVo vo = new QuoteVo();
            vo.setStockCode(daily.getStockCode());
            StockInfo info = infoMap.get(daily.getStockCode());
            vo.setStockName(info != null ? info.getStockName() : daily.getStockCode());
            vo.setClosePrice(daily.getClosePrice());
            vo.setIncreaseRateDay(daily.getIncreaseRateDay());
            return vo;
        }).toList();
    }

    /**
     * 执行所有机器人策略（先卖后买：卖出释放的资金可用于当日买入）
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void executeBotStrategies(Integer seasonId, StockSeason season) {
// 1. 查询该赛季的所有机器人配置
        List<StockBotConfig> botConfigs = stockBotConfigMapper.selectList(new LambdaQueryWrapper<StockBotConfig>()
                .eq(StockBotConfig::getSeasonId, seasonId));
// 2. 获取当日行情（转为 QuoteVo 列表）
        StockSeasonQuote seasonQuote = stockSeasonQuoteMapper.selectOne(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId)
                .eq(StockSeasonQuote::getTradeDay, season.getCurrentTradeDay()));
        if (seasonQuote == null) {
            return;
        }
        List<QuoteVo> quotes = getQuotesByDate(seasonQuote.getTradeDate());
        // 3. 遍历每个机器人，先卖后买
        for (StockBotConfig config : botConfigs) {
            try {
                StockAccount botAccount =
                        stockAccountMapper.selectOne(new LambdaQueryWrapper<StockAccount>()
                                .eq(StockAccount::getSeasonId, seasonId)
                                .eq(StockAccount::getPlayerName, config.getBotName()));
                if (botAccount == null) {
                    continue;
                }
                List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>()
                        .eq(StockPosition::getAccountId, botAccount.getId()));
                // 解析参数 JSON
                Map<String, Object> buyParams = parseParamsJson(config.getBuyParams());
                Map<String, Object> sellParams = parseParamsJson(config.getSellParams());
                // 先执行卖出策略
                SellStrategy sellStrategy = strategyRegistry.getSellStrategy(config.getSellStrategyId());
                if (sellStrategy != null) {
                    BotExecutionContext sellCtx = new BotExecutionContext();
                    sellCtx.setSeasonId(seasonId);
                    sellCtx.setPlayerName(config.getBotName());
                    sellCtx.setSeason(season);
                    sellCtx.setAccount(botAccount);
                    sellCtx.setPositions(positions);
                    sellCtx.setQuotes(quotes);
                    sellCtx.setTradeDate(season.getCurrentTradeDay());
                    sellCtx.setParams(sellParams);
                    sellCtx.setTradeService(stockTradeService);
                    sellCtx.setStockDailyMapper(stockDailyMapper);
                    sellCtx.setStockTradeRecordMapper(stockTradeRecordMapper);
                    sellCtx.setStockPositionMapper(stockPositionMapper);
                    sellCtx.setStockAccountMapper(stockAccountMapper);
                    sellStrategy.execute(sellCtx);
                    // 刷新账户余额
                    botAccount = stockAccountMapper.selectById(botAccount.getId());
                }
                // 再执行买入策略
                BuyStrategy buyStrategy = strategyRegistry.getBuyStrategy(config.getBuyStrategyId());
                if (buyStrategy != null) {
                    BotExecutionContext buyCtx = new BotExecutionContext();
                    buyCtx.setSeasonId(seasonId);
                    buyCtx.setPlayerName(config.getBotName());
                    buyCtx.setSeason(season);
                    buyCtx.setAccount(botAccount);
                    buyCtx.setPositions(positions);
                    buyCtx.setQuotes(quotes);
                    buyCtx.setTradeDate(season.getCurrentTradeDay());
                    buyCtx.setParams(buyParams);
                    buyCtx.setTradeService(stockTradeService);
                    buyCtx.setStockDailyMapper(stockDailyMapper);
                    buyCtx.setStockTradeRecordMapper(stockTradeRecordMapper);
                    buyCtx.setStockPositionMapper(stockPositionMapper);
                    buyCtx.setStockAccountMapper(stockAccountMapper);
                    buyStrategy.execute(buyCtx);
                }
            } catch (Exception e) {
                // 机器人策略失败不影响其他机器人
                log.error("机器人策略执行失败，跳过该机器人: seasonId={}, botName={}", seasonId, config.getBotName(), e);
            }
        }
    }

    /**
     * 注册预置机器人：在stock_bot_config中插入6个预置机器人配置，同时为每个创建stock_account
     */
    private void registerPresetBots(Integer seasonId, BigDecimal initialFund) {
        List<StockBotConfig> presets = List.of(
                createBotConfig(seasonId, "定投机器人", "PERCENT_FIXED_DCA", "NEVER_SELL", null, null),
                createBotConfig(seasonId, "波段机器人", "DIP_BUY", "RISE_SELL", "{\"buyThreshold\":-2}", "{\"sellThreshold\":3}"),
                createBotConfig(seasonId, "趋势机器人", "MOMENTUM_BUY", "WEIGHTED_DROP_SELL", null, null),
                createBotConfig(seasonId, "逆向机器人", "DIP_BUY", "TIERED_SELL", "{\"buyThreshold\":-2}", null),
                createBotConfig(seasonId, "止盈止损机器人", "EQUAL_BUY", "TAKE_PROFIT_STOP_LOSS", null, "{\"takeProfit\":10,\"stopLoss\":-5}"),
                createBotConfig(seasonId, "分批建仓机器人", "SCALE_IN", "RISE_SELL", "{\"totalShares\":5}", "{\"sellThreshold\":3}"));
        for (StockBotConfig config : presets) {
            stockBotConfigMapper.insert(config);
            addBotIfAbsent(seasonId, config.getBotName(), initialFund);
        }
    }

    private StockBotConfig createBotConfig(Integer seasonId, String botName, String buyStrategyId, String sellStrategyId, String buyParams, String sellParams) {
        StockBotConfig config = new StockBotConfig();
        config.setSeasonId(seasonId);
        config.setBotName(botName);
        config.setBuyStrategyId(buyStrategyId);
        config.setSellStrategyId(sellStrategyId);
        config.setBuyParams(buyParams);
        config.setSellParams(sellParams);
        config.setIsPreset(true);
        return config;
    }

    /**
     * 解析策略参数JSON
     */
    private Map<String, Object> parseParamsJson(String json) {
        if (json == null || json.isBlank()) {
            return new HashMap<>();
        }
        try {
            return JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.warn("策略参数JSON解析失败，使用空参数: json={}", json, e);
            return new HashMap<>();
        }
    }

    /**
     * 机器人不存在时自动加入赛季
     */
    private void addBotIfAbsent(Integer seasonId, String botName, BigDecimal initialFund) {
        Long count = stockAccountMapper.selectCount(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId)
                .eq(StockAccount::getPlayerName, botName));
        if (count == 0) {
            StockAccount account = new StockAccount();
            account.setSeasonId(seasonId);
            account.setPlayerName(botName);
            account.setAvailableFund(initialFund);
            stockAccountMapper.insert(account);
        }
    }

    /**
     * 赛季结束时强制清仓：所有账户的持仓按当日收盘价卖出。
     * <p>不走 {@code stockTradeService.sell()}，直接按收盘价结算并删除持仓，
     * 因此豁免 T+1 限制，也不会因单只股票校验失败而把外层事务标记为 rollback-only。
     */
    private void forceLiquidateSeason(Integer seasonId, StockSeason season) {
        var seasonQuote = stockSeasonQuoteMapper.selectOne(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId)
                .eq(StockSeasonQuote::getTradeDay, season.getCurrentTradeDay()));
        if (seasonQuote == null) {
            return;
        }
        var tradeDate = seasonQuote.getTradeDate();
        var accounts = stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>().eq(StockAccount::getSeasonId, seasonId));
        for (StockAccount account : accounts) {
            var positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>()
                    .eq(StockPosition::getAccountId, account.getId()));
            if (positions.isEmpty()) {
                continue;
            }
            // 批量查询收盘价
            var stockCodes = positions.stream().map(StockPosition::getStockCode).distinct().toList();
            var closePriceMap = stockDailyMapper.selectList(new LambdaQueryWrapper<StockDaily>()
                            .in(StockDaily::getStockCode, stockCodes)
                            .eq(StockDaily::getTradeDate, tradeDate))
                    .stream()
                    .collect(Collectors.toMap(StockDaily::getStockCode, StockDaily::getClosePrice, (a, b) -> a));
            BigDecimal totalProceeds = BigDecimal.ZERO;
            for (StockPosition pos : positions) {
                var closePrice = closePriceMap.getOrDefault(pos.getStockCode(), BigDecimal.ZERO);
                var proceeds = closePrice.multiply(BigDecimal.valueOf(pos.getQuantity()));
                totalProceeds = totalProceeds.add(proceeds);

                // 创建卖出交易记录
                var record = new StockTradeRecord();
                record.setAccountId(account.getId());
                record.setStockCode(pos.getStockCode());
                record.setTradeType("SELL");
                record.setQuantity(pos.getQuantity());
                record.setPrice(closePrice);
                record.setAmount(proceeds);
                record.setTradeDay(season.getCurrentTradeDay());
                record.setCreateTime(LocalDateTime.now());
                stockTradeRecordMapper.insert(record);

                // 删除持仓
                stockPositionMapper.deleteById(pos.getId());
            }
            // 卖出所得加入可用资金
            account.setAvailableFund(account.getAvailableFund().add(totalProceeds));
            stockAccountMapper.updateById(account);
        }
    }


    /**
     * 判断是否为纯机器人赛季：赛季没有任何真人玩家账户。
     * 判定依据：机器人账户名都登记在 stock_bot_config.bot_name 中，
     * 凡是账户名不在任何 bot_name 中的，即视为真人玩家。
     */
    private boolean isBotOnlySeason(Integer seasonId) {
        // 该赛季所有机器人名称
        List<String> botNames = stockBotConfigMapper.selectList(new LambdaQueryWrapper<StockBotConfig>()
                        .eq(StockBotConfig::getSeasonId, seasonId)).stream()
                .map(StockBotConfig::getBotName).toList();
        // 没有机器人配置，不可能是纯机器人赛季
        if (botNames.isEmpty()) {
            return false;
        }
        List<StockAccount> accounts = stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId));
        if (accounts.isEmpty()) {
            return false;
        }
        // 只要存在一个账户名不在机器人配置中，说明有真人玩家，非纯机器人赛季
        return accounts.stream().allMatch(account -> botNames.contains(account.getPlayerName()));
    }

    private SeasonVo toSeasonVo(StockSeason season) {
        if (season == null) {
            return null;
        }
        SeasonVo vo = new SeasonVo();
        vo.setId(season.getId());
        vo.setName(season.getName());
        vo.setStatus(season.getStatus());
        vo.setInitialFund(season.getInitialFund());
        vo.setCurrentTradeDay(season.getCurrentTradeDay());
        vo.setTotalTradeDays(season.getTotalTradeDays());
        vo.setHistoryRevealed(season.getHistoryRevealed());

        // 仅在 historyRevealed=true 时填充历史日期
        if (Boolean.TRUE.equals(season.getHistoryRevealed())) {
            vo.setHistoryStartDate(season.getHistoryStartDate());
            vo.setHistoryEndDate(season.getHistoryEndDate());
        }
        // 统计已准备人数与总人数（仅统计真人玩家，排除机器人）
        List<String> botNames = stockBotConfigMapper.selectList(new LambdaQueryWrapper<StockBotConfig>()
                        .eq(StockBotConfig::getSeasonId, season.getId())).stream()
                .map(StockBotConfig::getBotName).toList();
        List<StockAccount> humanAccounts = stockAccountMapper.selectList(new LambdaQueryWrapper<StockAccount>()
                        .eq(StockAccount::getSeasonId, season.getId())).stream()
                .filter(a -> !botNames.contains(a.getPlayerName()))
                .toList();
        vo.setTotalCount(humanAccounts.size());
        vo.setReadyCount((int) humanAccounts.stream().filter(a -> Boolean.TRUE.equals(a.getReady())).count());
        return vo;
    }
}
