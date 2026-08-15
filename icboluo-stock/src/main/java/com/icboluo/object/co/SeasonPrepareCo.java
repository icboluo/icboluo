package com.icboluo.object.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 玩家准备参数
 */
@Data
public class SeasonPrepareCo {
    /**
     * 赛季ID
     */
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;
    /**
     * 玩家名称
     */
    @NotBlank(message = "玩家昵称不能为空")
    private String playerName;
}
