package com.icboluo.object.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 赛季加入请求对象
 */
@Data
public class SeasonJoinCo {
    /**
     * 赛季ID
     */
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;
    /**
     * 玩家昵称
     */
    @NotBlank(message = "玩家昵称不能为空")
    private String playerName;
}
