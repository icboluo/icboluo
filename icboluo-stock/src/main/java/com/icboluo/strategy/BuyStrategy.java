package com.icboluo.strategy;

import java.util.List;

/**
 * 买入策略接口
 * <p>每种买入策略包含策略标识、名称、描述、参数定义和执行逻辑。
 * 实现类通过 {@code @Component} 注解注册为 Spring Bean，
 * 由 {@link StrategyRegistry} 在启动时自动收集。
 */
public interface BuyStrategy {
    /**
     * 策略唯一标识（如 "fixed_dca"）
     */
    String getId();

    /**
     * 策略名称（如 "定投买入"）
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
     * 执行买入策略
     *
     * @param context 执行上下文，包含账户、持仓、行情、参数等信息
     */
    void execute(BotExecutionContext context);
}
