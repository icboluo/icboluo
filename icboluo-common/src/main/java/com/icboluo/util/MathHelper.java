package com.icboluo.util;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author icboluo
 * @date 2020/12/2 20:29
 */
public class MathHelper {
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
        BigDecimal m = toBigDecimal(molecular);
        BigDecimal d = toBigDecimal(denominator);
        if (mode == null) {
            mode = RoundingMode.HALF_UP;
        }
        if (scale == null) {
            scale = 2;
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
    public static <M, D> String dividePercentage(@NonNull M molecular, @NonNull D denominator, Integer scale, RoundingMode mode) {
        BigDecimal multiply = toBigDecimal(molecular).multiply(new BigDecimal(100));
        BigDecimal divide = divide(multiply, denominator, scale, mode);
        return divide + "%";
    }

    public static <M, D> String dividePercentage(@NonNull M molecular, @NonNull D denominator, int scale) {
        return dividePercentage(molecular, denominator, scale, null);
    }

    public static <M, D> String dividePercentage(@NonNull M molecular, @NonNull D denominator) {
        return dividePercentage(molecular, denominator, null, null);
    }


    private static <T> BigDecimal toBigDecimal(@NonNull T number) {
        BigDecimal res = null;
        if (number instanceof BigDecimal) {
            res = (BigDecimal) number;
        } else if (number instanceof Integer) {
            Integer mTemp = (Integer) number;
            res = new BigDecimal(mTemp);
        } else if (number instanceof Long) {
            Long mTemp = (Long) number;
            res = new BigDecimal(mTemp);
        }
        return res;
    }
}
