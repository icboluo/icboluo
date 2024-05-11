package com.icboluo.object.query;


import com.icboluo.common.PageQuery;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author icboluo
 * @since 2021-03-31 22:03
 */
@Data
public class FundDataQuery extends PageQuery {

    @NotEmpty
    private String fundId;

    /**
     * 开始日期
     */
    private LocalDate startDate;
    /**
     * 结束日期
     */
    private LocalDate endDate;


    /**
     * 选择的开始对比日期
     */
    private LocalDate chooseDate;

    /**
     * 选择的日期对比长度
     */
    private Integer chooseDateLength;

}
