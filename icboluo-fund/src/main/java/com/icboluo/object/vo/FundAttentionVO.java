package com.icboluo.object.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author icboluo
 * @date 2021-28-09 0:28
 */
@Data
public class FundAttentionVO {

    private String id;

    private String name;
    /**
     * 年增长率map
     */
    private Map<Integer, BigDecimal> avgMap;

    private BigDecimal tenAvg;

    /**
     * 一年以来总天数
     */
    private Integer totalDay;
    /**
     * 一年以来亏损总天数
     */
    private Integer lossTotalDay;

    /**
     * 定投总收益
     */
    private BigDecimal fixedVote;

    /**
     * 亏损投入总收益
     */
    private BigDecimal lossInvestment;
}
