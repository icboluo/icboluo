package com.icboluo.util.response;

import com.alibaba.fastjson2.JSON;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.I18nException;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.Locale;

/**
 * 这个工具类的目的是将值写到Response这个类的对象中；
 * <p>为了统一前端的取参格式，如果有多个对象，本类约定：
 * <p>只要返回值是消息、状态码，必须放在code/message里面，不准放在data里面；
 * 也就是说，本类所写的返回值中，绝对不会包含传入参数以外的数据（枚举项、异常除外）；
 *
 * @author icboluo
 */
@SuppressWarnings("unused,rawtypes")
@Data
public class R implements Serializable {

    public static Response correct() {
        return new SingleResponse<>(ReEnum.OPERATION_SUCCESSFUL);
    }

    public static Response correct(CodeMessage codeMessage) {
        return new SingleResponse<>(codeMessage);
    }

    /**
     * 本方法只提供code和message的构建，不提供data数据的构建
     *
     * @param message message
     * @return 响应类
     */
    public static Response correct(String message) {
        return new SingleResponse<>(ReEnum.OPERATION_SUCCESSFUL.getCode(), message);
    }

    public static Response correct(String data, CodeMessage codeMessage) {
        return new SingleResponse<>(data, codeMessage);
    }

    public static <T> Response correct(T t) {
        return new SingleResponse<>(t, ReEnum.OPERATION_SUCCESSFUL);
    }

    public static Response error() {
        return new SingleResponse<>(ReEnum.SYSTEM_ERROR);
    }

    public static Response error(String message, MessageSource messageSource) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(message, null, locale);
        return new SingleResponse<>(ReEnum.SYSTEM_ERROR.getCode(), msg);
    }

    public static Response error(I18nException i18nException, MessageSource messageSource) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(i18nException.getMessage(), i18nException.getArgs(), locale);
        return new SingleResponse<>(ReEnum.SYSTEM_ERROR.getCode(), msg);
    }

    public static Response error(String errMsg) {
        return new SingleResponse<>(ReEnum.SYSTEM_ERROR.getCode(), errMsg);
    }

    public static Response error(String code, String errMsg) {
        return new SingleResponse<>(code, errMsg);
    }

    public static Response error(String code, Exception e) {
        return new SingleResponse<>(code, e.getMessage());
    }

    public static Response error(CodeMessage codeMessage) {
        return new SingleResponse<>(codeMessage);
    }

    public static Response error(CodeMessage codeMessage, MessageSource messageSource) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(codeMessage.getMsg(), null, locale);
        return new SingleResponse<>(codeMessage.getCode(), msg);
    }

    public static Response error(Exception e) {
        return new SingleResponse<>(ReEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    public static Response generate(String body) {
        return JSON.parseObject(JSON.toJSONString(body), Response.class);
    }
}
