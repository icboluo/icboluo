package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.entity.FundAttention;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.query.FundWeightQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.object.vo.FundMetricVo;
import com.icboluo.service.FundAttentionService;
import com.icboluo.service.FundDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * (FundAttention)表控制层
 *
 * @author icboluo
 * @since 2021-05-27 23:05:01
 */
@RestController
@RequestMapping("fundAttention")
@RequiredArgsConstructor
public class FundAttentionController {

    private final FundAttentionService fundAttentionService;

    private final FundAttentionMapper fundAttentionMapper;

    private final FundDataService fundDataService;


    @PostMapping("detail")
    public FundAttention detail(@RequestBody String id) {
        return fundAttentionMapper.selectById(id);
    }

    @PostMapping("init")
    public PageInfo<FundAttentionVO> init(@RequestBody FundAttentionQuery query) {
        return fundAttentionService.init(query);
    }

    @PostMapping("delete")
    public void delete(@RequestBody String id) {
        fundAttentionService.delete(id);
    }

    @PostMapping("fundWeight")
    public List<FundMetricVo> fundWeight(@RequestBody FundWeightQuery query) {
        return fundDataService.selectWeight(query);
    }

    public static void main(String[] args) {
        double cur = 15000;
        double rate = 3;

        // 当收益率为1的时候，卖出金额
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(100, 11);
        map.put(200, 12);
        map.put(300, 13);
        map.put(400, 14);
        map.put(500, 15);
        map.put(600, 16);
        map.put(700, 17);
        map.put(800, 18);
        map.put(900, 19);
        map.put(1000, 20);
        map.put(2000, 29);
        map.put(3000, 38);
        map.put(4000, 47);
        map.put(5000, 56);
        map.put(6000, 65);
        map.put(7000, 74);
        map.put(8000, 83);
        map.put(9000, 92);
        map.put(10000, 100);
        map.put(20000, 200);
        map.put(30000, 300);

        Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry((int) cur);
        Map.Entry<Integer, Integer> lowerEntry = map.lowerEntry((int) cur);

        BigDecimal temp = BigDecimal.valueOf(ceilingEntry.getValue() - lowerEntry.getValue())
                .divide(BigDecimal.valueOf(ceilingEntry.getKey() - lowerEntry.getKey()), 4, BigDecimal.ROUND_HALF_UP);
        double simpleMoney = (cur - lowerEntry.getKey()) * temp.doubleValue() + lowerEntry.getValue();
        double res = rate * rate * simpleMoney;
        System.out.println(res);
    }
}
