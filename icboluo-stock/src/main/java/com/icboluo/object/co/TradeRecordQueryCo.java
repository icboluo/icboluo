package com.icboluo.object.co;

import com.icboluo.common.PageQuery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 交易记录查询请求对象
 */
@Data
public class TradeRecordQueryCo extends PageQuery {
    @NotNull(message = "赛季ID不能为空")
    private Integer seasonId;

    @NotBlank(message = "玩家名称不能为空")
    private String playerName;
}
