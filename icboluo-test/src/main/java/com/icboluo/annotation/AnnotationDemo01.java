package com.icboluo.annotation;

/**
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
 * @date 2020-08-29 17:42
 * @since 1.5
 */
 class AnnotationDemo01 {
    /**
     * 求和
     *
     * @param a 第一个参数
     * @param b 第二个参数
     * @return 和
     */
    @Deprecated
    public int add(int a, int b) {
        return a + b;
    }
}
