package com.icboluo.util;

import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.response.Response;
import lombok.Getter;

/**
 * <p>公共异常
 * <p>自定义异常应该继承 Exception|RuntimeException, 不应该继承 Throwable
 *
 * @author icboluo
 */
@Getter
@SuppressWarnings("unused")
public class IcBoLuoException extends RuntimeException {
    /**
     * 状态码
     */
    private final String status;

    public IcBoLuoException() {
        super();
        this.status = Response.ERROR_CODE;
    }

    public IcBoLuoException(String msg) {
        super(msg);
        this.status = Response.ERROR_CODE;
    }

    public IcBoLuoException(ReEnum em) {
        super(em.getMsg());
        this.status = em.getCode();
    }

    public IcBoLuoException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
        this.status = Response.ERROR_CODE;
    }

    public IcBoLuoException(ReEnum em, Throwable throwable) {
        super(em.getMsg(), throwable);
        this.status = em.getCode();
    }

    public IcBoLuoException(String status, String msg) {
        super(msg);
        this.status = status;
    }
}
