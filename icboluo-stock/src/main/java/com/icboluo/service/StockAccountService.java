package com.icboluo.service;

import com.icboluo.object.vo.AccountVo;
import com.icboluo.object.vo.PositionDistributionVo;
import com.icboluo.object.vo.ProfitPointVo;
import com.icboluo.object.vo.RankVo;

import java.util.List;

public interface StockAccountService {
    AccountVo getAccount(Integer seasonId, String playerName);

    List<RankVo> getRank(Integer seasonId);

    List<ProfitPointVo> getProfitCurve(Integer seasonId, String playerName);

    List<PositionDistributionVo> getPositionDistribution(Integer seasonId, String playerName);
}
