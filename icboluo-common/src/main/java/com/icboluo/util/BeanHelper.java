package com.icboluo.util;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.icboluo.common.PageQuery;
import com.icboluo.enumerate.ExceptionEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

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
public class BeanHelper {

    private static final String CONVERT_ERR_MESSAGE = "【数据转换】数据转换出错，目标对象{}构造函数异常";

    /**
     * 将一个对象中的属性copy到另一个对象中
     *
     * @param source 源对象
     * @param target 目标类
     * @param <T>    类的类型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, @NonNull Class<T> target) {
        try {
            T t = target.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, target.getName(), e);
            throw new IcBoLuoException(ExceptionEnum.DATA_TRANSFER_ERROR);
        }
    }

    public static <T> T copyProperties(Object source, @NonNull Supplier<T> supplier) {
        try {
            T t = supplier.get();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, supplier.getClass().getName(), e);
            throw new IcBoLuoException(ExceptionEnum.DATA_TRANSFER_ERROR);
        }
    }

    /**
     * 将list集合中的属性copy到目标对象的集合中
     *
     * @param sourceList 原集合
     * @param target     目标类
     * @param <T>        目标类的类型
     * @return 目标集合
     */
    public static <T> List<T> copyWithCollection(List<?> sourceList, @NonNull Class<T> target) {
        try {
            return sourceList.stream()
                    .map(s -> copyProperties(s, target))
                    .toList();
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, target.getName(), e);
            throw new IcBoLuoException(ExceptionEnum.DATA_TRANSFER_ERROR);
        }
    }

    public static <T> List<T> copyWithCollection(List<?> sourceList, @NonNull Supplier<T> supplier) {
        try {
            return sourceList.stream()
                    .map(s -> copyProperties(s, supplier))
                    .toList();
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, supplier.getClass().getName(), e);
            throw new IcBoLuoException(ExceptionEnum.DATA_TRANSFER_ERROR);
        }
    }

    /**
     * 将set集合中的属性copy到目标对象的集合中
     *
     * @param sourceList 原集合
     * @param target     目标类
     * @param <T>        目标类的类型
     * @return 目标集合
     */
    public static <T> Set<T> copyWithCollection(Set<?> sourceList, @NonNull Class<T> target) {
        try {
            return sourceList.stream()
                    .map(s -> copyProperties(s, target))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            log.error(CONVERT_ERR_MESSAGE, target.getName(), e);
            throw new IcBoLuoException(ExceptionEnum.DATA_TRANSFER_ERROR);
        }
    }

    /**
     * 获取list的第一个元素
     *
     * @param list list 容器
     * @param <T>  容器元素类型
     * @return 如果容器是空，或者第一个元素是空，返回 null
     */
    public static <T> T listGetFirst(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        Optional<T> first = list.stream()
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

    public static Boolean byteToBoolean(Byte byt) {
        if (byt == null) {
            return null;
        }
        if (byt == 1) {
            return true;
        }
        if (byt == 0) {
            return false;
        }
        return null;
    }

    public static <SK, TK, V> Map<TK, V> mapKeyConvert(Map<SK, V> map, Function<SK, TK> keyConvert) {
        return mapConvert(map, keyConvert, item -> item);
    }

    public static <K, SV, TV> Map<K, TV> mapValConvert(Map<K, SV> map, Function<SV, TV> valConvert) {
        return mapConvert(map, item -> item, valConvert);
    }

    public static <SK, TK, SV, TV> Map<TK, TV> mapConvert(Map<SK, SV> map, Function<SK, TK> keyConvert, Function<SV, TV> valConvert) {
        return map.entrySet().stream()
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
        List<S> subList = list.subList(start, end);
        List<T> apply = convert.apply(subList);
        PageInfo<T> pi = PageInfo.of(apply);
        pi.setTotal(list.size());
        return pi;
    }

    public static <S, Q extends PageQuery> PageInfo<S> fakePage(List<S> list, Q query) {
        return fakePage(list, a -> a, query);
    }
}
