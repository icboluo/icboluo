package com.icboluo.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物料的视图对象，比档案对象对了数量字段
 *
 * @author icboluo
 * @since 2020/12/3 21:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materials {
    /**
     * 项
     */
    private CodeName item;

    /**
     * 数量
     */
    private Integer num;
}
