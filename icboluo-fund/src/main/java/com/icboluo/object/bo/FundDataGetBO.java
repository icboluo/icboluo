package com.icboluo.object.bo;

import com.icboluo.common.PageQuery;
import lombok.Builder;
import lombok.Data;

/**
 * @author icboluo
 * @since 2021-49-07 23:49
 */
@Data
@Builder
public class FundDataGetBO extends PageQuery {

    private String fundId;

    private String startTime;

    private String endTime;

}
