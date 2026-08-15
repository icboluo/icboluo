package com.icboluo.controller;

import com.icboluo.object.co.AdvanceDayCo;
import com.icboluo.object.co.SeasonCreateCo;
import com.icboluo.object.co.SeasonJoinCo;
import com.icboluo.object.co.SeasonPrepareCo;
import com.icboluo.object.co.SeasonQueryCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.SeasonVo;
import com.icboluo.service.SeasonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 赛季管理控制器
 */
@RestController
@RequestMapping("season")
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    /**
     * 创建赛季
     */
    @PostMapping("create")
    public SeasonVo create(@RequestBody @Valid SeasonCreateCo co) {
        return seasonService.createSeason(co);
    }

    /**
     * 加入赛季
     */
    @PostMapping("join")
    public SeasonVo join(@RequestBody @Valid SeasonJoinCo co) {
        return seasonService.joinSeason(co);
    }

    /**
     * 玩家准备（所有玩家就绪后自动开始赛季）
     */
    @PostMapping("prepare")
    public SeasonVo prepare(@RequestBody @Valid SeasonPrepareCo co) {
        return seasonService.prepare(co);
    }

    /**
     * 开始赛季
     */
    @PostMapping("start")
    public SeasonVo start(@RequestBody @Valid AdvanceDayCo co) {
        return seasonService.startSeason(co.getSeasonId());
    }

    /**
     * 推进交易日
     */
    @PostMapping("advance")
    public List<QuoteVo> advance(@RequestBody @Valid AdvanceDayCo co) {
        return seasonService.advanceDay(co);
    }

    /**
     * 查询所有赛季
     */
    @PostMapping("list")
    public List<SeasonVo> list() {
        return seasonService.listSeasons();
    }

    /**
     * 删除赛季
     */
    @PostMapping("delete")
    public void delete(@RequestBody @Valid SeasonQueryCo co) {
        seasonService.deleteSeason(co.getSeasonId());
    }

    /**
     * 机器人对战：创建赛季、机器人加入、自动开始，一步到位
     */
    @PostMapping("bot-match")
    public SeasonVo botMatch(@RequestBody @Valid SeasonCreateCo co) {
        return seasonService.createBotMatch(co);
    }

    /**
     * 提前结束赛季
     */
    @PostMapping("finish")
    public void finish(@RequestBody @Valid SeasonQueryCo co) {
        seasonService.finishSeason(co.getSeasonId());
    }
}
