package com.icboluo.object;

import lombok.Data;

/**
 * 饼图
 *
 * @author icboluo
 * @since 2023-07-05 19:28
 */
@Data
public class PieChart {

    /**
     * 类型
     */
    private IdName type;

    /**
     * 数量
     */
    private Integer num;
}
