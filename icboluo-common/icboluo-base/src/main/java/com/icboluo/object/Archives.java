package com.icboluo.object;

import lombok.Data;

/**
 * 档案对象
 *
 * @author icboluo
 * @since 2023-02-17 19:37
 */
@Data
public class Archives<I, N> {

    /**
     * id
     */
    private I id;


    /**
     * 名称
     */
    private N name;
}
