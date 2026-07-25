package com.icboluo.controller;

import com.icboluo.object.co.AccountQueryCo;
import com.icboluo.object.co.SeasonQueryCo;
import com.icboluo.object.vo.AccountVo;
import com.icboluo.object.vo.PositionDistributionVo;
import com.icboluo.object.vo.ProfitPointVo;
import com.icboluo.object.vo.RankVo;
import com.icboluo.service.StockAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 股票账户控制器
 */
@RestController
@RequestMapping("stockAccount")
@RequiredArgsConstructor
public class StockAccountController {
    private final StockAccountService stockAccountService;

    /**
     * 查询账户信息
     */
    @PostMapping
    public AccountVo getAccount(@RequestBody @Valid AccountQueryCo co) {
        return stockAccountService.getAccount(co.getSeasonId(), co.getPlayerName());
    }

    /**
     * 查询赛季排行榜
     */
    @PostMapping("rank")
    public List<RankVo> getRank(@RequestBody @Valid SeasonQueryCo co) {
        return stockAccountService.getRank(co.getSeasonId());
    }

    /**
     * 收益曲线
     */
    @PostMapping("profitCurve")
    public List<ProfitPointVo> getProfitCurve(@RequestBody @Valid AccountQueryCo co) {
        return stockAccountService.getProfitCurve(co.getSeasonId(), co.getPlayerName());
    }

    /**
     * 持仓分布
     */
    @PostMapping("positionDistribution")
    public List<PositionDistributionVo> getPositionDistribution(@RequestBody @Valid AccountQueryCo co) {
        return stockAccountService.getPositionDistribution(co.getSeasonId(), co.getPlayerName());
    }
}
