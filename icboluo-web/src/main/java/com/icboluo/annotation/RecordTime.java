package com.icboluo.annotation;

import java.lang.annotation.*;

/**
 * 记录方法执行的时间
 * @author icboluo
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordTime {
}
