package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户响应对象
 */
@Data
public class AccountVo {
    /**
     * 账户ID
     */
    private Integer id;
    /**
     * 玩家名称
     */
    private String playerName;
    /**
     * 可用资金
     */
    private BigDecimal availableFund;
    /**
     * 总资产 = availableFund + ∑(position.quantity × closePrice)
     */
    private BigDecimal totalAsset;
    /**
     * 收益率 = (totalAsset - initialFund) / initialFund × 100
     */
    private BigDecimal profitRate;
    /**
     * 初始资金（从赛季配置获取）
     */
    private BigDecimal initialFund;
    /**
     * 持仓列表
     */
    private List<PositionVo> positions;
}
