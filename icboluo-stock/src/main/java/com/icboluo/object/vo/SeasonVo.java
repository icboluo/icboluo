package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 赛季响应对象
 */
@Data
public class SeasonVo {
    /**
     * 赛季ID
     */
    private Integer id;
    /**
     * 赛季名称
     */
    private String name;
    /**
     * 赛季状态
     */
    private String status;
    /**
     * 初始资金
     */
    private BigDecimal initialFund;
    /**
     * 当前交易日
     */
    private Integer currentTradeDay;
    /**
     * 总交易日数
     */
    private Integer totalTradeDays;
    /**
     * 历史行情是否已揭示
     */
    private Boolean historyRevealed;
    /**
     * 历史行情起始日期（仅在 historyRevealed=true 时有值）
     */
    private LocalDate historyStartDate;
    /**
     * 历史行情结束日期
     */
    private LocalDate historyEndDate;
    /**
     * 已准备人数
     */
    private Integer readyCount;
    /**
     * 总人数（赛季账户数）
     */
    private Integer totalCount;
}
