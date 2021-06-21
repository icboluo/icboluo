package com.icboluo.object.query;

import com.icboluo.common.PageQuery;
import lombok.Data;

/**
 * @author icboluo
 * @date 2021-03-31 22:03
 */
@Data
public class FundDataQuery extends PageQuery {

    private String fundId;

    /**
     * TODO TO LOCAL DATE TIME
     */
    private String startTime;
}
