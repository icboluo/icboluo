package com.icboluo.function;

import java.io.Serializable;

/**
 * @author icboluo
 * @since 2021-29-12 22:29
 */
@FunctionalInterface
public interface SerialFunction<T, R> extends Serializable {

    R apply(T t);
}
