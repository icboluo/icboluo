package com.icboluo.annotation;

import com.icboluo.util.SpringUtil;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * id-name注解的解析器
 *
 * @author icboluo
 * @since 2024-03-28 0:37
 */
public class IdNameParser {

    private static final Executor IO_EXECUTOR = (Executor) SpringUtil.getBean("ioExecutor");
    private static final Map<Class<?>, Map<IdNameEnum, List<Field>>> CACHE = new ConcurrentHashMap<>();

    /**
     * 存储该枚举项拥有的code，用于批量查询
     */
    private final EnumMap<IdNameEnum, List<Object>> fieldCodeMap = new EnumMap<>(IdNameEnum.class);

    private final EnumMap<IdNameEnum, CompletableFuture<Map<?, String>>> fieldValueMap = new EnumMap<>(IdNameEnum.class);

    public static <T> T parse(T object) {
        return parse(object, new IdNameParser());
    }

    /**
     * 解析对象：支持List嵌套以及对象嵌套
     *
     * @param object 需要解析的对象
     * @param parser 已经进行预处理的解析器，可以不传，重载即可
     * @param <T>    数据类型
     * @return 参数对象
     */
    public static <T> T parse(T object, IdNameParser parser) {
        if (ObjectUtils.isEmpty(object)) {
            return object;
        }
        if (object instanceof Iterable<?> it) {
            Class<?> clazz = it.iterator().next().getClass();
            parser.toCache(clazz);
            parser.collect(it, clazz);
            for (Object obj : it) {
                parser.parseObject(obj);
            }
        } else {
            Class<?> clazz = object.getClass();
            parser.toCache(clazz);
            parser.collect(Collections.singletonList(object), clazz);
            parser.parseObject(object);
        }
        return object;
    }

    /**
     * 预处理（支持重复操作
     *
     * @param preHandlerEnum 将该枚举项内容提前加载
     * @return 对象本身
     */
    public IdNameParser preHandler(IdNameEnum... preHandlerEnum) {
        for (IdNameEnum item : preHandlerEnum) {
            CompletableFuture<Map<?, String>> cf = item.preHandler();
            fieldValueMap.put(item, cf);
        }
        return this;
    }


    private void toCache(Class<?> clazz) {
    }

    private void collect(Iterable<?> it, Class<?> clazz) {
    }

    private void parseObject(Object obj) {
    }
}
