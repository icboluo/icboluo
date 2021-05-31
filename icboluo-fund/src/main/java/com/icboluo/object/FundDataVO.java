package com.icboluo.object;

import lombok.Builder;
import lombok.Data;

/**
 * @author icboluo
 * @date 2021-27-01 0:27
 */
@Data
@Builder
public class FundDataVO {

    private Long count;

    private Double min;

    private Double max;

    private Double avg;
}
