package com.icboluo.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @since 2021-47-24 21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericArchives<I,N> {
    /**
     * id
     */
    private I id;

    /**
     * 名称
     */
    private N name;

}
