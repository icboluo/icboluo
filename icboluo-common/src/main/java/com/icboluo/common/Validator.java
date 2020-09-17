package com.icboluo.common;


/**
 * @author icboluo
 */
public interface Validator {

    /**
     * 校验数据，如果失败抛出异常
     *
     * @param candidate 要校验的数据
     */
    default void validate(Object candidate) {

    }

}
