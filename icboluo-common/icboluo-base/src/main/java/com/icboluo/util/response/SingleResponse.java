
package com.icboluo.util.response;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
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

    public static <T> SingleResponse<T> generate(String json) {
        // TypeReference 可以消除 <>.class 警告
        // return JSON.parseObject(json, SingleResponse.class);
        return JSON.parseObject(json, new TypeReference<SingleResponse<T>>() {
        });
    }
}
