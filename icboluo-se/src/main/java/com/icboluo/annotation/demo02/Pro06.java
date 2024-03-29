package com.icboluo.annotation.demo02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author icboluo
 * @since 2020-08-29 21:25
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
 @interface Pro06 {

    String className();

    String methodName();
}
