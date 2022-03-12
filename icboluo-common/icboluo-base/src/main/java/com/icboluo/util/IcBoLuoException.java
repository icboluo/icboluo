package com.icboluo.util;

import com.icboluo.enumerate.ReEnum;
import lombok.Getter;

/**
 * 公共异常
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

    public IcBoLuoException(ReEnum em, Throwable throwable) {
        super(em.getMessage(), throwable);
        this.status = em.getCode();
    }

    public IcBoLuoException(int status, String msg) {
        super(msg);
        this.status = status;
    }
}
