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
public class I18nException extends RuntimeException {
    /**
     * 状态码
     */
    private final String status;

    /**
     * 该字段仅做国际化时使用，若无参数，完全没必要写
     */
    private transient Object[] args;

    public I18nException() {
        super();
        this.status = "500";
    }

    public I18nException(String msg) {
        super(msg);
        this.status = "500";
    }

    public I18nException(ReEnum em) {
        super(em.getMsg());
        this.status = em.getCode();
    }

    public I18nException(ReEnum em, Throwable throwable) {
        super(em.getMsg(), throwable);
        this.status = em.getCode();
    }

    public I18nException(String status, String msg) {
        super(msg);
        this.status = status;
    }

    public I18nException(String msg, Object[] args) {
        super(msg);
        this.status = "500";
    }

    public I18nException(String msg, Exception ex) {
        super(msg);
        this.status = "500";
    }
}
