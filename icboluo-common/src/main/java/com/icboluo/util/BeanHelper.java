package com.icboluo.util;


import com.icboluo.common.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author icboluo
 */
@Slf4j
public class BeanHelper {

    /**
     * 将一个对象中的属性copy到另一个对象中
     *
     * @param source 源对象
     * @param target 目标类
     * @param <T>    类的类型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        try {
            T t = target.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error("【数据转换】数据转换出错，目标对象{}构造函数异常", target.getName(), e);
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
    public static <T> List<T> copyWithCollection(List<?> sourceList, Class<T> target) {
        try {
            return sourceList.stream()
                    .map(s -> copyProperties(s, target))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("【数据转换】数据转换出错，目标对象{}构造函数异常", target.getName(), e);
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
    public static <T> Set<T> copyWithCollection(Set<?> sourceList, Class<T> target) {
        try {
            return sourceList.stream()
                    .map(s -> copyProperties(s, target))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            log.error("【数据转换】数据转换出错，目标对象{}构造函数异常", target.getName(), e);
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
}
