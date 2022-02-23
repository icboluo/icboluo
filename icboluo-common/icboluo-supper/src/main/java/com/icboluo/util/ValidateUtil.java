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
 * @author icboluo
 * @date 2022-01-12 11:59
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
    public static <T> void validateProperty(T obj, SerialFunction<T, String>... get) {
        for (SerialFunction<T, String> serialFunction : get) {
            String columnName = SerializedLambda.getColumnName(serialFunction);
            Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validateProperty(obj, columnName, Default.class);
        }
    }
}
