package com.icboluo.enumerate;

import com.icboluo.util.IcBoLuoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;

/**
 * 可以使用联想搜索
 * 状态值枚举，包含了对状态值的相关操作
 * 枚举用equals比较可能相等，用==比较，不同的枚举类型值不会相等
 * switch中可以使用枚举
 * 常量比较方式用equals
 *
 * @author icboluo
 * @date 2020/11/12 20:29
 */
@AllArgsConstructor
public enum StatusEnum {
    /**
     * 自由态
     */
    FREEDOM("freedom", "自由态", 1),
    SAVE("save", "保存态", 2),
    AUDIT("freedom", "审核态", 3),
    ABANDON_TRIAL("abandon trial", "弃审态", 4),
    REFUSAL("refusal", "拒审态", 5),
    INVALID("invalid", "作废状态", 6),
    PENDING("Pending", "待处理", 7),
    PROCESSED("Processed", "已处理", 8),
    IN_MAINTENANCE("In maintenance", "维护中", 9),
    IN_IMPLEMENTATION("In implementation", "实施中", 10),
    QX("In implementation", "取消", 10),
    GB("In implementation", "关闭", 10),
    YGB("In implementation", "已关闭", 10),
    DGB("In implementation", "待关闭", 10),
    ;

    /**
     * 英文描述
     */
    private final String english;
    /**
     * 中文描述
     */
    private final String chinese;
    /**
     * 状态值，禁止为null
     * <p/>用的包装类型，没有使用基本类型
     */
    @Getter
    private final Integer status;

    private static final EnumSet<StatusEnum> ALL_SET = EnumSet.allOf(StatusEnum.class);

    public static <T> String findNameByStatusLanguage(T status, LanguageEnum language) {
        Optional<StatusEnum> e = findByStatus(status);
        return chooseByLanguage(language, e);
    }

    private static String chooseByLanguage(LanguageEnum language, Optional<StatusEnum> e) {
        StatusEnum statusEnum = e.orElseThrow(() -> new IcBoLuoException(ExceptionEnum.STATUS_VALUE_NOT_FOUND));
        return LanguageEnum.CHINESE == language ? statusEnum.chinese : statusEnum.english;
    }

    /**
     * 根据状态查询定义的枚举类型
     *
     * @param status 状态
     * @return 定义的枚举类型
     */
    private static Optional<StatusEnum> findByStatus(Integer status) {
//        校验写到底层会让所有的方法都通过校验，但是也会损耗性能（在这里的校验只是相当于移动到底层）
//        校验并不一定需要写到最上层，有的时候需要准备校验参数，数据要写到函数内部
        validateStatusUniqueness();
        return ALL_SET.stream()
                .filter(e -> e.status.equals(status))
                .findAny();
    }

    public static <T> Optional<StatusEnum> findByStatus(T status) {
        int state;
        if (status instanceof String) {
            state = Integer.parseInt((String) status);
        } else {
            throw new IcBoLuoException(ExceptionEnum.STATUS_VALUE_ERROR);
        }
        return findByStatus(state);
    }

    /**
     * 校验当前枚举类状态值(已包含空值校验)
     */
    private static void validateStatusUniqueness() {
        long count = ALL_SET.stream()
                .map(StatusEnum::getStatus)
                .filter(Objects::nonNull)
                .distinct()
                .count();
        if (count != ALL_SET.size()) {
            throw new IcBoLuoException(ExceptionEnum.ENUM_DEFINED_ERROR);
        }
    }
}
