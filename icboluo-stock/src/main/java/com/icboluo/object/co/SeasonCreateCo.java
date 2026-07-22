package com.icboluo.object.co;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 赛季创建请求对象
 */
@Data
public class SeasonCreateCo {
    /**
     * 赛季名称
     */
    @NotBlank(message = "赛季名称不能为空")
    private String name;
    /**
     * 初始资金
     */
    private BigDecimal initialFund = new BigDecimal("1000000");
}
