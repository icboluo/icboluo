package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易记录响应对象
 */
@Data
public class TradeRecordVo {
    /**
     * 记录ID
     */
    private Integer id;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 交易类型 BUY/SELL
     */
    private String tradeType;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 交易日
     */
    private Integer tradeDay;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
