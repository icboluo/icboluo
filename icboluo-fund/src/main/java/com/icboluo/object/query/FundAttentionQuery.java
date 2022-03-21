package com.icboluo.object.query;


import com.icboluo.common.PageQuery;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @date 2021-28-09 0:28
 */
@Data
public class FundAttentionQuery extends PageQuery {

    /**
     * 查询日期
     */
    private LocalDateTime date;
}
