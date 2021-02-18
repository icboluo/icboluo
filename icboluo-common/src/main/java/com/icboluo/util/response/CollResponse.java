package com.icboluo.util.response;

import com.icboluo.enumerate.ReEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * @author icboluo
 * @date 2021-14-18 21:14
 */
public class CollResponse<T> extends Response {

    /**
     * 数据
     */
    @Getter
    @Setter
    private Collection<T> data;

    public CollResponse(ReEnum reEnum) {
        super(reEnum);
    }

    public CollResponse(Collection<T> data, ReEnum reEnum) {
        super(reEnum);
        this.data = data;
    }

    public CollResponse(Integer code, String message) {
        super(code, message);
    }
}
