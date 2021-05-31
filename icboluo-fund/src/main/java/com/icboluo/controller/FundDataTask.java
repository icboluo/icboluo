package com.icboluo.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.icboluo.entity.FundAttention;
import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.object.bo.FundCompleteBO;
import com.icboluo.object.bo.FundCompleteDateBO;
import com.icboluo.object.bo.FundDateBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @date 2021-33-27 22:33
 */
//@Component
@Slf4j
public class FundDataTask {

    @Resource
    private FundAttentionMapper fundAttentionMapper;

    @Resource
    private FundDataMapper fundDataMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void asyncFundData() {
        List<FundAttention> fundAttentions = fundAttentionMapper.queryAll();
        for (FundAttention fundAttention : fundAttentions) {
            FundCompleteBO fundCompleteBO = testDepartmentList1(fundAttention.getId());
            FundCompleteDateBO data = fundCompleteBO.getData();
            List<FundDateBO> fundDateList = data.getFundDateList();
            List<FundData> fundDataList = fundDateList.stream()
                    .map(item -> item.businessToData(fundAttention.getId()))
                    .collect(Collectors.toList());
            fundDataMapper.insertOrUpdateBatch(fundDataList);
        }
    }

    /**
     * httClient 请求 GET
     * 获取基金网数据1
     */
    public static FundCompleteBO testDepartmentList1(String fundId) {
        Integer pageIndex = 1;
        Integer pageSize = 20;
        String startTime = "2021-01-01";
        String endTime = LocalDate.now().toString();
        String referer = "http://fundf10.eastmoney.com/f10/jjjz_" + fundId + ".html";
        long time = System.currentTimeMillis();
        String url = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306596328894644803_1571038362181&" +
                "fundCode=%s&pageIndex=%s&pageSize=%s&startDate=%s&endDate=%s&_=%s";
        url = String.format(url, fundId, pageIndex, pageSize, startTime, endTime, time);
        HttpRequest request = HttpUtil.createGet(url);
        request.header("Referer", referer);
        String str = request.execute().body();
        return strToBusinessObject(str);
    }

    private static FundCompleteBO strToBusinessObject(String str) {
        log.warn(str);
        int indexStart = str.indexOf("(");
        String subStr = str.substring(indexStart + 1, str.length() - 1);
        log.warn(subStr);
        return JSON.parseObject(subStr, FundCompleteBO.class);
    }
}
