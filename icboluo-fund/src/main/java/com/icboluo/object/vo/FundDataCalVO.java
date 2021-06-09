package com.icboluo.object.vo;

import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * @author icboluo
 * @date 2021-27-01 0:27
 */
@Data
@Builder
public class FundDataCalVO {

    private Long count;

    private Double min;

    private Double max;

    private Double avg;

    private Integer incrIncr;
    private Integer incrDecr;
    private Integer decrIncr;
    private Integer decrDecr;

    private Map<DayOfWeek, Double> weekMap;
    private Map<Integer, Double> monthMap;
}
