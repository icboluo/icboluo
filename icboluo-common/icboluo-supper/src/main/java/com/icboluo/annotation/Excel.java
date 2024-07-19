package com.icboluo.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.TreeMap;

/**
 * <p>声明：导入使用的是client实例，导出得到的是view实例。2个是不同的实体承载，所以导入导出没有必要使用同一个注解，除非功能比较简单
 * <p>注解的注解（属于自定义元注解，以下简称元注解）；元注解需要写所有的属性值，好多不写是因为有默认值
 *
 * @author icboluo
 * @since 2023-06-25 19:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@I18n
public @interface Excel {
    /**
     * <p>AliasFor 起一个别名的作用，可以将a和b互通，常用的是value可以不写，然后对应attribute互通
     * <p>还有一个作用，@Service 注解继承 @Component 也可以使用这个指定
     * <p>注解的继承需要使用@Inherited, 其实不是注解的继承，是注解修饰的对象的继承，可以使用 @AliasFor使注解合并
     * <p>not meta-present 注解的合并需要把注解当做元注解，先修饰注解，然后使用@AliasFor互通，第二项不是必须的，第一项必须要，否则报错
     *
     * @return 英文名
     * @see org.springframework.stereotype.Service
     */
    @AliasFor(annotation = I18n.class) String en() default "";

    @AliasFor(annotation = I18n.class) String zh() default "";

    /**
     * <p>导出列序号 A B C D, 如果同时存在多个指定，首先使用 this.columnNumber 其次使用 ExcelProperty.index,
     * <p>最终全部归一到 this.columnIndex 上
     *
     * @return 导出列英文序号，不区分大小写
     */
    String columnNumber() default "";

    /**
     * 导出列索引 0 1 2 3
     *
     * @return 导出列索引
     */
    int columnIndex() default -1;

    /**
     * 传参名称，可以认为是字段的别名，如果字段名称不适合作为key，可以指定这个别名
     *
     * @return 映射参数名称
     */
    String paramName() default "";

    /**
     * 表头是否校验
     *
     * @return 默认校验
     */
    boolean check() default true;
}
