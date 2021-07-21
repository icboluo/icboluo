package com.icboluo.controller;

import com.icboluo.entity.FundData;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author icboluo
 * @date 2021-55-21 23:55
 */
public class MyCollector implements Collector<FundData, BigDecimal, BigDecimal> {
    /**
     * 建立新的结果容器
     *
     * @return
     */
    @Override
    public Supplier<BigDecimal> supplier() {
        return null;
    }

    /**
     * 将元素添加到结果容器
     *
     * @return
     */
    @Override
    public BiConsumer<BigDecimal, FundData> accumulator() {
        return null;
    }

    /**
     * 合并两个结果容器 用于并行流
     *
     * @return
     */
    @Override
    public BinaryOperator<BigDecimal> combiner() {
        return null;
    }

    /**
     * 对结果容器应用最终转换
     *
     * @return
     */
    @Override
    public Function<BigDecimal, BigDecimal> finisher() {
        return null;
    }

    /**
     * 定义收集器的行为
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
