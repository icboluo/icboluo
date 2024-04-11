package com.icboluo.annotation;

import com.icboluo.object.Archives;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.SpringUtil;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

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
    @SneakyThrows
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

    /**
     * 将该类结构读取到缓存中
     *
     * @param clazz 类类型
     * @throws ClassNotFoundException 编码异常
     */
    private void toCache(Class<?> clazz) throws ClassNotFoundException {
        if (CACHE.containsKey(clazz)) {
            return;
        }
        List<Field> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(IdToName.class)) {
                continue;
            }
            list.add(field);
            if (field.getType() == List.class) {
                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                Type[] actualTypeArguments = genericType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    Class<?> subClazz = Class.forName(actualTypeArgument.getTypeName());
                    toCache(subClazz);
                }
            } else if (field.getType() != Integer.class && field.getType() != String.class) {
                toCache(field.getType());
            }
        }
        Map<IdNameEnum, List<Field>> i18nMap = list.stream()
                .collect(Collectors.groupingBy(field -> field.getAnnotation(IdToName.class).data()));
        CACHE.put(clazz, i18nMap);
    }

    /**
     * 收集：目的是将key收集起来，一次性查出key
     *
     * @param it
     * @param clazz
     */
    private void collect(Iterable<?> it, Class<?> clazz) {
    }

    /**
     * 解析：将name赋值给目标对象
     *
     * @param obj 目标对象
     */
    private void parseObject(Object obj) throws NoSuchFieldException, IllegalAccessException {
        if (obj == null) {
            return;
        }
        Class<?> cla = obj.getClass();
        if (!CACHE.containsKey(cla)) {
            return;
        }
        for (Map.Entry<IdNameEnum, List<Field>> entry : CACHE.get(cla).entrySet()) {
            IdNameEnum key = entry.getKey();
            for (Field field : entry.getValue()) {
                field.setAccessible(true);
                parseField(obj, cla, key, field);
            }
        }
    }

    private void collectField(List<Object> fieldValList, Object object, Field field) throws IllegalAccessException {
        Object fieldObj = field.get(object);
        if (fieldObj == null) {
            return;
        }
        if (field.getType() == Integer.class || field.getType() == String.class) {
            fieldValList.add(fieldObj);
        } else if (Archives.class.isAssignableFrom(field.getType())) {
            Archives<?, ?> arch = (Archives<?, ?>) fieldObj;
            fieldValList.add(arch.getId());
        } else if (field.getType() == List.class) {
            List<?> fieldObjList = (List<?>) fieldObj;
            if (!ObjectUtils.isEmpty(fieldObjList)) {
                collect(fieldObjList, fieldObjList.get(0).getClass());
            }
            // 自定义类型
        } else {
            collect(Collections.singletonList(fieldObj), fieldObj.getClass());
        }
    }

    private void parseField(Object obj, Class<?> cla, IdNameEnum key, Field field) throws NoSuchFieldException, IllegalAccessException {
        if (field.getType() == Integer.class || field.getType() == String.class) {
            IdToName idToName = field.getAnnotation(IdToName.class);
            String target = idToName.target();
            Field targetField = cla.getDeclaredField(target);
            targetField.setAccessible(true);
            String name = key.findNameByCode(fieldValueMap.get(key).join(), field.get(obj));
            if (targetField.get(obj) == null) {
                targetField.set(obj, name);
            }
        } else if (Archives.class.isAssignableFrom(field.getType())) {
            Archives<?, ?> arch = (Archives<?, ?>) field.get(obj);
            String name = key.findNameByCode(fieldValueMap.get(key).join(), arch.getId());
            if (arch.getId() != null && arch.getName() == null && name != null) {
                arch.setName(BeanUtil.cast(name));
            }
        } else if (field.getType() == List.class) {
            List<?> fieldObjList = (List<?>) field.get(obj);
            if (ObjectUtils.isEmpty(fieldObjList)) {
                return;
            }
            for (Object fieObj : fieldObjList) {
                parseObject(fieObj);
            }
            // 自定义类型
        } else {
            parseObject(field.get(obj));
        }
    }
}
