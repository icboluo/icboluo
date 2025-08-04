package com.icboluo.util.serialize;

import com.icboluo.function.SerialFunction;
import com.icboluo.util.IcBoLuoException;
import lombok.SneakyThrows;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author icboluo
 */
@SuppressWarnings("unused")
public class LambdaUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 8025925345765570181L;

    /**
     * 实现方法名 （for example ：getName）
     */
    private String implMethodName;

    /**
     * 获取索性名的方法
     *
     * @param function 字段get方法
     * @param <T>      对象类型
     * @return 字段名
     */
    public static <T, R> String getColumnName(SerialFunction<T, R> function) {
        // 每个function是不一样的
        // 第1步 获取SerializedLambda
        SerializedLambda lambdaUtil = findSl(function);
        // 第2步 implMethodName 即为Field对应的Getter方法名
        String implMethodName = lambdaUtil.getImplMethodName();
        return resolveFieldName(implMethodName);
    }

    @SneakyThrows
    public static <T, R> Field getField(SerialFunction<T, R> function) {
        // 每个function是不一样的
        // 第1步 获取SerializedLambda
        SerializedLambda lambdaUtil = findSl(function);
        // 第2步 implMethodName 即为Field对应的Getter方法名
        String implMethodName = lambdaUtil.getImplMethodName();
        String fieldName = resolveFieldName(implMethodName);
        String implCla = lambdaUtil.getImplClass().replace("/", ".");
        Class<?> cla = Class.forName(implCla, false, ClassUtils.getDefaultClassLoader());
        return ReflectionUtils.findField(cla, fieldName);
    }

    public static <T, R> SerializedLambda findSl(SerialFunction<T, R> function) {
        Method method;
        try {
            method = function.getClass().getDeclaredMethod("writeReplace");
        } catch (NoSuchMethodException e) {
            throw new IcBoLuoException("没有方法");
        }
        method.setAccessible(Boolean.TRUE);
        try {
            return (SerializedLambda) method.invoke(function);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IcBoLuoException("方法调用异常");
        }
    }

    /**
     * <p>
     * 解析 getMethodName -> propertyName
     * </p>
     *
     * @param getMethodName 需要解析的
     * @return 返回解析后的字段名称
     */
    public static String resolveFieldName(String getMethodName) {
        if (getMethodName.startsWith("get")) {
            getMethodName = getMethodName.substring(3);
        } else if (getMethodName.startsWith("is")) {
            getMethodName = getMethodName.substring(2);
        }
        // 小写第一个字母
        return firstToLowerCase(getMethodName);
    }

    /**
     * <p>
     * 首字母转换小写
     * </p>
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String firstToLowerCase(String param) {
        if (param == null || param.isEmpty()) {
            return "";
        }
        return param.substring(0, 1).toLowerCase() + param.substring(1);
    }
}
