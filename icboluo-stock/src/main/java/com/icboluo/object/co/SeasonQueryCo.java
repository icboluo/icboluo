package com.icboluo.object.co;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 赛季查询请求对象
 */
@Data
public class SeasonQueryCo {
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;
}
