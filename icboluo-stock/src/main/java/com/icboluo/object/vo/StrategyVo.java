package com.icboluo.object.vo;

import com.icboluo.strategy.StrategyParamMeta;
import lombok.Data;

import java.util.List;

@Data
public class StrategyVo {
    /**
     * 策略标识
     */
    private String id;
    /**
     * 策略名称
     */
    private String name;
    /**
     * 策略描述
     */
    private String description;
    /**
     * 参数定义列表
     */
    private List<StrategyParamMeta> params;
}
