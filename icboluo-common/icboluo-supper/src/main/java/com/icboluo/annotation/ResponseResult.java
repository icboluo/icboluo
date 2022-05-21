package com.icboluo.annotation;

import java.lang.annotation.*;

/**
 * @author icboluo
 * @since 2021-23-13 23:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
