
package com.icboluo.util.response;

import com.alibaba.fastjson.JSON;
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

    public SingleResponse(CodeMessage codeMessage) {
        super(codeMessage);
    }

    public SingleResponse(T data, CodeMessage codeMessage) {
        super(codeMessage);
        this.data = data;
    }

    public SingleResponse(String code, String message) {
        super(code, message);
    }

    public static SingleResponse build(String json) {
        return JSON.parseObject(json, SingleResponse.class);
    }
}
