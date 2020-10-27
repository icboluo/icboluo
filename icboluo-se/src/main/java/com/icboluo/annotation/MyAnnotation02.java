package com.icboluo.annotation;

/**
 * @author icboluo
 * @date 2020-08-29 18:08
 */

 @interface MyAnnotation02 {
    /**
     * 接口中的抽象方法，在注解中称作属性
     *
     * @return 属性的返回值类型要求：1.基本数据类型，2.String，3.注解，4.枚举，5.以上数据类型的一维数组
     */
    String name();

    int age() default 18;


}
