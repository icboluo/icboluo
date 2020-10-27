package com.icboluo.annotation;

/**
 * 使用注解的时候，必须给属性赋值，除非有初始化默认值
 * 如果只有一个属性需要赋值，并且属性的名称是value，则属性的名称可以省略
 *
 * @author icboluo
 * @date 2020-08-29 18:17
 */
//@MyAnnotation02(age=18,name="abc",枚举=枚举项,注解=@MyAnnotation02(),数组={第一个，第二个})，如果数组中只有一个，大括号可以省略
@MyAnnotation02(name = "xiao ming")
 class Worker04 {
}
