package com.icboluo.object;

import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @since 2023-02-17 19:38
 */
@NoArgsConstructor
public class IdName extends Archives<Integer, String> {
    public IdName(Integer key, String val) {
        super(key, val);
    }
}
