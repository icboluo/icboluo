package com.icboluo.enumerate;

import org.springframework.util.CollectionUtils;

import java.util.EnumSet;

/**
 * @author icboluo
 * @date 2021-06-19 21:06
 */
public interface EnumServiceInterface<E extends Enum<E> & EnumInter> {
    /**
     * 枚举中所有元素
     *
     * @return 枚举元素
     */
    EnumSet<E> allSet();

    /**
     * 根据id查询英文名称
     *
     * @param id  id
     * @param <T> id的类型
     * @return 英文名称
     */
    default <T> String findEnumNameById(T id) {
        EnumInter enumById = findEnumById(id);
        return enumById.getEn();
    }

    /**
     * 根据id查询枚举
     *
     * @param id  id
     * @param <T> id类型
     * @return 查询到的枚举
     */
    default <T> EnumInter findEnumById(T id) {
        if (id == null) {
            return null;
        }
        EnumSet<E> set = allSet();
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        return set.stream()
                .filter(um -> um.getId().equals(id.toString()))
                .findAny()
                .orElse(null);
    }
}
