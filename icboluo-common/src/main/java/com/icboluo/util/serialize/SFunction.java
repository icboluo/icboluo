package com.icboluo.util.serialize;

import java.io.Serializable;

/**
 * @author icboluo
 */
@FunctionalInterface
public interface SFunction<T, R> extends Serializable {
    
    R apply(T t);
}