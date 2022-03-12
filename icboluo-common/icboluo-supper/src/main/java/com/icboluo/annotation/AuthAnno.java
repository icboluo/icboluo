package com.icboluo.annotation;

import java.lang.annotation.*;

/**
 * @author icboluo
 * @date 2022-02-22 21:16
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAnno {

    String role();
}
