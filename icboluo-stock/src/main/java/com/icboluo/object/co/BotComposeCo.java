package com.icboluo.object.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 组合机器人创建请求对象
 */
@Data
public class BotComposeCo {
    /**
     * 机器人名称
     */
    @NotBlank(message = "机器人名称不能为空")
    private String name;
    /**
     * 买入策略标识
     */
    @NotBlank(message = "买入策略标识不能为空")
    private String buyStrategyId;
    /**
     * 卖出策略标识
     */
    @NotBlank(message = "卖出策略标识不能为空")
    private String sellStrategyId;
    /**
     * 买入策略参数覆盖（JSON字符串）
     */
    private String buyParams;
    /**
     * 卖出策略参数覆盖（JSON字符串）
     */
    private String sellParams;
    /**
     * 赛季ID
     */
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;
}
