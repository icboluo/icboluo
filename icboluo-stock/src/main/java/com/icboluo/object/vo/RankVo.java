package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 排行榜响应对象
 */
@Data
public class RankVo {
    /**
     * 排名
     */
    private Integer rank;
    /**
     * 玩家名称
     */
    private String playerName;
    /**
     * 总资产
     */
    private BigDecimal totalAsset;
    /**
     * 收益率
     */
    private BigDecimal profitRate;
    /**
     * 持仓股票种数
     */
    private Integer positionCount;
    /**
     * 持仓股票名称列表
     */
    private List<String> positionStockNames;
}
