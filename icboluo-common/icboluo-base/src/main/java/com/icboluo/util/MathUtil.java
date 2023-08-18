package com.icboluo.util;

import com.alibaba.fastjson.util.TypeUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 数学工具类
 *
 * @author icboluo
 * @since 2020/12/2 20:29
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathUtil {
    /**
     * 除法
     *
     * @param molecular   分子
     * @param denominator 分母
     * @param scale       小数位数
     * @param mode        精确方式
     * @param <M>         分子类型
     * @param <D>         分母类型
     * @return 除法返回值
     */
    public static <M, D> BigDecimal divide(@NonNull M molecular, @NonNull D denominator, Integer scale, RoundingMode mode) {
        BigDecimal m = TypeUtils.castToBigDecimal(molecular);
        BigDecimal d = TypeUtils.castToBigDecimal(denominator);
        if (mode == null) {
            mode = RoundingMode.HALF_UP;
        }
        if (scale == null) {
            scale = 2;
        }
        if (equals(m, BigDecimal.ZERO) || equals(d, BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        return m.divide(d, scale, mode);
    }

    public static <M, D> BigDecimal divide(@NonNull M molecular, @NonNull D denominator, int scale) {
        return divide(molecular, denominator, scale, null);
    }

    public static <M, D> BigDecimal divide(@NonNull M molecular, @NonNull D denominator) {
        return divide(molecular, denominator, null, null);
    }

    /**
     * 除法计算百分数
     *
     * @param molecular   分子
     * @param denominator 分母
     * @param scale       小数位数
     * @param mode        精确方式
     * @param <M>         分子类型
     * @param <D>         分母类型
     * @return 百分数返回值
     */
    public static <M, D> BigDecimal dividePercentage(@NonNull M molecular, @NonNull D denominator, Integer scale, RoundingMode mode) {
        BigDecimal mBd = TypeUtils.castToBigDecimal(molecular);
        BigDecimal dBd = TypeUtils.castToBigDecimal(denominator);
        BigDecimal divide = divide(mBd.multiply(new BigDecimal(100)), dBd, scale, mode);
        if (equals(divide, BigDecimal.valueOf(100))) {
            if (equals(mBd, dBd)) {
                return BigDecimal.valueOf(100);
            }
            return BigDecimal.valueOf(99.99);
        }
        if (equals(divide, BigDecimal.ZERO)) {
            if (equals(mBd, BigDecimal.ZERO)) {
                return BigDecimal.ZERO;
            }
            return BigDecimal.valueOf(0.01);
        }
        return divide;
    }

    public static <M, D> BigDecimal dividePercentage(@NonNull M molecular, @NonNull D denominator, int scale) {
        return dividePercentage(molecular, denominator, scale, null);
    }

    public static <M, D> BigDecimal dividePercentage(@NonNull M molecular, @NonNull D denominator) {
        return dividePercentage(molecular, denominator, null, null);
    }

    @SafeVarargs
    public static <T extends Comparable<T>> T max(T... arr) {
        return Arrays.stream(arr).max(T::compareTo).orElse(null);
    }

    @SafeVarargs
    public static <T extends Comparable<T>> T min(T... arr) {
        return Arrays.stream(arr).min(T::compareTo).orElse(null);
    }

    public static <T extends Comparable<T>> boolean between(T source, T a, T b) {
        if (equals(source, a) || equals(source, b)) {
            return true;
        }
        return source.compareTo(a) * source.compareTo(b) == -1;
    }

    public static <T> BigDecimal avg(List<T> list, Function<T, BigDecimal> mapFun) {
        if (list == null || list.isEmpty()) {
            return BigDecimal.ZERO;
        }
        List<BigDecimal> bdList = list.stream().map(mapFun).filter(Objects::nonNull).toList();
        BigDecimal sum = bdList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return divide(sum, bdList.size());
    }

    /**
     * 计算成功率
     *
     * @param list      列表元素
     * @param isSuccess 断言是否成功
     * @param <T>       列表元素类型
     * @return 成功率 eg：99.87
     */
    public static <T> BigDecimal successRate(List<T> list, Predicate<T> isSuccess) {
        if (list == null || list.isEmpty()) {
            return BigDecimal.ZERO;
        }
        List<T> bdList = list.stream().filter(Objects::nonNull).toList();
        long sum = bdList.stream().filter(isSuccess).count();
        return dividePercentage(sum, bdList.size());
    }

    /**
     * 数值是否相等 包装类型比较大小的时候不要用equals，用compare
     *
     * @param a   参数a
     * @param b   参数b
     * @param <T> 数据类型
     * @return a和b是否相等
     */
    private static <T extends Comparable<T>> boolean equals(T a, T b) {
        return a.compareTo(b) == 0;
    }
}
