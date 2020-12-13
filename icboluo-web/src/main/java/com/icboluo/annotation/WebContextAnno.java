package com.icboluo.annotation;

import com.icboluo.common.enumeration.WebContextEnum;

import java.lang.annotation.*;

/**
 * @author icboluo
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebContextAnno {
    WebContextEnum service();
}
