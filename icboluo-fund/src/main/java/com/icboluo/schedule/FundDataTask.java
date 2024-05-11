package com.icboluo.schedule;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
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
import com.icboluo.util.DateUtil;
import jakarta.annotation.Resource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2021-33-27 22:33
 */
//@Component
@Slf4j
@Profile("!simple")
public class FundDataTask {

    @Resource
    private FundAttentionMapper fundAttentionMapper;

    @Resource
    private FundDataMapper fundDataMapper;

    @Resource
    private FundAsyncRecordMapper fundAsyncRecordMapper;

    @Resource
    private FundInfoMapper fundInfoMapper;

    /**
     * SchedulingConfigurer 是编程时定时任务；@Scheduled 注解是声明式定时任务
     */
    @Scheduled(cron = "0 * * * * ?")
    public void asyncFundData() {
        List<FundAttention> fundAttentions = fundAttentionMapper.queryAll();
        List<FundAsyncRecord> recordList = fundAsyncRecordMapper.queryAll();
        Map<String, FundAsyncRecord> recordMap = recordList.stream()
                .collect(Collectors.groupingBy(FundAsyncRecord::getId,
                        Collectors.collectingAndThen(Collectors.toList(), li -> li.get(0))));
        LocalDate startTime = LocalDate.of(2020, 1, 1);

        for (FundAttention fundAttention : fundAttentions) {
            String fundId = fundAttention.getId();
            FundAsyncRecord dbFundAsync = recordMap.get(fundId);
            boolean haveUpdateFundData = false;
            if (dbFundAsync == null) {
                syncFundData(fundId, startTime, LocalDate.now());
                haveUpdateFundData = true;
            } else {
//            如果数据库中的开始时间比较大
                if (dbFundAsync.getStartTime().toLocalDate().isAfter(startTime)) {
                    syncFundData(fundId, startTime, dbFundAsync.getStartTime().toLocalDate());
                    haveUpdateFundData = true;
                }
//            如果数据库结束时间比现在小
                if (dbFundAsync.getEndTime().toLocalDate().isBefore(LocalDate.now())) {
                    syncFundData(fundId, dbFundAsync.getEndTime().toLocalDate(), LocalDate.now());
                    haveUpdateFundData = true;
                }
            }
            if (haveUpdateFundData) {
                FundAsyncRecord fundAsyncRecord = new FundAsyncRecord();
                fundAsyncRecord.setId(fundId);
                fundAsyncRecord.setStartTime(DateUtil.firstTime(startTime));
                fundAsyncRecord.setEndTime(DateUtil.firstTime(LocalDate.now().minusDays(1)));
                fundAsyncRecordMapper.insertOrUpdateBatch(Collections.singletonList(fundAsyncRecord));
            }
        }
    }

    @Scheduled(cron = "0 * * * * ?")
    public void asyncFundInfo() {
        List<FundAttention> fundAttentions = fundAttentionMapper.queryAll();
        List<FundInfo> fundInfos = fundInfoMapper.selectAll();
        List<FundInfo> list = new ArrayList<>();
        for (FundAttention fundAttention : fundAttentions) {
            boolean isExistName = false;
            for (FundInfo dbInfo : fundInfos) {
                if (dbInfo.getId().equals(fundAttention.getId())) {
                    if (StringUtils.hasText(dbInfo.getName())) {
                        isExistName = true;
                    }
                }
            }
            if (isExistName) {
                continue;
            }
            String name = httpFundInfo(fundAttention.getId());
            FundInfo fundInfo = new FundInfo();
            fundInfo.setId(fundAttention.getId());
            fundInfo.setName(name);
            list.add(fundInfo);
        }
        if (!CollectionUtils.isEmpty(list)) {
            fundInfoMapper.insertOrUpdateBatch(list);
        }
    }

    private void syncFundData(@NonNull String fundId, @NonNull LocalDate start, @NonNull LocalDate end) {
        if (start.isAfter(end)) {
            return;
        }
        int countPage = 1;
        LocalDate temp = start;
        while (end.isAfter(temp)) {
            temp = temp.plusDays(21);
            countPage++;
        }
        this.addFundData(fundId, start, end, countPage);
    }

    private void addFundData(String fundId, LocalDate startTime, LocalDate endTime, int countPage) {
        FundDataGetBO fundDataGetBO = FundDataGetBO.builder()
                .fundId(fundId)
                .startTime(startTime.toString())
                .endTime(endTime.toString())
                .build();
        for (int i = 0; i < countPage; i++) {
            fundDataGetBO.setPageNum(i + 1);
            fundDataGetBO.setPageSize(15);
            FundCompleteBO fundComplete = httpFundData(fundDataGetBO);
            FundCompleteDateBO data = fundComplete.getData();
            List<FundDateBO> fundDateList = data.getFundDateList();
            List<FundData> fundDataList = fundDateList.stream()
                    .map(item -> item.businessToData(fundId))
                    .toList();
            if (CollectionUtils.isEmpty(fundDataList)) {
                break;
            } else {
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
