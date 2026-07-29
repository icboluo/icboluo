package com.icboluo.strategy;

import java.util.List;

/**
 * 卖出策略接口
 * <p>每种卖出策略包含策略标识、名称、描述、参数定义和执行逻辑。
 * 实现类通过 {@code @Component} 注解注册为 Spring Bean，
 * 由 {@link StrategyRegistry} 在启动时自动收集。
 */
public interface SellStrategy {
    /**
     * 策略唯一标识（如 "never_sell"）
     */
    String getId();

    /**
     * 策略名称（如 "永不卖出"）
     */
    String getName();

    /**
     * 策略描述
     */
    String getDescription();

    /**
     * 策略参数元数据列表
     */
    List<StrategyParamMeta> getParamMetas();

    /**
     * 执行卖出策略
     *
     * @param context 执行上下文，包含账户、持仓、行情、参数等信息
     */
    void execute(BotExecutionContext context);
}
