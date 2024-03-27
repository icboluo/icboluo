package com.icboluo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * id转换为name的注解
 *
 * @author icboluo
 * @since 2024-03-28 0:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface IdToName {
    /**
     * 目标字段名
     *
     * @return 字段名称
     */
    String target() default "";

    /**
     * id转换为name使用的配置
     *
     * @return 标准化枚举
     */
    IdNameEnum data() default IdNameEnum.NULL;
}
