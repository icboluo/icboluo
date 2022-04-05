package com.icboluo.object.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-27-01 0:27
 */
@Data
@Builder
public class FundDataCalVO {

    private Long count;

    private Double min;

    private Double max;

    private BigDecimal avg;

    private Map<DayOfWeek, BigDecimal> weekMap;

    private Map<Integer, BigDecimal> monthMap;
}
