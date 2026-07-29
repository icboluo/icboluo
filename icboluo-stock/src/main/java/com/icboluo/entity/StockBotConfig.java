package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StockBotConfig implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 赛季ID
     */
    private Integer seasonId;
    /**
     * 机器人名称(联合唯一)
     */
    private String botName;
    /**
     * 买入策略标识
     */
    private String buyStrategyId;
    /**
     * 卖出策略标识
     */
    private String sellStrategyId;
    /**
     * 买入策略参数JSON
     */
    private String buyParams;
    /**
     * 卖出策略参数JSON
     */
    private String sellParams;
    /**
     * 是否预置机器人
     */
    private Boolean isPreset;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
