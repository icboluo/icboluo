package com.icboluo.util;

import com.icboluo.function.SerialFunction;
import com.icboluo.util.serialize.SerializedLambda;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 如果不加 @Valid注解，可以使用本类开启手动校验，可以实现校验部分参数
 * 子类需要校验的时候需要在子类字段上加上@Valid注解，否则校验不到子类中
 * NotEmpty注解代表不能为null&&'',
 * NotBlack注解代表不能为''可以为null,没什么用
 * NotEmpty.contain NotBlack+NotNull
 * Valid和Validate注解在基础功能上没有区别，在分组（valid没有分组），嵌套上游区别
 *
 * @author icboluo
 * @Valid 注解标记List类型的失效，需要在类上增加@Validated注解
 * @see jakarta.validation.Valid
 * @see org.springframework.validation.annotation.Validated
 * <p>
 * validate 如果后面不加BindingResult则抛异常，如果加上，则会将错误的结果收集到bs中
 * @since 2022-01-12 11:59
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtil {

    private static final Validator VALIDATOR;

    static {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            VALIDATOR = validatorFactory.getValidator();
        }
    }

    /**
     * @param obj
     * @param propertyName
     * @param <T>
     */
    public static <T> Set<ConstraintViolation<T>> validateProperty(T obj, String propertyName) {
        return VALIDATOR.validateProperty(obj, propertyName, Default.class);
    }

    /**
     * @param obj
     * @param get
     * @param <T>
     */
    public static <T> List<Set<ConstraintViolation<T>>> validateProperty(T obj, SerialFunction<T, Object>... get) {
        List<Set<ConstraintViolation<T>>> res = new ArrayList<>();
        for (SerialFunction<T, Object> serialFunction : get) {
            String columnName = SerializedLambda.getColumnName(serialFunction);
            Set<ConstraintViolation<T>> cvSet = VALIDATOR.validateProperty(obj, columnName, Default.class);
            res.add(cvSet);
        }
        return res;
    }

    public static <T> List<String> buildMsg(Set<ConstraintViolation<T>> cvSet) {
        List<String> res = new ArrayList<>();
        for (ConstraintViolation<T> cv : cvSet) {
            res.add(cv.getPropertyPath() + " " + cv.getMessage());
        }
        return res;
    }
}
