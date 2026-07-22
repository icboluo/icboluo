package com.icboluo.object.co;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 交易请求对象（买入/卖出共用）
 */
@Data
public class TradeCo {
    /**
     * 赛季ID
     */
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;
    /**
     * 玩家名称
     */
    @NotBlank(message = "玩家名称不能为空")
    private String playerName;
    /**
     * 股票代码
     */
    @NotBlank(message = "股票代码不能为空")
    private String stockCode;
    /**
     * 数量（必须为100的整数倍，1手=100股）
     */
    @NotNull(message = "数量不能为空")
    @Min(value = 100, message = "数量不能小于100")
    private Integer quantity;
}
