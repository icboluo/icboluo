package icboluo.enumerate;

import org.springframework.util.CollectionUtils;

import java.util.EnumSet;

/**
 * @author icboluo
 * @date 2021-06-19 21:06
 */
public interface EnumServiceInterface {

    EnumSet<? extends EnumInter> allSet();

    default <T> String findEnumNameById(T id) {
        EnumInter enumById = findEnumById(id);
        return enumById.getEn();
    }

    default <T> EnumInter findEnumById(T id) {
        if (id == null) {
            return null;
        }
        EnumSet<? extends EnumInter> set = allSet();
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        return set.stream()
                .filter(um -> um.getId().equals(id.toString()))
                .findAny()
                .orElse(null);
    }
}
