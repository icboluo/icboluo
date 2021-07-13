package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.common.ResponseResult;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataRecentVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.service.FundDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * (FundData)表控制层
 *
 * @author icboluo
 * @since 2021-05-28 00:12:38
 */
@RestController
@RequestMapping("/fundData")
@CrossOrigin(origins = "*", maxAge = 3600)
@ResponseResult
public class FundDataController {
    /**
     * 服务对象
     */
    @Resource
    private FundDataService fundDataService;


    @GetMapping("/init")
    public PageInfo<FundDataVO> init(FundDataQuery query) {
        return fundDataService.selectByQuery(query);
    }

    @GetMapping("cal")
    public FundDataCalVO cal(String fundId, LocalDate startDate) {
        return fundDataService.cal(fundId, startDate);
    }

    @GetMapping("/simCal")
    public Double simCal(Double source, Double target) {
        double divide = target - source;
        return divide / source * 100;
    }

    @GetMapping("/findRecentData")
    public FundDataRecentVO findRecentData(String fundId, LocalDate myChooseDate) {
        return fundDataService.findRecentData(fundId, myChooseDate);
    }

    @GetMapping("/addToday")
    public void addToday(String fundId, BigDecimal rate) {
        fundDataService.addToday(fundId, rate);
    }
}
