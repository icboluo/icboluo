package com.icboluo.annotation;

import java.lang.annotation.*;

/**
 * target：描述注解能够作用的位置
 * retention：描述注解被保留的阶段
 * documented：描述注解是否被抽取到api文档中，就是doc文档
 * inherited：描述注解是否被子类继承
 *
 * @author icboluo
 * @date 2020-08-29 20:58
 */
/*
type：作用于类上
    method：方法上
    field：作用于方法上
 */
@Target(value = {ElementType.ANNOTATION_TYPE})
/*
当前被描述的注解，会保留到class字节码文件中，并被jvm读取到
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//使用该注解的子类也相当于有该注解
@interface MyAnnotation05 {
}
