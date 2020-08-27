package com.icboluo.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lp
 */
@Getter
@NoArgsConstructor
public class IcBoLuoException extends RuntimeException {
    private int status;

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
