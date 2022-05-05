package com.icboluo.util;

import com.icboluo.function.SerialFunction;
import com.icboluo.util.serialize.SerializedLambda;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * 如果不加 @Valid注解，可以使用本类开启手动校验，可以实现校验部分参数
 * 子类需要校验的时候需要在子类字段上加上@Valid注解，否则校验不到子类中
 * NotEmpty注解代表不能为null&&'',
 * NotBlack注解代表不能为''可以为null,没什么用
 * NotEmpty+NotNull可以实现判断数组中的每一个元素均不为null
 * NotEmpty.contain NotBlack+NotNull
 * Valid和Validate注解在基础功能上没有区别，在分组（valid没有分组），嵌套上游区别
 *
 * @author icboluo
 * @see javax.validation.Valid
 * @see org.springframework.validation.annotation.Validated
 * <p>
 * validate 如果后面不加BindingResult则抛异常，如果家何时能感，则会将错误的结果收集到bs中
 * @since 2022-01-12 11:59
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtil {

    private static final Validator VALIDATOR;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = validatorFactory.getValidator();
    }

    /**
     * TODO 返回值处理 校验属性
     *
     * @param obj
     * @param propertyName
     * @param <T>
     */
    public static <T> Set<ConstraintViolation<T>> validateProperty(T obj, String propertyName) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validateProperty(obj, propertyName, Default.class);
        return constraintViolations;
    }

    /**
     * TODO 返回值处理 校验属性
     *
     * @param obj
     * @param get
     * @param <T>
     */
    public static <T> void validateProperty(T obj, SerialFunction<T, Object>... get) {
        for (SerialFunction<T, Object> serialFunction : get) {
            String columnName = SerializedLambda.getColumnName(serialFunction);
            Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validateProperty(obj, columnName, Default.class);
        }
    }
}
