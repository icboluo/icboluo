package com.icboluo.annotation;

import com.icboluo.enumeration.WebContextEnum;

import java.lang.annotation.*;

/**
 * @author icboluo
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebContextAnno {
    WebContextEnum service();
}
