package com.icboluo.util;

import com.icboluo.enumerate.ReEnum;
import lombok.Getter;

/**
 * 国际化异常
 *
 * @see com.icboluo.util.response.R
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
    private final transient Object[] args;

    public I18nException() {
        super();
        this.status = "500";
        this.args = new Object[0];
    }

    public I18nException(String msg) {
        super(msg);
        this.status = "500";
        this.args = new Object[0];
    }

    public I18nException(ReEnum em) {
        super(em.getMsg());
        this.status = em.getCode();
        this.args = new Object[0];
    }

    public I18nException(ReEnum em, Throwable throwable) {
        super(em.getMsg(), throwable);
        this.status = em.getCode();
        this.args = new Object[0];
    }

    public I18nException(String msg, String... argArr) {
        super(msg);
        this.status = "500";
        this.args = new Object[argArr.length];
        System.arraycopy(argArr, 0, args, 0, args.length);
    }

    public I18nException(String msg, Object[] args) {
        super(msg);
        this.status = "500";
        this.args = new Object[0];
    }

    public I18nException(String msg, Exception ex) {
        super(msg);
        this.status = "500";
        this.args = new Object[0];
    }
}
