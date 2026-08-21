package com.icboluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icboluo.util.BeanUtil;
import com.icboluo.entity.*;
import com.icboluo.mapper.*;
import com.icboluo.object.co.TradeCo;
import com.icboluo.object.vo.TradeRecordVo;
import com.icboluo.service.StockTradeService;
import com.icboluo.util.I18nException;
import com.icboluo.websocket.StockWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 股票交易服务实现
 * <p>模拟交易：以赛季当前交易日对应的 stock_daily 收盘价为成交价。
 * 持仓按 (account_id, stock_code, buy_trade_day) 分批次记录，用于支持 T+1 校验。
 *
 * @author icboluo
 * @since 2026-08-04 22:42
 */
@Service
@RequiredArgsConstructor
public class StockTradeServiceImpl implements StockTradeService {

    private final StockAccountMapper stockAccountMapper;
    private final StockPositionMapper stockPositionMapper;
    private final StockTradeRecordMapper stockTradeRecordMapper;
    private final StockSeasonMapper stockSeasonMapper;
    private final StockSeasonQuoteMapper stockSeasonQuoteMapper;
    private final StockDailyMapper stockDailyMapper;
    private final StockWebSocketHandler webSocketHandler;

    @Override
    @Transactional
    public TradeRecordVo buy(TradeCo co, String playerName) {
        StockAccount account = getAccount(co.getSeasonId(), playerName);
        StockSeason season = stockSeasonMapper.selectById(co.getSeasonId());
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        BigDecimal price = getClosePrice(co.getSeasonId(), season.getCurrentTradeDay(), co.getStockCode());
        BigDecimal amount = price.multiply(BigDecimal.valueOf(co.getQuantity())).setScale(2, RoundingMode.HALF_UP);
        // 校验资金是否充足
        if (account.getAvailableFund().compareTo(amount) < 0) {
            throw new I18nException("可用资金不足，需要 " + amount + "，当前 " + account.getAvailableFund());
        }
        // 扣减可用资金
        account.setAvailableFund(account.getAvailableFund().subtract(amount));
        stockAccountMapper.updateById(account);
        // 更新持仓（同一股票同一买入交易日合并）
        upsertPosition(account.getId(), co.getStockCode(), co.getQuantity(), season.getCurrentTradeDay());
        // 写交易记录
        StockTradeRecord record = buildRecord(account.getId(), co.getStockCode(), "BUY", co.getQuantity(),
                price, amount, season.getCurrentTradeDay());
        stockTradeRecordMapper.insert(record);
        // WebSocket 推送交易事件
        webSocketHandler.broadcastToSeason(co.getSeasonId(), "trade", toVo(record));
        return toVo(record);
    }

    @Override
    @Transactional
    public TradeRecordVo sell(TradeCo co, String playerName) {
        StockAccount account = getAccount(co.getSeasonId(), playerName);
        StockSeason season = stockSeasonMapper.selectById(co.getSeasonId());
        if (season == null) {
            throw new I18nException("赛季不存在");
        }
        // 查询该股票持仓（取最早买入批次用于 T+1 校验）
        List<StockPosition> positions = stockPositionMapper.selectList(new LambdaQueryWrapper<StockPosition>()
                .eq(StockPosition::getAccountId, account.getId())
                .eq(StockPosition::getStockCode, co.getStockCode())
                .orderByAsc(StockPosition::getBuyTradeDay));
        int heldQuantity = positions.stream().mapToInt(StockPosition::getQuantity).sum();
        if (heldQuantity < co.getQuantity()) {
            throw new I18nException("持仓不足，持有 " + heldQuantity + "，卖出 " + co.getQuantity());
        }
        // T+1 校验：最早买入批次的交易日必须小于当前交易日
        int earliestBuyDay = positions.getFirst().getBuyTradeDay();
        if (earliestBuyDay >= season.getCurrentTradeDay()) {
            throw new I18nException("T+1 限制：当日买入的股票不能当日卖出");
        }
        BigDecimal price = getClosePrice(co.getSeasonId(), season.getCurrentTradeDay(), co.getStockCode());
        BigDecimal amount = price.multiply(BigDecimal.valueOf(co.getQuantity())).setScale(2, RoundingMode.HALF_UP);
        // 增加可用资金
        account.setAvailableFund(account.getAvailableFund().add(amount));
        stockAccountMapper.updateById(account);
        // 减少持仓（从最早批次开始扣减）
        reducePositions(positions, co.getQuantity());
        // 写交易记录
        StockTradeRecord record = buildRecord(account.getId(), co.getStockCode(), "SELL", co.getQuantity(),
                price, amount, season.getCurrentTradeDay());
        stockTradeRecordMapper.insert(record);
        // WebSocket 推送交易事件
        webSocketHandler.broadcastToSeason(co.getSeasonId(), "trade", toVo(record));
        return toVo(record);
    }

