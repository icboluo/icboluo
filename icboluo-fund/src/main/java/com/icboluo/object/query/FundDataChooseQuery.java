package com.icboluo.object.query;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author icboluo
 * @date 2021-55-08 23:55
 */
@Data
@Builder
public class FundDataChooseQuery {

    private String fundId;

    /**
     * 选择的开始对比日期
     */
    private LocalDate chooseDate;

    /**
     * 选择的日期对比长度
     */
    private Integer chooseDateLength;

    /**
     * 增长率最大值,好像不能作为查询条件
     */
    private BigDecimal max;

    /**
     * 增长率最小值
     */
    private BigDecimal min;
}
