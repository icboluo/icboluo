
package com.icboluo.util.response;

import com.alibaba.fastjson.JSON;
import com.icboluo.enumerate.ReEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 */
@NoArgsConstructor
@Data
public class SingleResponse<T> extends Response {

    /**
     * 数据
     */
    private T data;

    public SingleResponse(ReEnum reEnum) {
        super(reEnum);
    }

    public SingleResponse(T data, ReEnum reEnum) {
        super(reEnum);
        this.data = data;
    }

    public SingleResponse(String code, String message) {
        super(code, message);
    }

    public static SingleResponse build(String json) {
        return JSON.parseObject(json, SingleResponse.class);
    }
}
