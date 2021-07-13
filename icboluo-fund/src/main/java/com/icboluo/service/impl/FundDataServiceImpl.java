package com.icboluo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.object.query.FundDataChooseQuery;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataRecentVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.service.FundDataService;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.MathHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (FundData)表服务实现类
 *
 * @author icboluo
 * @since 2021-05-28 00:12:37
 */
@Service
public class FundDataServiceImpl implements FundDataService {
    @Resource
    private FundDataMapper fundDataMapper;

    /**
     * 新增数据
     *
     * @param fundData 实例对象
     * @return 实例对象
     */
    @Override
    public FundData insert(FundData fundData) {
        fundDataMapper.insert(fundData);
        return fundData;
    }

    @Override
    public PageInfo<FundDataVO> selectByQuery(FundDataQuery query) {
//        如果不满足寻找相似的涨幅趋势条件
        if (ObjectUtils.isEmpty(query.getChooseDate()) || ObjectUtils.isEmpty(query.getChooseDateLength())) {
            PageHelper.startPage(query.getPageNum(), query.getPageSize());
            List<FundDataVO> list = fundDataMapper.selectByQuery(query);
            fillView(list);
            return PageInfo.of(list);
        } else {
            FundDataChooseQuery curQuery = FundDataChooseQuery.builder()
                    .fundId(query.getFundId())
                    .chooseDate(query.getChooseDate())
                    .chooseDateLength(query.getChooseDateLength())
                    .build();
            List<FundDataVO> sourceList = fundDataMapper.selectChooseDate(curQuery);
            if (sourceList.size() < query.getChooseDateLength()) {
                throw new IcBoLuoException("data input error ,please re choose");
            }
            List<FundDataVO> allList = fundDataMapper.selectChooseAll(query.getFundId());
            List<FundDataVO> simChoose = findSimChoose(sourceList, allList, query.getChooseDateLength());

            int start = (query.getPageNum() - 1) * query.getPageSize();
            int end = Math.min(simChoose.size(), start + query.getPageSize());
            List<FundDataVO> subList = simChoose.subList(start, end);
            fillView(subList);
            PageInfo<FundDataVO> pageInfo = new PageInfo<>();
            pageInfo.setTotal(simChoose.size());
            pageInfo.setList(subList);
            return pageInfo;
        }
    }

