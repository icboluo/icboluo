package com.icboluo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icboluo.entity.StockBotConfig;
import com.icboluo.mapper.StockBotConfigMapper;
import com.icboluo.object.co.BotComposeCo;
import com.icboluo.object.vo.PresetBotVo;
import com.icboluo.object.vo.StrategyVo;
import com.icboluo.strategy.StrategyRegistry;
import com.icboluo.strategy.BuyStrategy;
import com.icboluo.strategy.SellStrategy;
import com.icboluo.util.I18nException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 机器人策略管理控制器
 */
@RestController
@RequestMapping("bot-strategy")
@RequiredArgsConstructor
public class BotStrategyController {
    private final StrategyRegistry strategyRegistry;
    private final StockBotConfigMapper stockBotConfigMapper;

    /**
     * 查询买入策略列表
     */
    @PostMapping("buy/list")
    public List<StrategyVo> buyList() {
        return strategyRegistry.getAllBuyStrategies().stream().map(this::toStrategyVo).toList();
    }

    /**
     * 查询卖出策略列表
     */
    @PostMapping("sell/list")
    public List<StrategyVo> sellList() {
        return strategyRegistry.getAllSellStrategies().stream().map(this::toStrategyVo).toList();
    }

    /**
     * 查询预置机器人列表
     */
    @PostMapping("preset/list")
    public List<PresetBotVo> presetList() {
        List<PresetBotVo> presets = new ArrayList<>();
        presets.add(buildPreset("定投机器人", "FIXED_DCA", "NEVER_SELL", null, null));
        presets.add(buildPreset("波段机器人", "DIP_BUY", "RISE_SELL", "{\"buyThreshold\":-2}", "{\"sellThreshold\":3}"));
        presets.add(buildPreset("趋势机器人", "MOMENTUM_BUY", "DROP_SELL", null, null));
        presets.add(buildPreset("逆向机器人", "DIP_BUY", "TIERED_SELL", "{\"buyThreshold\":-2}", null));
        presets.add(buildPreset("止盈止损机器人", "EQUAL_BUY", "TAKE_PROFIT_STOP_LOSS", null, "{\"takeProfit\":10,\"stopLoss\":-5}"));
        presets.add(buildPreset("分批建仓机器人", "SCALE_IN", "RISE_SELL", "{\"totalShares\":5}", "{\"sellThreshold\":3}"));
        return presets;
    }

    /**
     * 查询组合机器人列表
     */
    @PostMapping("compose/list")
    public List<PresetBotVo> composeList(@RequestBody BotComposeCo co) {
        List<StockBotConfig> configs = stockBotConfigMapper.selectList(new LambdaQueryWrapper<StockBotConfig>().eq(StockBotConfig::getSeasonId, co.getSeasonId()).eq(StockBotConfig::getIsPreset, false));
        return configs.stream().map(this::toPresetBotVo).toList();
    }

    /**
     * 创建组合机器人
     */
    @PostMapping("compose")
    public void compose(@RequestBody @Valid BotComposeCo co) {
        BuyStrategy buyStrategy = strategyRegistry.getBuyStrategy(co.getBuyStrategyId());
        if (buyStrategy == null) {
            throw new I18nException("买入策略不存在：" + co.getBuyStrategyId());
        }
        SellStrategy sellStrategy = strategyRegistry.getSellStrategy(co.getSellStrategyId());
        if (sellStrategy == null) {
            throw new I18nException("卖出策略不存在：" + co.getSellStrategyId());
        }
        StockBotConfig config = new StockBotConfig();
        config.setSeasonId(co.getSeasonId());
        config.setBotName(co.getName());
        config.setBuyStrategyId(co.getBuyStrategyId());
        config.setSellStrategyId(co.getSellStrategyId());
        config.setBuyParams(co.getBuyParams());
        config.setSellParams(co.getSellParams());
        config.setIsPreset(false);
        stockBotConfigMapper.insert(config);
    }

    /**
     * 删除组合机器人
     */
    @PostMapping("compose/{id}")
    public void deleteCompose(@PathVariable Integer id) {
        StockBotConfig config = stockBotConfigMapper.selectById(id);
        if (config == null) {
            throw new I18nException("机器人配置不存在");
        }
        if (Boolean.TRUE.equals(config.getIsPreset())) {
            throw new I18nException("预置机器人不允许删除");
        }
        stockBotConfigMapper.deleteById(id);
    }

    private StrategyVo toStrategyVo(BuyStrategy strategy) {
        StrategyVo vo = new StrategyVo();
        vo.setId(strategy.getId());
        vo.setName(strategy.getName());
        vo.setDescription(strategy.getDescription());
        vo.setParams(strategy.getParamMetas());
        return vo;
    }

    private StrategyVo toStrategyVo(SellStrategy strategy) {
        StrategyVo vo = new StrategyVo();
        vo.setId(strategy.getId());
        vo.setName(strategy.getName());
        vo.setDescription(strategy.getDescription());
        vo.setParams(strategy.getParamMetas());
        return vo;
    }

    private PresetBotVo buildPreset(String name, String buyStrategyId, String sellStrategyId, String buyParams, String sellParams) {
        PresetBotVo vo = new PresetBotVo();
        vo.setName(name);
        vo.setBotName(name);
        vo.setBuyStrategyId(buyStrategyId);
        vo.setSellStrategyId(sellStrategyId);
        vo.setBuyParams(buyParams);
        vo.setSellParams(sellParams);
        BuyStrategy buyStrategy = strategyRegistry.getBuyStrategy(buyStrategyId);
        vo.setBuyStrategyName(buyStrategy != null ? buyStrategy.getName() : buyStrategyId);
        SellStrategy sellStrategy = strategyRegistry.getSellStrategy(sellStrategyId);
        vo.setSellStrategyName(sellStrategy != null ? sellStrategy.getName() : sellStrategyId);
        return vo;
    }

    private PresetBotVo toPresetBotVo(StockBotConfig config) {
        PresetBotVo vo = new PresetBotVo();
        vo.setId(config.getId());
        vo.setName(config.getBotName());
        vo.setBotName(config.getBotName());
        vo.setBuyStrategyId(config.getBuyStrategyId());
        vo.setSellStrategyId(config.getSellStrategyId());
        vo.setBuyParams(config.getBuyParams());
        vo.setSellParams(config.getSellParams());
        BuyStrategy buyStrategy = strategyRegistry.getBuyStrategy(config.getBuyStrategyId());
        vo.setBuyStrategyName(buyStrategy != null ? buyStrategy.getName() : config.getBuyStrategyId());
        SellStrategy sellStrategy = strategyRegistry.getSellStrategy(config.getSellStrategyId());
        vo.setSellStrategyName(sellStrategy != null ? sellStrategy.getName() : config.getSellStrategyId());
        return vo;
    }
}
