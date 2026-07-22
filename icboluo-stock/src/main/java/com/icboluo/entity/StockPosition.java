package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 唯一约束：(account_id, stock_code, buy_trade_day)，按买入批次记录持仓以支持T+1校验
 */
@Data
public class StockPosition implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 账户id
     */
    private Integer accountId;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 持仓数量
     */
    private Integer quantity;
    /**
     * 买入交易日
     */
    private Integer buyTradeDay;
}
