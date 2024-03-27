package com.icboluo.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * <p>下拉框
 * <p>NotNull 注解是有一个 NotNullValidator 的，所以不需要 validatedBy 属性，如果没有的话是需要这个属性的
 *
 * @author icboluo
 * @see jakarta.validation.constraints.NotNull
 * @see jakarta.validation.constraints.NotEmpty
 * @since 2024-03-27 23:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = DownBoxConstraintValidator.class)
public @interface DownBox {

    /**
     * 默认提示消息
     *
     * @return 提示消息
     */
    String messages() default "{data.is.incorrect}";

    /**
     * 这2个属性必须加上
     *
     * @return 分组
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 现在不支持中文的场景
     *
     * @return 不支持中文
     */
    String[] zh() default {};

    String[] en() default {};

    /**
     * 是否允许被修改
     *
     * @return 默认为false
     */
    boolean isCanChange() default false;
}
