package com.icboluo.common;

import com.icboluo.object.vo.FundDataVO;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2021-55-21 23:55
 */
public class MyCollector implements Collector<FundDataVO, List<BigDecimal>, BigDecimal> {
    /**
     * 建立新的结果容器
     *
     * @return
     */
    @Override
    public Supplier<List<BigDecimal>> supplier() {
        return ArrayList::new;
    }

    /**
     * 将元素添加到结果容器
     *
     * @return
     */
    @Override
    public BiConsumer<List<BigDecimal>, FundDataVO> accumulator() {
        return (list, data) -> list.add(data.getIncreaseRateDay());
    }

    /**
     * 合并两个结果容器 用于并行流
     *
     * @return
     */
    @Override
    public BinaryOperator<List<BigDecimal>> combiner() {
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    /**
     * 对结果容器应用最终转换
     * 完成器也可以直接=中间结果
     *
     * @return
     * @see Collectors.CollectorImpl
     */
    @Override
    public Function<List<BigDecimal>, BigDecimal> finisher() {
        return list -> {
            Double collect = list.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.averagingDouble(BigDecimal::doubleValue));
            return BigDecimal.valueOf(collect);
        };
    }

    /**
     * 定义收集器的行为
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
//运行会报错，因为这样会执行强制类型转换，而事实上无法将一个Set强制转换成Map
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        /**
         * Characteristics.CONCURRENT:
         * 如果上面调用parallelStream，不管有没有这个属性都是并行
         * 不加这个属性，是多个线程操作多个结果容器，combiner也会调用多次
         * 加了这个属性，是多个线程操作一个结果容器，combiner也无需调用了
         */
        //合并方法执行多次
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
        //合并方法不会执行，多次运行该示例可能抛出异常ConcurrentModificationException
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
        /**
         * ConcurrentModificationException：并发修改异常，一个线程修改在一个集合，另一个集合在迭代这个集合，就会报出这个异常
         * 不加CONCURRENT，多个容器，也就互不干扰了；加了就一个容器，就会出现这个问题
         * */
    }
}
