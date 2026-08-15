package com.icboluo.service;

import com.github.pagehelper.PageInfo;
import com.icboluo.object.co.TradeCo;
import com.icboluo.object.vo.TradeRecordVo;

/**
 * 股票交易服务接口
 *
 * @author icboluo
 * @since 2026-07-25 19:34
 */
public interface StockTradeService {
    /**
     * 买入股票
     *
     * @param co         交易请求（含 seasonId、stockCode、quantity）
     * @param playerName 玩家名称
     * @return 交易记录
     */
    TradeRecordVo buy(TradeCo co, String playerName);

    /**
     * 卖出股票（T+1规则：当天买入的不能当天卖出）
     *
     * @param co         交易请求（含 seasonId、stockCode、quantity）
     * @param playerName 玩家名称
     * @return 交易记录
     */
    TradeRecordVo sell(TradeCo co, String playerName);

    /**
     * 分页查询交易记录
     *
     * @param seasonId   赛季ID
     * @param playerName 玩家名称
     * @param stockCode  股票代码（可选，非空则只查该股票）
     * @param pageNum    页码
     * @param pageSize   每页条数
     * @return 交易记录分页
     */
    PageInfo<TradeRecordVo> getTradeRecords(Integer seasonId, String playerName, String stockCode, int pageNum, int pageSize);
}
