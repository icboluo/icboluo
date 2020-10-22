package com.icboluo.annotation;

import java.lang.annotation.*;

/**
 * @author icboluo
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserCode {
}
