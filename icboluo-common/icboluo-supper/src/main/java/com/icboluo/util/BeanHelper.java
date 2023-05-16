package com.icboluo.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.util.TypeUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.icboluo.common.PageQuery;
import com.icboluo.enumerate.ReEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * @author icboluo
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("unused")
public class BeanHelper {

    private static final String CONVERT_ERR_MESSAGE = "【数据转换】数据转换出错，目标对象{}构造函数异常";

    /**
     * 属性复制，也可以直接使用Spring的工具类
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 将一个对象中的属性copy到另一个对象中，需要默认无参的构造方法
     *
     * @param source 源对象
     * @param target 目标类
     * @param <T>    类的类型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, @NonNull Class<T> target) {
        T t;
        try {
            t = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, target.getName(), e);
            throw new IcBoLuoException(ReEnum.DATA_TRANSFER_ERROR);
        }
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static <T> T copyProperties(Object source, @NonNull Supplier<T> supplier) {
        T t = supplier.get();
        BeanUtils.copyProperties(source, t);
        return t;
    }

    /**
     * 将list集合中的属性copy到目标对象的集合中
     *
     * @param sourceList 原集合
     * @param target     目标类
     * @param <T>        目标类的类型
     * @return 目标集合
     */
    public static <T> List<T> copyWithColl(List<?> sourceList, @NonNull Class<T> target) {
        try {
            return sourceList.stream()
                    .map(s -> copyProperties(s, target))
                    .toList();
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, target.getName(), e);
            throw new IcBoLuoException(ReEnum.DATA_TRANSFER_ERROR);
        }
    }

    public static <T> List<T> copyWithColl(List<?> sourceList, @NonNull Supplier<T> supplier) {
        return sourceList.stream()
                .map(s -> copyProperties(s, supplier))
                .toList();
    }

    public static <S, T> List<T> copyWithColl(List<S> sourceList, @NonNull Supplier<T> supplier, BiConsumer<S, T> callBack) {
        return sourceList.stream()
                .map(s -> {
                    T t = copyProperties(s, supplier);
                    if (callBack != null) {
                        callBack.accept(s, t);
                    }
                    return t;
                }).toList();
    }

    /**
     * 将set集合中的属性copy到目标对象的集合中
     *
     * @param sourceList 原集合
     * @param target     目标类
     * @param <T>        目标类的类型
     * @return 目标集合
     */
    public static <T> Set<T> copyWithColl(Set<?> sourceList, @NonNull Class<T> target) {
        try {
            return sourceList.stream()
                    .map(s -> copyProperties(s, target))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, target.getName(), e);
            throw new IcBoLuoException(ReEnum.DATA_TRANSFER_ERROR);
        }
    }

    /**
     * 获取list的第一个元素
     *
     * @param coll list 容器
     * @param <T>  容器元素类型
     * @return 如果容器是空，或者第一个元素是空，返回 null
     */
    public static <T> T listGetFirst(Collection<T> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return null;
        }
        Optional<T> first = coll.stream()
                .findFirst();
        return first.orElse(null);
    }

    public static <T> T singletonGetFirst(Collection<T> coll) {
        if (CollectionUtils.isEmpty(coll) || coll.size() != 1) {
            return null;
        }
        Optional<T> first = coll.stream()
                .findFirst();
        return first.orElse(null);
    }

    public static boolean allIsNull(Object... arr) {
        return Arrays.stream(arr)
                .map(Objects::isNull)
                .reduce(true, (a, b) -> a && b);
    }

    public static boolean allNotNull(Object... arr) {
        return Arrays.stream(arr)
                .map(Objects::nonNull)
                .reduce(true, (a, b) -> a && b);
    }

    public static boolean anyEmpty(Object... arr) {
        return Arrays.stream(arr)
                .map(Objects::nonNull)
                .reduce(false, (a, b) -> a || b);
    }

    public static boolean haveNull(Object... arr) {
        return Arrays.stream(arr)
                .anyMatch(Objects::isNull);
    }

    public static <T> boolean allIsNullOrEquals(T a, T b) {
        if (a == null && b == null) {
            return true;
        }
        return a != null && a.equals(b);
    }

    public static <S, T, F> void notEmptyThenSet(S source, T target, Function<S, F> get, BiConsumer<T, F> set) {
        if (!ObjectUtils.isEmpty(get.apply(source))) {
            set.accept(target, get.apply(source));
        }
    }

    public static <T> void notNullToAddAll(List<T> source, List<T> target) {
        if (CollectionUtils.isEmpty(source)) {
            target.addAll(source);
        }
    }

    public static <SK, TK, V> Map<TK, V> mapKeyConvert(Map<SK, V> map, Function<SK, TK> keyConvert) {
        return mapConvert(map, keyConvert, item -> item);
    }

    public static <K, SV, TV> Map<K, TV> mapValConvert(Map<K, SV> map, Function<SV, TV> valConvert) {
        return mapConvert(map, item -> item, valConvert);
    }

    public static <SK, TK, SV, TV> Map<TK, TV> mapConvert(@NonNull Map<SK, SV> map, Function<SK, TK> keyConvert, Function<SV, TV> valConvert) {
        return map.entrySet().stream()
                .filter(entry -> allNotNull(entry.getKey(), keyConvert.apply(entry.getKey())))
                .filter(entry -> allNotNull(entry.getKey(), valConvert.apply(entry.getValue())))
                .collect(Collectors.toMap(
                        entry -> keyConvert.apply(entry.getKey()),
                        entry -> valConvert.apply(entry.getValue())));
    }

    public static <S, T> PageInfo<T> pageInfoConvert(PageInfo<S> source, List<T> target) {
        Page<T> page = new Page<>(source.getPageNum(), source.getPageSize());
        page.setTotal(source.getTotal());
        PageInfo<T> pageInfo = PageInfo.of(page);
        pageInfo.setList(target);
        return pageInfo;
    }

    public static <S, T> PageInfo<T> pageInfoConvert(List<S> source, List<T> target) {
        return pageInfoConvert(PageInfo.of(source), target);
    }

    /**
     * 假分页
     *
     * @param list    原集合
     * @param convert 转换函数
     * @param query   分页条件
     * @param <S>     source
     * @param <T>     target
     * @param <Q>     query
     * @return page info
     */
    public static <S, T, Q extends PageQuery> PageInfo<T> fakePage(List<S> list, Function<List<S>, List<T>> convert, Q query) {
        int start = (query.getPageNum() - 1) * query.getPageSize();
        int end = Math.min(list.size(), query.getPageSize() * query.getPageNum());
        List<S> subList;
        if (start > list.size()) {
            int mo = list.size() % query.getPageSize();
            subList = list.subList(list.size() - mo, list.size());
        } else {
            subList = list.subList(start, Math.min(end, list.size()));
        }
        List<T> apply = convert.apply(subList);
        PageInfo<T> pi = PageInfo.of(apply);
        pi.setTotal(list.size());
        pi.setPageNum(query.getPageNum());
        pi.setPageSize(query.getPageSize());
        return pi;
    }

    public static <S, Q extends PageQuery> PageInfo<S> fakePage(List<S> list, Q query) {
        return fakePage(list, a -> a, query);
    }

    public static <S> PageInfo<S> fakePage(Collection<S> coll) {
        if (coll instanceof List<S> list) {
            return PageInfo.of(list);
        }
        return PageInfo.of(new ArrayList<>(coll));
    }

    public static JSONObject merge(Object obj1, Object... arr) {
        ValueFilter valueFilter = valueFilter();
        JSONObject json1 = JSON.parseObject(JSON.toJSONString(obj1, valueFilter, SerializerFeature.WriteMapNullValue));
        for (Object obj2 : arr) {
            if (obj2 == null) {
                continue;
            }
            JSONObject json2 = JSON.parseObject(JSON.toJSONString(obj2, valueFilter, SerializerFeature.WriteMapNullValue));
            for (Map.Entry<String, Object> entry : json2.entrySet()) {
                if (json1.containsKey(entry.getKey())) {
                    json1.computeIfAbsent(entry.getKey(), k -> entry.getValue());
                } else {
                    json1.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return json1;
    }

    /**
     * TypeUtils 拥有数据类型转换函数
     *
     * @param val 对象值
     * @return int值
     * @deprecated 不应该调用此方法，直接调用type转换工具类即可
     */
    @Deprecated(since = "all")
    public static Integer toInt(Object val) {
        return TypeUtils.castToInt(val);
    }

    private static ValueFilter valueFilter() {
        return (obj, name, val) -> {
            if (val == null) {
                val = "";
            }
            if (val instanceof LocalDate localDateVal) {
                val = localDateVal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            if (val instanceof LocalDateTime localDateTimeVal) {
                val = localDateTimeVal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
            return val;
        };
    }
}
