package com.icboluo.object;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案对象
 *
 * @author icboluo
 * @since 2020/12/3 21:40
 */
@Data
@NoArgsConstructor
public class CodeName extends Archives<String, String> {

    public CodeName(String key, String val) {
        super(key, val);
    }
}
