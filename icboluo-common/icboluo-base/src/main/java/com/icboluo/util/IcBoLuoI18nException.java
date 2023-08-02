package com.icboluo.util;

import com.icboluo.enumerate.ReEnum;
import lombok.Getter;

/**
 * 国际化异常
 *
 * @author icboluo
 */
@Getter
@SuppressWarnings("unused")
public class IcBoLuoI18nException extends RuntimeException {
    /**
     * 状态码
     */
    private final int status;

    public IcBoLuoI18nException() {
        super();
        this.status = 500;
    }

    public IcBoLuoI18nException(String msg) {
        super(msg);
        this.status = 500;
    }

    public IcBoLuoI18nException(ReEnum em) {
        super(em.getMessage());
        this.status = em.getCode();
    }

    public IcBoLuoI18nException(ReEnum em, Throwable throwable) {
        super(em.getMessage(), throwable);
        this.status = em.getCode();
    }

    public IcBoLuoI18nException(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public IcBoLuoI18nException(String msg, Object[] args) {
        super(msg);
        this.status = 500;
    }

    public IcBoLuoI18nException(String msg, Exception ex) {
        super(msg);
        this.status = 500;
    }
}
