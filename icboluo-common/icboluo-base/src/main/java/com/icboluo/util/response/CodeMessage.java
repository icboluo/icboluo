package com.icboluo.util.response;

/**
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
