package com.icboluo.strategy;

import com.icboluo.entity.StockAccount;
import com.icboluo.entity.StockPosition;
import com.icboluo.entity.StockSeason;
import com.icboluo.mapper.StockAccountMapper;
import com.icboluo.mapper.StockDailyMapper;
import com.icboluo.mapper.StockPositionMapper;
import com.icboluo.mapper.StockTradeRecordMapper;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.service.StockTradeService;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 机器人策略执行上下文
 * <p>封装策略执行时所需的全部信息，包括赛季、账户、持仓、行情、参数及各种 Mapper 和 Service。
 */
@Data
public class BotExecutionContext {
    /**
     * 赛季ID
     */
    private Integer seasonId;
    /**
     * 玩家名称
     */
    private String playerName;
    /**
     * 赛季信息（策略需要获取 initialFund 和 totalTradeDays）
     */
    private StockSeason season;
    /**
     * 当前账户
     */
    private StockAccount account;
    /**
     * 当前持仓列表
     */
    private List<StockPosition> positions;
    /**
     * 当日行情列表
     */
    private List<QuoteVo> quotes;
    /**
     * 当前交易日序号
     */
    private Integer tradeDate;
    /**
     * 策略自定义参数（来自机器人配置的 params JSON）
     */
    private Map<String, Object> params;
    /**
     * 交易服务（用于执行买入/卖出）
     */
    private StockTradeService tradeService;
    /**
     * 日行情 Mapper（策略需要查询当日行情明细）
     */
    private StockDailyMapper stockDailyMapper;
    /**
     * 交易记录 Mapper（策略如止盈止损需要查询买入记录计算成本）
     */
    private StockTradeRecordMapper stockTradeRecordMapper;
    /**
     * 持仓 Mapper（策略需要查询持仓）
     */
    private StockPositionMapper stockPositionMapper;
    /**
     * 账户 Mapper（卖出后需要刷新账户余额）
     */
    private StockAccountMapper stockAccountMapper;
}
