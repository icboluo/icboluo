package com.icboluo.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataRecentVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.service.FundDataService;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * (FundData)表控制层
 *
 * @author icboluo
 * @since 2021-05-28 00:12:38
 */
@RestController
@RequestMapping("fundData")
public class FundDataController {

    @Resource
    private FundDataService fundDataService;


    @PostMapping("init")
    public Response init(@RequestBody @Valid FundDataQuery query) {
        PageInfo<FundDataVO> pageInfo = fundDataService.selectByQuery(query);
        BigDecimal avg = pageInfo.getList().stream()
                .collect(new MyCollector());
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(pageInfo));
        jsonObject.put("isPageAvg", avg);
        return R.correct(jsonObject);
    }

    /**
     * 计算页面结果
     *
     * @param fundId    基金id
     * @param startDate 开始日期
     * @return 计算结果
     */
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

    @GetMapping("/dayOfWeekTest")
    public DayOfWeek dayOfWeekTest() {
        return DayOfWeek.FRIDAY;
    }
}
