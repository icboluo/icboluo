package com.icboluo.object.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author icboluo
 * @since 2021-06-10 1:06
 */
@Data
@Builder
public class FundDataRecentVO {

    private List<FundDataVO> list;

    private Long count;

    private Double min;

    private Double max;

    private BigDecimal avg;

    /**
     * 紧接着10天的平均值
     */
    private BigDecimal nextAvg;
}
