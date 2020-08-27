
package com.icboluo.util;

import com.icboluo.common.ReEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author icboluo
 */
@NoArgsConstructor
public class SingleResponse<T> extends Response {
    /**
     * 数据
     */
    @Getter
    @Setter
    private T data;

    public SingleResponse(ReEnum reEnum) {
        super(reEnum);
    }

    public SingleResponse(T data, ReEnum reEnum) {
        super(reEnum);
        this.data = data;
    }

    public SingleResponse(Integer code, String message) {
        super(code, message);
    }
}
