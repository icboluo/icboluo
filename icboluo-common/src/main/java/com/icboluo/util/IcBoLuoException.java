package com.icboluo.util;

import com.icboluo.common.ExceptionEnum;
import lombok.Getter;

/**
 * @author icboluo
 */
@Getter
@SuppressWarnings("unused")
public class IcBoLuoException extends RuntimeException {
    /**
     * 状态码
     */
    private int status;

    public IcBoLuoException() {
        super();
        this.status = 500;
    }

    public IcBoLuoException(String msg) {
        super(msg);
        this.status = 500;
    }

    public IcBoLuoException(ExceptionEnum em) {
        super(em.getMessage());
        this.status = em.getStatus();
    }

    public IcBoLuoException(ExceptionEnum em, Throwable throwable) {
        super(em.getMessage(), throwable);
        this.status = em.getStatus();
    }

    public IcBoLuoException(int status, String msg) {
        super(msg);
        this.status = status;
    }
}
