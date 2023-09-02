package com.icboluo.util;

import com.icboluo.enumerate.ReEnum;
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
    private final int status;

    public IcBoLuoException() {
        super();
        this.status = 500;
    }

    public IcBoLuoException(String msg) {
        super(msg);
        this.status = 500;
    }

    public IcBoLuoException(ReEnum em) {
        super(em.getMessage());
        this.status = em.getCode();
    }

    public IcBoLuoException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
        this.status = 500;
    }

    public IcBoLuoException(ReEnum em, Throwable throwable) {
        super(em.getMessage(), throwable);
        this.status = em.getCode();
    }

    public IcBoLuoException(int status, String msg) {
        super(msg);
        this.status = status;
    }
}