    @Override
    public FundDataCalVO cal(String fundId, LocalDate startDate) {
        FundDataQuery query = new FundDataQuery();
        query.setFundId(fundId);
        query.setStartDate(startDate);
        List<FundDataVO> findList = fundDataMapper.selectByQuery(query);
        List<FundData> list = findList.stream()
                .filter(item -> !ObjectUtils.isEmpty(item.getIncreaseRateDay()))
                .map(item -> BeanHelper.copyProperties(item, FundData.class))
                .collect(Collectors.toList());
        DoubleSummaryStatistics summaryStatistics = list.stream()
                .map(FundData::getIncreaseRateDay)
                .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));

        Map<DayOfWeek, List<FundData>> dayOfWeekMap = new HashMap<>();
        Map<Integer, List<FundData>> dayOfMonthMap = new HashMap<>();
        for (FundData fundData : list) {
            LocalDate netValueDate = fundData.getNetValueDate();
            DayOfWeek dayOfWeek = netValueDate.getDayOfWeek();
            int dayOfMonth = netValueDate.getDayOfMonth();
            List<FundData> weekList = dayOfWeekMap.get(dayOfWeek);
            if (CollectionUtils.isEmpty(weekList)) {
                weekList = new ArrayList<>();
            }
            List<FundData> monthList = dayOfMonthMap.get(dayOfMonth);
            if (CollectionUtils.isEmpty(monthList)) {
                monthList = new ArrayList<>();
            }
            weekList.add(fundData);
            monthList.add(fundData);
            dayOfWeekMap.put(dayOfWeek, weekList);
            dayOfMonthMap.put(dayOfMonth, monthList);
        }
        Map<DayOfWeek, BigDecimal> weekMap = new HashMap<>();
        Map<Integer, BigDecimal> monthMap = new HashMap<>();
        for (Map.Entry<DayOfWeek, List<FundData>> entry : dayOfWeekMap.entrySet()) {
            DoubleSummaryStatistics weekList = entry.getValue().stream()
                    .map(FundData::getIncreaseRateDay)
                    .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));
            weekMap.put(entry.getKey(), BigDecimal.valueOf(weekList.getAverage()).setScale(4, RoundingMode.HALF_UP));
        }
        for (Map.Entry<Integer, List<FundData>> entry : dayOfMonthMap.entrySet()) {
            DoubleSummaryStatistics weekList = entry.getValue().stream()
                    .map(FundData::getIncreaseRateDay)
                    .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));
            monthMap.put(entry.getKey(), BigDecimal.valueOf(weekList.getAverage()).setScale(4, RoundingMode.HALF_UP));
        }
        return FundDataCalVO.builder()
                .count(summaryStatistics.getCount())
                .avg(BigDecimal.valueOf(summaryStatistics.getAverage()).setScale(4, RoundingMode.HALF_UP))
                .min(summaryStatistics.getMin())
                .max(summaryStatistics.getMax())
                .weekMap(weekMap)
                .monthMap(monthMap)
                .build();
    }

    @Override
    public FundDataRecentVO findRecentData(String fundId, LocalDate myChooseDate) {
        LocalDate fiveDayAgo = myChooseDate.minusDays(10);
        FundDataChooseQuery fundDataChooseQuery = FundDataChooseQuery.builder()
                .fundId(fundId)
                .chooseDate(fiveDayAgo)
                .chooseDateLength(20)
                .build();
        List<FundDataVO> list = fundDataMapper.selectChooseDate(fundDataChooseQuery);
        fillView(list);
        FundDataVO chooseEle = list.stream()
                .filter(item -> item.getNetValueDate().equals(myChooseDate))
                .findFirst().orElse(null);
        int chooseEleIndex = list.indexOf(chooseEle);
        List<FundDataVO> res = list.subList(chooseEleIndex - 2, Math.min(chooseEleIndex + 8, list.size()));

        DoubleSummaryStatistics summaryStatistics = res.stream()
                .map(FundDataVO::getIncreaseRateDay)
                .filter(item -> !ObjectUtils.isEmpty(item))
                .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));

        DoubleSummaryStatistics summaryStatistics2 = list.subList(chooseEleIndex + 1, Math.min(chooseEleIndex + 8, list.size())).stream()
                .map(FundDataVO::getIncreaseRateDay)
                .filter(item -> !ObjectUtils.isEmpty(item))
                .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));
        return FundDataRecentVO.builder()
                .count(summaryStatistics.getCount())
                .avg(BigDecimal.valueOf(summaryStatistics.getAverage()).setScale(4, RoundingMode.HALF_UP))
                .min(summaryStatistics.getMin())
                .max(summaryStatistics.getMax())
                .list(res)
                .nextAvg(BigDecimal.valueOf(summaryStatistics2.getAverage()).setScale(4, RoundingMode.HALF_UP))
                .build();
    }

    @Override
    public void addToday(String fundId, BigDecimal rate) {
        FundData data = FundData.builder()
                .fundId(fundId)
                .increaseRateDay(rate)
                .netValueDate(LocalDate.now())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        fundDataMapper.insertSelective(data);
    }

    private List<FundDataVO> findSimChoose(List<FundDataVO> sourceList, List<FundDataVO> allList, Integer length) {
        List<FundDataVO> res = new ArrayList<>();
//        比如当前是10个，选择2天，10-2+1=9 i的取值为0-8，当取8的时候可以选择8/9，是符合边界条件的
        for (int i = 0; i < allList.size() - length + 1; i++) {
//            source list 中第几个元素
            int sourceEleIndex = 0;
            while (sourceEleIndex < sourceList.size()) {
                FundDataVO source = sourceList.get(sourceEleIndex);
                BigDecimal rate = source.getIncreaseRateDay();
                BigDecimal max = rate.multiply(BigDecimal.valueOf(1.2).pow(sourceEleIndex + 1));
                BigDecimal min = rate.multiply(BigDecimal.valueOf(0.8).pow(sourceEleIndex + 1));

                FundDataVO all = allList.get(i + sourceEleIndex);
                BigDecimal increaseRateDay = all.getIncreaseRateDay();
                if (increaseRateDay == null) {
                    break;
                }
                boolean belong = MathHelper.belongTo(increaseRateDay, min, max);
                if (belong) {
                    sourceEleIndex++;
                } else {
                    break;
                }
            }
            if (sourceEleIndex == sourceList.size()) {
                List<FundDataVO> subList = allList.subList(i, i + length);
                res.addAll(subList);
            }
        }
        return res.stream()
                .distinct()
                .sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate()))
                .collect(Collectors.toList());
    }

    private void fillView(List<FundDataVO> list) {
        list.forEach(data -> {
            data.setDayOfWeek(data.getNetValueDate().getDayOfWeek());
        });
    }
}
