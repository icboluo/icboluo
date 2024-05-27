package com.icboluo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.icboluo.common.Desc.Recommend.MORE_THAN;

/**
 * @author icboluo
 * @since 2024-05-15 20:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Desc {
    String desc() default "";

    String value() default "123";

    String tip() default "";

    Recommend recommend() default MORE_THAN;

    enum Recommend {
        MORE_THAN,
        LESS_THAN,
        ;
    }

}
