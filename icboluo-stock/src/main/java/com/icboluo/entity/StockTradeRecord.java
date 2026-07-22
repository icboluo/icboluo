package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockTradeRecord implements Serializable {
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
     * 交易类型
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
