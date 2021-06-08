package com.icboluo.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icboluo.entity.FundAsyncRecord;
import com.icboluo.entity.FundAttention;
import com.icboluo.entity.FundData;
import com.icboluo.entity.FundInfo;
import com.icboluo.mapper.FundAsyncRecordMapper;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.mapper.FundInfoMapper;
import com.icboluo.object.bo.FundCompleteBO;
import com.icboluo.object.bo.FundCompleteDateBO;
import com.icboluo.object.bo.FundDataGetBO;
import com.icboluo.object.bo.FundDateBO;
import com.icboluo.util.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

    @Resource
    private FundAsyncRecordMapper fundAsyncRecordMapper;

    @Resource
    private FundInfoMapper fundInfoMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void asyncFundData() {
        List<FundAttention> fundAttentions = fundAttentionMapper.queryAll();
        LocalDate startTime = LocalDate.of(2021, 1, 1);

        for (FundAttention fundAttention : fundAttentions) {
            String fundId = fundAttention.getId();
            FundAsyncRecord dbFundAsync = fundAsyncRecordMapper.queryById(fundId);
            if (dbFundAsync == null) {
                int countPage = 1;
                LocalDate temp = startTime;
                while (LocalDate.now().isAfter(temp)) {
                    temp = temp.plusDays(15);
                    countPage++;
                }
                this.addFundData(fundAttention, startTime, LocalDate.now(), countPage);
            } else {
//                如果数据库中的开始时间比较小
                if (dbFundAsync.getStartTime().compareTo(DateHelper.firstTime(startTime)) < 0) {
                    continue;
                }
                int countPage = 1;
                LocalDate temp = startTime;
                while (dbFundAsync.getEndTime().isAfter(DateHelper.firstTime(temp))) {
                    temp = temp.plusDays(15);
                    countPage++;
                }
                this.addFundData(fundAttention, startTime, LocalDate.now(), countPage);
                this.addFundData(fundAttention, startTime, LocalDate.now(), countPage);
            }
            FundAsyncRecord fundAsyncRecord = new FundAsyncRecord();
            fundAsyncRecord.setId(fundId);
            fundAsyncRecord.setStartTime(DateHelper.firstTime(startTime));
            fundAsyncRecord.setEndTime(DateHelper.firstTime(LocalDate.now()));
            fundAsyncRecordMapper.insertOrUpdateBatch(Collections.singletonList(fundAsyncRecord));
        }
    }

    @Scheduled(cron = "0 * * * * ?")
    public void asyncFundInfo() {
        List<FundAttention> fundAttentions = fundAttentionMapper.queryAll();
        List<FundInfo> list = new ArrayList<>();
        for (FundAttention fundAttention : fundAttentions) {
            String name = httpFundInfo(fundAttention.getId());
            FundInfo fundInfo = new FundInfo();
            fundInfo.setId(fundAttention.getId());
            fundInfo.setName(name);
            list.add(fundInfo);
        }
        fundInfoMapper.insertOrUpdateBatch(list);
    }

    private void addFundData(FundAttention fundAttention, LocalDate startTime, LocalDate endTime, int countPage) {
        String fundId = fundAttention.getId();
        FundDataGetBO fundDataGetBO = FundDataGetBO.builder()
                .fundId(fundId)
                .startTime(startTime.toString())
                .endTime(endTime.toString())
                .build();
        for (int i = 0; i < countPage; i++) {
            fundDataGetBO.setPageNum(i + 1);
            fundDataGetBO.setPageSize(21);
            FundCompleteBO fundComplete = httpFundData(fundDataGetBO);
            FundCompleteDateBO data = fundComplete.getData();
            List<FundDateBO> fundDateList = data.getFundDateList();
            List<FundData> fundDataList = fundDateList.stream()
                    .map(item -> item.businessToData(fundId))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(fundDataList)) {
                fundDataMapper.insertOrUpdateBatch(fundDataList);
            }
        }
    }

    private FundCompleteBO httpFundData(FundDataGetBO fundGet) {
        String referer = "http://fundf10.eastmoney.com/f10/jjjz_" + fundGet.getFundId() + ".html";
        long time = System.currentTimeMillis();
        String url = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306596328894644803_1571038362181&" +
                "fundCode=%s&pageIndex=%s&pageSize=%s&startDate=%s&endDate=%s&_=%s";
        url = String.format(url, fundGet.getFundId(), fundGet.getPageNum(), fundGet.getPageSize(), fundGet.getStartTime(), fundGet.getEndTime(), time);
        HttpRequest request = HttpUtil.createGet(url);
        request.header("Referer", referer);
        String str = request.execute().body();
        return strToBusinessObject(str);
    }

    private static FundCompleteBO strToBusinessObject(String str) {
        int indexStart = str.indexOf("(");
        String subStr = str.substring(indexStart + 1, str.length() - 1);
        return JSON.parseObject(subStr, FundCompleteBO.class);
    }

    /**
     * httClient 请求 GET
     * 获取基金网数据2
     */
    public static String httpFundInfo(String code) {
        String referer = "http://so.eastmoney.com/web/s?keyword=" + code + "";
        String url = "http://push2.eastmoney.com/api/qt/stock/get?ut=fa5fd1943c7b386f172d6893dbfba10b&fltt" +
                "=2&fields=f59,f169,f170,f161,f163,f171,f126,f168,f164,f78,f162,f43,f46,f44,f45,f60,f47," +
                "f48,f49,f84,f116,f55,f92,f71,f50,f167,f117,f85,f84,f58,f57,f86,f172,f108,f118,f107,f164," +
                "f177&invt=2&secid=0." + code + "&cb=jQuery1124006112441213993569_1587006450385&_=1587006450403";
        url = String.format(url, code);
        HttpRequest request = HttpUtil.createGet(url);
        request.header("Referer", referer);
        String str = request.execute().body();
        int indexStart = str.indexOf("(");
        String subStr = str.substring(indexStart + 1, str.length() - 2);
        JSONObject jsonObject = JSON.parseObject(subStr);
        String data = jsonObject.getString("data");
        JSONObject dataObj = JSON.parseObject(data);
        if (dataObj == null) {
            return null;
        }
        return dataObj.getString("f58");
    }
}
