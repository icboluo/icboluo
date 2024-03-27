package com.icboluo.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

/**
 * <p>下拉框校验器
 * <p>ConstraintValidator 自定义注解校验器；校验器是基于java类型生效的，也就是说，必须先序列化成java类型，然后才可以使用校验器功能
 * <p>一个注解可以根据不同的类型校验器进行不同的校验
 *
 * @author icboluo
 * @since 2024-03-27 23:39
 */
public class DownBoxConstraintValidator implements ConstraintValidator<DownBox, Object> {
    private DownBox downBox;

    @Override
    public void initialize(DownBox constraintAnnotation) {
        downBox = constraintAnnotation;
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object val, ConstraintValidatorContext constraintValidatorContext) {
        Object[] en = downBox.en();
        boolean canChange = downBox.isCanChange();
        if (canChange) {
            return true;
        }
        return Arrays.asList(en).contains(val);
    }
}
