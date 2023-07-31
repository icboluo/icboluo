package com.icboluo.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 声明：导入使用的是client实例，导出得到的是view实例。2个是不同的实体承载，所以导入导出没有必要使用同一个注解，除非功能比较简单
 *
 * @author icboluo
 * @since 2023-06-25 19:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@I18n
public @interface ExcelExport {
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
}
