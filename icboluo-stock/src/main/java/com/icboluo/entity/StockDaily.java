package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 股票日线行情实体类
 * <p>存储真实A股历史日线数据，赛季推进交易日时从中读取收盘价作为成交价。
 */
@Data
public class StockDaily implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 交易日期
     */
    private LocalDate tradeDate;
    /**
     * 收盘价（作为模拟交易的成交价）
     */
    private BigDecimal closePrice;
    /**
     * 日涨跌幅（%）
     */
    private BigDecimal increaseRateDay;
    /**
     * 累计净值
     */
    private BigDecimal netValueCumulative;
}
