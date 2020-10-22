package com.icboluo.annotation;

import java.lang.annotation.*;

/**
 * @author icboluo
 * @date 2020/10/22 19:15
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBodyParam {
}
