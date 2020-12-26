package com.icboluo.annotation;

/**
 * Java引用数据类型的四大类：class类、interface接口、enum枚举、annotation注解
 * 注解的功能：
 * 1.进行编译检查:override
 * 2.生成doc文档，就是api文档：
 * 命令：javadoc AnnotationDemo01.java,
 * 打开index.html即可,生成的是文档注释，要注意编码
 * 3.对代码里标识的注解进行代码分析
 *
 * override：验证重写
 * deprecated：过时
 * SuppressWarnings：抑制警告
 *
 * @author icboluo
 * @date 2020-08-29 18:08
 * @since 1.5
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
