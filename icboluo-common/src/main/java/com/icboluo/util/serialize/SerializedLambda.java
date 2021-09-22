package com.icboluo.util.serialize;

import com.icboluo.function.SerialFunction;
import com.icboluo.util.IoHelper;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author icboluo
 */
@SuppressWarnings("unused")
public class SerializedLambda implements Serializable {

    @Serial
    private static final long serialVersionUID = 8025925345765570181L;

    /**
     * 实现方法名 （for example ：getName）
     */
    private String implMethodName;

    /**
     * 获取索性名的方法
     *
     * @param getFun 字段get方法
     * @param <T>    对象类型
     * @return 字段名
     */
    public static <T> String getColumnName(SerialFunction<T, ?> getFun) {
        SerializedLambda sl = resolveFunc(getFun);
        String implMethodName = sl.getImplMethodName();
        return resolveFieldName(implMethodName);
    }

    /**
     * 通过反序列化转换 lambda 表达式，该方法只能序列化 lambda 表达式，不能序列化接口实现或者正常非 lambda 写法的对象
     *
     * @param lambda lambda对象
     * @return 返回解析后的 SerializedLambda
     */
    public static SerializedLambda resolve(SerialFunction lambda) {
        if (!lambda.getClass().isSynthetic()) {
            throw new RuntimeException("该方法仅能传入 lambda 表达式产生的合成类");
        }
        byte[] serialize = IoHelper.serialize(lambda);
        try (ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(serialize)) {
            @Override
            protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                Class<?> clazz = super.resolveClass(objectStreamClass);
                return clazz == java.lang.invoke.SerializedLambda.class ? SerializedLambda.class : clazz;
            }
        }) {
            return (SerializedLambda) objIn.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException("This is impossible to happen", e);
        }
    }

    /**
     * SerializedLambda 反序列化缓存
     */
    private static final Map<Class<?>, WeakReference<SerializedLambda>> FUNC_CACHE = new ConcurrentHashMap<>();

    /**
     * <p>
     * 解析 lambda 表达式
     * </p>
     *
     * @param func 需要解析的 lambda 对象
     * @param <T>  类型，被调用的 Function 对象的目标类型
     * @return 返回解析后的结果
     */
    public static <T> SerializedLambda resolveFunc(SerialFunction<T, ?> func) {
        Class<? extends SerialFunction> clazz = func.getClass();
        return Optional.ofNullable(FUNC_CACHE.get(clazz))
                .map(WeakReference::get)
                .orElseGet(() -> {
                    SerializedLambda lambda = SerializedLambda.resolve(func);
                    FUNC_CACHE.put(clazz, new WeakReference<>(lambda));
                    return lambda;
                });
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
        if (!StringUtils.hasText(param)) {
            return "";
        }
        return param.substring(0, 1).toLowerCase() + param.substring(1);
    }

    /**
     * 获取实现者的方法名称
     *
     * @return 方法名称
     */
    public String getImplMethodName() {
        return implMethodName;
    }
}
