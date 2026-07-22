package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 收益曲线数据点
 */
@Data
public class ProfitPointVo {
    /**
     * 交易日序号
     */
    private Integer tradeDay;
    /**
     * 总资产
     */
    private BigDecimal totalAsset;
    /**
     * 收益率
     */
    private BigDecimal profitRate;
}
