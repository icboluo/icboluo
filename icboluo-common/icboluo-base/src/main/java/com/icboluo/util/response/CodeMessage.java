package com.icboluo.util.response;

/**
 * <p>如果有2个模块的异常枚举，拥有相同的i18n code，会按照就近原则优先使用
 *
 * @author icboluo
 * @since 2024-02-19 23:01
 */
public interface CodeMessage {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    String getCode();

    /**
     * 获取消息
     *
     * @return 消息
     */
    String getMsg();
}
