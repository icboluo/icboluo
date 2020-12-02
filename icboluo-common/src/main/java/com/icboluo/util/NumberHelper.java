package com.icboluo.util;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author icboluo
 * @date 2020/12/2 20:29
 */
public class NumberHelper {
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
    public static <M, D> BigDecimal divide(@NonNull M molecular, @NonNull D denominator, int scale, @NonNull RoundingMode mode) {
        BigDecimal m = toBigDecimal(molecular);
        BigDecimal d = toBigDecimal(denominator);
        return m.divide(d, scale, mode);
    }

    private static <T> BigDecimal toBigDecimal(@NonNull T number) {
        BigDecimal res = null;
        if (number instanceof Integer) {
            Integer mTemp = (Integer) number;
            res = new BigDecimal(mTemp);
        }
        return res;
    }
}
