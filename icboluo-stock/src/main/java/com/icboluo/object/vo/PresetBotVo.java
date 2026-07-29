package com.icboluo.object.vo;

import lombok.Data;

/**
 * 机器人配置响应对象
 */
@Data
public class PresetBotVo {
    /**
     * ID（组合机器人有ID，预置机器人无ID）
     */
    private Integer id;
    /**
     * 机器人名称
     */
    private String name;
    /**
     * 机器人名称（兼容组合机器人的botName字段）
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
     * 买入策略名称
     */
    private String buyStrategyName;
    /**
     * 卖出策略名称
     */
    private String sellStrategyName;
    /**
     * 买入策略参数（JSON字符串）
     */
    private String buyParams;
    /**
     * 卖出策略参数（JSON字符串）
     */
    private String sellParams;
}
