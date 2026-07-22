package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 股票赛季实体类
 * <p>管理一个赛季的生命周期，包括创建、加入、开始、推进交易日等。
 */
@Data
public class StockSeason implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 赛季名称
     */
    private String name;
    /**
     * 赛季状态：PREPARING /PLAYING /FINISHED
     */
    private String status;
    /**
     * 初始资金（每位玩家加入时获得的起始资金）
     */
    private BigDecimal initialFund;
    /**
     * 历史行情起始日期（从真实A股历史数据中截取的起始日期）
     */
    private LocalDate historyStartDate;
    /**
     * 历史行情结束日期
     */
    private LocalDate historyEndDate;
    /**
     * 当前交易日序号（从1开始，0表示尚未开始）
     */
    private Integer currentTradeDay;
    /**
     * 总交易日天数
     */
    private Integer totalTradeDays;
    /**
     * 是否已向玩家揭示历史行情区间（防止玩家利用真实数据回测）
     */
    private Boolean historyRevealed;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
