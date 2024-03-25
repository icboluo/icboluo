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
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 如果不加 @Valid注解，可以使用本类开启手动校验，可以实现校验部分参数
 * 子类需要校验的时候需要在子类字段上加上@Valid注解，否则校验不到子类中
 * NotEmpty注解代表不能为null&&'',
 * NotBlack注解代表不能为''可以为null,没什么用
 * Valid和Validate注解在基础功能上没有区别，在分组（valid没有分组），嵌套上有区别
 *
 * @author icboluo
 * Valid注解标记List类型的失效，需要在类上增加@Validated注解
 * @see jakarta.validation.Valid
 * @see org.springframework.validation.annotation.Validated
 * <p>
 * validate 如果后面不加BindingResult则抛异常，如果加上，则会将错误的结果收集到bs中
 * @since 2022-01-12 11:59
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtil {

    /**
     * Environment是可以直接获取到配置文件中的内容的 *.properties
     */
    private static final String I18N_MESSAGES = SpringUtil.getBean(Environment.class).getProperty("i18n/messages");

    private static final Validator VALIDATOR;

    static {
        try (ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new RequestLocaleAwareMessageInterpolator(
                        // 提供 AggregateResourceBundleLocator 使得除了用框架提供的Validation ConstrainViolation Message 外
                        // 还可以用自己指定的或覆盖框架提供的
                        new AggregateResourceBundleLocator(Collections.singletonList(I18N_MESSAGES))
                ))
                .buildValidatorFactory()
        ) {
            VALIDATOR = validatorFactory.getValidator();
        }
    }

    /**
     * 自定义 ResourceBundleMessageInterpolator 的若干方法，使得可根据request指定的语言返回对应语种的Validation ConstrainViolation Message
     */
    public static class RequestLocaleAwareMessageInterpolator extends ResourceBundleMessageInterpolator {
        public RequestLocaleAwareMessageInterpolator(ResourceBundleLocator userResourceBundleLocator) {
            super(userResourceBundleLocator);
        }

        @Override
        public String interpolate(String message, Context context) {
            return super.interpolate(message, context, LocaleContextHolder.getLocale());
        }
    }

    /**
     * 校验属性
     *
     * @param obj          需要校验的对象
     * @param propertyName 需要校验的属性名
     * @param <T>          对象的类型
     * @return 校验结果
     */
    public static <T> Set<ConstraintViolation<T>> validateProperty(T obj, String propertyName) {
        return VALIDATOR.validateProperty(obj, propertyName, Default.class);
    }

    /**
     * 批量校验属性
     *
     * @param obj 需要校验的对象
     * @param get 需要校验的属性的get函数
     * @param <T> 对象的类型
     * @return 批量校验结果
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

    public static <T> List<String> validateProperty(T obj) {
        List<String> res = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Set<ConstraintViolation<T>> constraintViolations = validateProperty(obj, name);
            if (!CollectionUtils.isEmpty(constraintViolations)) {
                res.add(constraintViolations.iterator().next().getMessage());
            }
        }
        return res;
    }

    /**
     * 将批量校验结果收集起来
     *
     * @param cvSet 批量校验结果
     * @param <T>   被校验的对象类型
     * @return 多个结果的列表
     */
    public static <T> List<String> buildMsg(Set<ConstraintViolation<T>> cvSet) {
        List<String> res = new ArrayList<>();
        for (ConstraintViolation<T> cv : cvSet) {
            res.add(cv.getPropertyPath() + " " + cv.getMessage());
        }
        return res;
    }
}
