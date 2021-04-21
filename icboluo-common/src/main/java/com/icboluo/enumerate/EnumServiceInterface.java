package com.icboluo.enumerate;

import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @author icboluo
 * @date 2021-06-19 21:06
 */
public interface EnumServiceInterface {

    Set<EnumInter> allSet();

    default <T> String findEnumNameById(T id) {
        if (id == null) {
            return null;
        }
        Set<EnumInter> set = allSet();
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }

        return set.stream()
                .filter(um -> um.getId().equals(id.toString()))
                .findAny()
                .map(EnumInter::getEn)
                .orElse(null);
    }
}
