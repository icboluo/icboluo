package com.icboluo.service;

import com.icboluo.object.co.AdvanceDayCo;
import com.icboluo.object.co.SeasonCreateCo;
import com.icboluo.object.co.SeasonJoinCo;
import com.icboluo.object.co.SeasonPrepareCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.SeasonVo;

import java.util.List;

/**
 * 赛季服务接口
 */
public interface SeasonService {
    /**
     * 创建赛季
     *
     * @param co 赛季创建请求
     * @return 赛季信息
     */
    SeasonVo createSeason(SeasonCreateCo co);

    /**
     * 加入赛季
     *
     * @param co 赛季加入请求
     * @return 赛季信息
     */
    SeasonVo joinSeason(SeasonJoinCo co);

    /**
     * 玩家准备（赛季 PREPARING 阶段）。所有真人玩家就绪后自动开始赛季。
     *
     * @param co 准备请求
     * @return 赛季信息
     */
    SeasonVo prepare(SeasonPrepareCo co);

    /**
     * 开始赛季
     *
     * @param seasonId 赛季ID
     * @return 赛季信息
     */
    SeasonVo startSeason(Integer seasonId);

    /**
     * 查询所有赛季
     *
     * @return 赛季列表
     */
    List<SeasonVo> listSeasons();

    /**
     * 推进交易日
     *
     * @param co 推进请求
     * @return 当日行情列表
     */
    List<QuoteVo> advanceDay(AdvanceDayCo co);

    /**
     * 删除赛季
     *
     * @param seasonId 赛季ID
     */
    void deleteSeason(Integer seasonId);

    /**
     * 提前结束赛季
     *
     * @param seasonId 赛季ID
     */
    void finishSeason(Integer seasonId);

    /**
     * 创建机器人对战赛季：自动创建、机器人加入、自动开始
     *
     * @param co 赛季创建请求
     * @return 赛季信息（已处于PLAYING状态）
     */
    SeasonVo createBotMatch(SeasonCreateCo co);
}