    @Override
    public PageInfo<TradeRecordVo> getTradeRecords(Integer seasonId, String playerName, String stockCode, int pageNum, int pageSize) {
        StockAccount account = getAccount(seasonId, playerName);
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<StockTradeRecord> wrapper = new LambdaQueryWrapper<StockTradeRecord>()
                .eq(StockTradeRecord::getAccountId, account.getId())
                .orderByDesc(StockTradeRecord::getTradeDay)
                .orderByDesc(StockTradeRecord::getId);
        if (stockCode != null && !stockCode.isBlank()) {
            wrapper.eq(StockTradeRecord::getStockCode, stockCode);
        }
        List<StockTradeRecord> records = stockTradeRecordMapper.selectList(wrapper);
        List<TradeRecordVo> vos = records.stream().map(this::toVo).toList();
        return BeanUtil.pageInfoConvert(PageInfo.of(records), vos);
    }

    /**
     * 按赛季与玩家名查询账户，不存在则抛异常
     */
    private StockAccount getAccount(Integer seasonId, String playerName) {
        StockAccount account = stockAccountMapper.selectOne(new LambdaQueryWrapper<StockAccount>()
                .eq(StockAccount::getSeasonId, seasonId)
                .eq(StockAccount::getPlayerName, playerName));
        if (account == null) {
            throw new I18nException("账户不存在：seasonId=" + seasonId + ", playerName=" + playerName);
        }
        return account;
    }

    /**
     * 取某交易日某股票的收盘价作为成交价
     */
    private BigDecimal getClosePrice(Integer seasonId, Integer tradeDay, String stockCode) {
        StockSeasonQuote quote = stockSeasonQuoteMapper.selectOne(new LambdaQueryWrapper<StockSeasonQuote>()
                .eq(StockSeasonQuote::getSeasonId, seasonId)
                .eq(StockSeasonQuote::getTradeDay, tradeDay));
        if (quote == null) {
            throw new I18nException("赛季交易日不存在：tradeDay=" + tradeDay);
        }
        StockDaily daily = stockDailyMapper.selectOne(new LambdaQueryWrapper<StockDaily>()
                .eq(StockDaily::getStockCode, stockCode)
                .eq(StockDaily::getTradeDate, quote.getTradeDate()));
        if (daily == null || daily.getClosePrice() == null) {
            throw new I18nException("无行情数据：stockCode=" + stockCode + ", tradeDate=" + quote.getTradeDate());
        }
        return daily.getClosePrice();
    }

    /**
     * 同一股票同一买入交易日合并持仓，不存在则新增
     */
    private void upsertPosition(Integer accountId, String stockCode, Integer quantity, Integer buyTradeDay) {
        StockPosition position = stockPositionMapper.selectOne(new LambdaQueryWrapper<StockPosition>()
                .eq(StockPosition::getAccountId, accountId)
                .eq(StockPosition::getStockCode, stockCode)
                .eq(StockPosition::getBuyTradeDay, buyTradeDay));
        if (position == null) {
            position = new StockPosition();
            position.setAccountId(accountId);
            position.setStockCode(stockCode);
            position.setBuyTradeDay(buyTradeDay);
            position.setQuantity(quantity);
            stockPositionMapper.insert(position);
        } else {
            position.setQuantity(position.getQuantity() + quantity);
            stockPositionMapper.updateById(position);
        }
    }

    /**
     * 按批次从早到晚扣减持仓数量，数量为 0 的批次删除
     */
    private void reducePositions(List<StockPosition> positions, Integer toSell) {
        int remaining = toSell;
        for (StockPosition position : positions) {
            if (remaining <= 0) {
                break;
            }
            int q = position.getQuantity();
            if (q <= remaining) {
                stockPositionMapper.deleteById(position.getId());
                remaining -= q;
            } else {
                position.setQuantity(q - remaining);
                stockPositionMapper.updateById(position);
                remaining = 0;
            }
        }
    }

    /**
     * 构造交易记录实体
     */
    private StockTradeRecord buildRecord(Integer accountId, String stockCode, String tradeType, Integer quantity,
                                         BigDecimal price, BigDecimal amount, Integer tradeDay) {
        StockTradeRecord record = new StockTradeRecord();
        record.setAccountId(accountId);
        record.setStockCode(stockCode);
        record.setTradeType(tradeType);
        record.setQuantity(quantity);
        record.setPrice(price);
        record.setAmount(amount);
        record.setTradeDay(tradeDay);
        record.setCreateTime(LocalDateTime.now());
        return record;
    }

    /**
     * 交易记录实体转 VO
     */
    private TradeRecordVo toVo(StockTradeRecord record) {
        TradeRecordVo vo = new TradeRecordVo();
        vo.setId(record.getId());
        vo.setStockCode(record.getStockCode());
        vo.setTradeType(record.getTradeType());
        vo.setQuantity(record.getQuantity());
        vo.setPrice(record.getPrice());
        vo.setAmount(record.getAmount());
        vo.setTradeDay(record.getTradeDay());
        vo.setCreateTime(record.getCreateTime());
        return vo;
    }
}
