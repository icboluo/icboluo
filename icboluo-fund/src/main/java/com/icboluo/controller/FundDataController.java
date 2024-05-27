package com.icboluo.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icboluo.common.MyCollector;
import com.icboluo.object.co.FundDataTodayCo;
import com.icboluo.object.co.FundIdDateCo;
import com.icboluo.object.co.SimpleCalCo;
import com.icboluo.object.query.FundDataCalQuery;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataRecentVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.service.FundDataService;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.DayOfWeek;

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
     * @param query 基金id
     * @return 计算结果
     */
    @PostMapping("cal")
    public FundDataCalVO cal(@RequestBody FundDataCalQuery query) {
        return fundDataService.cal(query.getFundId(), query.getStartDate());
    }

    @PostMapping("/simCal")
    public Double simCal(@RequestBody SimpleCalCo client) {
        double divide = client.getTarget() - client.getSource();
        return divide / client.getSource() * 100;
    }

    @PostMapping("/findRecentData")
    public FundDataRecentVO findRecentData(@RequestBody FundIdDateCo client) {
        return fundDataService.findRecentData(client.getFundId(), client.getMyChooseDate());
    }

    @PostMapping("/addToday")
    public void addToday(@RequestBody FundDataTodayCo client) {
        fundDataService.addToday(client.getFundId(), client.getRate());
    }

    @PostMapping("delete")
    public void delete(@RequestBody String id) {
        fundDataService.delete(id);
    }

    @PostMapping("/dayOfWeekTest")
    public DayOfWeek dayOfWeekTest() {
        return DayOfWeek.FRIDAY;
    }
}
