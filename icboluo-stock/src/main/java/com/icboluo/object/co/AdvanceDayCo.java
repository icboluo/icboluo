package com.icboluo.object.co;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 推进交易日请求对象
 */
@Data
public class AdvanceDayCo {
    /**
     * 赛季ID
     */
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;
}
