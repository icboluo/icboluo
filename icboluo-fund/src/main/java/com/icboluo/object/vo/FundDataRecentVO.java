package com.icboluo.object.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author icboluo
 * @date 2021-06-10 1:06
 */
@Data
@Builder
public class FundDataRecentVO {

    private List<FundDataVO> list;

    private Long count;

    private Double min;

    private Double max;

    private BigDecimal avg;

    private BigDecimal nextAvg;
}
