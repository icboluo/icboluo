package com.icboluo.util;

import com.icboluo.function.SerialFunction;
import com.icboluo.util.serialize.LambdaUtil;
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
import java.util.*;

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
    private static final List<String> I18N_BUNDLE_ADDR = SpringUtil.getBean(Environment.class)
            .getProperty("spring.messages.basename", List.class, Collections.singletonList("messages"));

    /**
     * <p>好像不加supper也可以调用到supper的内容，是直接取yml的，不是取代码中的
     * <p>valid 和 MessageSource 的工作原理是不同的，@Valid是使用valid.valid函数，用hibernate来校验的
     * <p>MessageSource 是根据配置的国际化信息来校验的
     */
    private static final Validator VALIDATOR;

    static {
        try (ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new RequestLocaleAwareMessageInterpolator(
                        // 提供 AggregateResourceBundleLocator 使得除了用框架提供的Validation ConstrainViolation Message 外
                        // 还可以用自己指定的或覆盖框架提供的
                        new AggregateResourceBundleLocator(I18N_BUNDLE_ADDR)
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
            String columnName = LambdaUtil.getColumnName(serialFunction);
            Set<ConstraintViolation<T>> cvSet = VALIDATOR.validateProperty(obj, columnName, Default.class);
            res.add(cvSet);
        }
        return res;
    }

    /**
     * 批量校验属性，如果校验失败，抛异常
     *
     * @param obj        需要校验的对象
     * @param fieldNames 需要校验的属性名
     * @param <T>        对象的类型
     */
    public static <T> void validatePropertyOrThrow(T obj, String... fieldNames) {
        for (String fieldName : fieldNames) {
            Set<ConstraintViolation<T>> cvSet = VALIDATOR.validateProperty(obj, fieldName, Default.class);
            Iterator<ConstraintViolation<T>> it = cvSet.iterator();
            if (it.hasNext()) {
                ConstraintViolation<T> cv = it.next();
                throw new I18nException(cv.getPropertyPath() + " " + cv.getMessage());
            }
        }
    }

    public static <T> List<Set<ConstraintViolation<T>>> validateFields(T obj, Field[] fields) {
        List<Set<ConstraintViolation<T>>> res = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Set<ConstraintViolation<T>> cvSet = VALIDATOR.validateProperty(obj, field.getName(), Default.class);
            res.add(cvSet);
        }
        return res;
    }

    public static <T> Map<Field, String> validateFieldsToMap(T obj, Field[] fields) {
        Map<Field, String> map = new LinkedHashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Set<ConstraintViolation<T>> cvSet = VALIDATOR.validateProperty(obj, field.getName(), Default.class);
            if (!cvSet.isEmpty()) {
                map.put(field, cvSet.iterator().next().getMessage());
            }
        }
        return map;
    }

    public static <T> List<String> validateProperty(T obj) {
        List<String> res = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Set<ConstraintViolation<T>> cvSet = validateProperty(obj, name);
            if (!CollectionUtils.isEmpty(cvSet)) {
                res.add(cvSet.iterator().next().getMessage());
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
