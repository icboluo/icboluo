package com.icboluo.strategy;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 策略注册中心
 * <p>在 Spring 启动时自动收集所有 {@link BuyStrategy} 和 {@link SellStrategy} 实现，
 * 并按策略标识建立索引，供运行时查询和获取策略实例。
 *
 * @author icboluo
 * @since 2026-07-25 19:35
 */
@Component
public class StrategyRegistry {
    private final Map<String, BuyStrategy> buyStrategyMap;
    private final Map<String, SellStrategy> sellStrategyMap;

    StrategyRegistry(List<BuyStrategy> buyStrategies, List<SellStrategy> sellStrategies) {
        this.buyStrategyMap = buyStrategies.stream().collect(Collectors.toMap(BuyStrategy::getId, Function.identity()));
        this.sellStrategyMap = sellStrategies.stream().collect(Collectors.toMap(SellStrategy::getId, Function.identity()));
    }

    /**
     * 获取所有买入策略
     */
    public List<BuyStrategy> getAllBuyStrategies() {
        return Collections.unmodifiableList(List.copyOf(buyStrategyMap.values()));
    }

    /**
     * 获取所有卖出策略
     */
    public List<SellStrategy> getAllSellStrategies() {
        return Collections.unmodifiableList(List.copyOf(sellStrategyMap.values()));
    }

    /**
     * 根据策略标识获取买入策略
     *
     * @param id 策略标识
     * @return 买入策略实例，不存在则返回 null
     */
    public BuyStrategy getBuyStrategy(String id) {
        return buyStrategyMap.get(id);
    }

    /**
     * 根据策略标识获取卖出策略
     *
     * @param id 策略标识
     * @return 卖出策略实例，不存在则返回 null
     */
    public SellStrategy getSellStrategy(String id) {
        return sellStrategyMap.get(id);
    }
}
