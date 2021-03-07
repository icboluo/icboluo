package com.icboluo.reflect.反射设置注解属性;

import java.lang.annotation.*;

/**
 * @author icboluo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Stu {

    int index() default -1;
}
