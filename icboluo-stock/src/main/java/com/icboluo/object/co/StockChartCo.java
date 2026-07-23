package com.icboluo.object.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 股票走势图查询请求对象
 */
@Data
public class StockChartCo {
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;

    @NotBlank(message = "股票代码不能为空")
    private String stockCode;
    /**
     * 玩家名称（可选，不传则不返回买卖标记）
     */
    private String playerName;
}
