package com.icboluo.strategy;

/**
 * 策略参数元数据定义
 * <p>每个策略通过此记录类声明其可配置参数的名称、标签、类型、默认值和描述。
 */
public record StrategyParamMeta(
/**
 * 参数名（唯一标识，如 "buyAmount"）
 */
String name,
/**
 * 参数标签（前端展示名，如 "买入金额"）
 */
String label,
/**
 * 参数类型（如 "integer"、"decimal"、"boolean"）
 */
String type,
/**
 *默认值
 */
Object defaultValue,
/**
 * 参数描述
 */
String description) {
}
