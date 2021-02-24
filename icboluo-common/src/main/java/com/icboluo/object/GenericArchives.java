package com.icboluo.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @date 2021-47-24 21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericArchives<T> {
    /**
     * d
     */
    private T id;

    /**
     * 名称
     */
    private String name;

}
