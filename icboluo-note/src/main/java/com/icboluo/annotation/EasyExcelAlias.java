package com.icboluo.annotation;

import java.lang.annotation.*;

/**
 * @author icboluo
 * @date 2020/12/2 21:34
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyExcelAlias {

    String[] alias();
}
