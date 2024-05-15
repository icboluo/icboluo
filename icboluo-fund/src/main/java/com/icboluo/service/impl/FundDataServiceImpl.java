package com.icboluo.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.icboluo.common.FundMetricEnum;
import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.object.bo.FundMetricBo;
import com.icboluo.object.query.FundDataChooseQuery;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.query.FundWeightQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataRecentVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.object.vo.FundMetricVo;
import com.icboluo.service.FundDataService;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.DateUtil;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.MathUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

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
            PageMethod.startPage(query.getPageNum(), query.getPageSize());
            List<FundDataVO> list = fundDataMapper.selectByQuery(query);
            fillView(list);
            return BeanUtil.fakePage(list, query);
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

            return BeanUtil.fakePage(simChoose, a -> {
                fillView(a);
                return a;
            }, query);
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
                .map(item -> BeanUtil.copyProperties(item, FundData.class))
                .toList();
        DoubleSummaryStatistics summaryStatistics = list.stream()
                .map(FundData::getIncreaseRateDay)
                .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));

        Map<DayOfWeek, List<FundData>> dayOfWeekMap = new EnumMap<>(DayOfWeek.class);
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
        Map<DayOfWeek, BigDecimal> weekMap = new EnumMap<>(DayOfWeek.class);
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
        FundDataChooseQuery fundDataChooseQuery = FundDataChooseQuery.builder()
                .fundId(fundId)
                .chooseDate(myChooseDate.minusDays(10))
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
                .list(res.stream().sorted((a, b) -> -1).toList())
                .nextAvg(BigDecimal.valueOf(summaryStatistics2.getAverage()).setScale(4, RoundingMode.HALF_UP))
                .build();
    }

    private List<FundData> findRecentData(List<FundData> list, LocalDate myChooseDate, int length) {
        return list.stream()
                .dropWhile(item -> item.getNetValueDate().isBefore(myChooseDate))
                .limit(length).toList();
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

        fundDataMapper.deleteByFundIdDate(fundId, LocalDate.now());
        fundDataMapper.insertSelective(data);
    }

    @Override
    public BigDecimal last10DaysAvg(List<FundData> fundList) {
        if (CollectionUtils.isEmpty(fundList)) {
            return BigDecimal.ZERO;
        }
        if (fundList.size() > 10) {
            fundList = fundList.subList(0, 10);
        }
        Double tenAvg = fundList.stream()
                .map(FundData::getIncreaseRateDay)
                .filter(Objects::nonNull)
                .collect(Collectors.averagingDouble(BigDecimal::doubleValue));
        return BigDecimal.valueOf(tenAvg);
    }

    @Override
    public Map<Integer, BigDecimal> yearAvg(List<FundData> fundList) {
        LocalDate now = LocalDate.now();
        Map<Integer, BigDecimal> avgMap = new HashMap<>();
//            每年进行计算
        for (int i = 0; i < 5; i++) {
            LocalDate beforeDate = now.minusYears(i + 1);
            Double val = fundList.stream()
                    .filter(fundData -> !fundData.getNetValueDate().isBefore(beforeDate))
                    .map(FundData::getIncreaseRateDay)
                    .filter(Objects::nonNull)
                    .collect(Collectors.averagingDouble(BigDecimal::doubleValue));
            avgMap.put(i, BigDecimal.valueOf(val));
        }
        return avgMap;
    }

    @Override
    public void delete(String id) {
        fundDataMapper.deleteById(id);
    }

    @Override
    public List<FundMetricVo> selectWeight(FundWeightQuery query) {
        List<FundData> list = fundDataMapper.selectByFundId(query.getFundId());

        List<FundMetricVo> res = new ArrayList<>();
        res.add(TEN_TRADING_AGO(list));
        res.add(ONE_MONTH_AGO(list));
        res.add(ONE_YEAR_AGO(list));
        res.add(TWO_YEAR_AGO(list));
        res.add(THREE_YEAR_AGO(list));
        res.add(ONE_WEEK_RISE_5(list));
        res.add(ONE_WEEK_RISE_10(list));
        res.add(ONE_WEEK_RISE_20(list));
        res.add(RISE_THIS_MONTH_LAST_YEAR(list));
        res.add(RISE_RECENT_THIS_MONTH_LAST_YEAR(list));
        res.add(RISE_THIS_MONTH_LAST_YEAR_2(list));
        res.add(RISE_RECENT_THIS_MONTH_LAST_YEAR_2(list));
        return res;
    }

    private FundMetricVo TEN_TRADING_AGO(List<FundData> dataList) {
        List<FundData> list = dataList.subList(dataList.size() - 10, dataList.size());
        FundMetricBo business = calBusiness(list);

        return FundMetricVo.builder()
                .metric(FundMetricEnum.TEN_TRADING_AGO)
                .businessData(business)
                .build();
    }

    private FundMetricVo ONE_MONTH_AGO(List<FundData> dataList) {
        LocalDate before = LocalDate.now().minusMonths(1L);
        List<FundData> list = dataList.stream()
                .filter(item -> DateUtil.between(item.getNetValueDate(), before, LocalDate.now()))
                .toList();
        FundMetricBo business = calBusiness(list);

        return FundMetricVo.builder()
                .metric(FundMetricEnum.ONE_MONTH_AGO)
                .businessData(business)
                .build();
    }

    private FundMetricVo ONE_YEAR_AGO(List<FundData> dataList) {
        List<FundData> list = recentYearData(dataList, 1);
        FundMetricBo business = calBusiness(list);

        return FundMetricVo.builder()
                .metric(FundMetricEnum.ONE_YEAR_AGO)
                .businessData(business)
                .build();
    }

    private FundMetricVo TWO_YEAR_AGO(List<FundData> dataList) {
        List<FundData> list = recentYearData(dataList, 2);
        FundMetricBo business = calBusiness(list);

        return FundMetricVo.builder()
                .metric(FundMetricEnum.TWO_YEAR_AGO)
                .businessData(business)
                .build();
    }

    private FundMetricVo THREE_YEAR_AGO(List<FundData> dataList) {
        List<FundData> list = recentYearData(dataList, 3);
        FundMetricBo business = calBusiness(list);

        return FundMetricVo.builder()
                .metric(FundMetricEnum.THREE_YEAR_AGO)
                .businessData(business)
                .build();
    }

    private FundMetricVo ONE_WEEK_RISE_5(List<FundData> dataList) {
        List<FundData> sourceList = recentDayData(dataList, 1);
        List<FundData> simileDataList = findSimChoose1(sourceList, recentYearData(dataList, 1), 1);
        List<List<FundData>> lists = new ArrayList<>();
        for (FundData simileData : simileDataList) {
            List<FundData> recentData = findRecentData(dataList, simileData.getNetValueDate(), 5);
            lists.add(recentData);
        }

        FundMetricBo business = new FundMetricBo(lists, 1);
        return FundMetricVo.builder()
                .metric(FundMetricEnum.ONE_WEEK_RISE_5)
                .businessData(business)
                .build();
    }

    private FundMetricVo ONE_WEEK_RISE_10(List<FundData> dataList) {
        List<FundData> sourceList = recentDayData(dataList, 1);
        List<FundData> simileDataList = findSimChoose1(sourceList, recentYearData(dataList, 1), 1);
        List<List<FundData>> lists = new ArrayList<>();
        for (FundData simileData : simileDataList) {
            List<FundData> recentData = findRecentData(dataList, simileData.getNetValueDate(), 10);
            lists.add(recentData);
        }
        FundMetricBo business = new FundMetricBo(lists, 1);
        return FundMetricVo.builder()
                .metric(FundMetricEnum.ONE_WEEK_RISE_10)
                .businessData(business)
                .build();
    }

    private FundMetricVo ONE_WEEK_RISE_20(List<FundData> dataList) {
        List<FundData> sourceList = recentDayData(dataList, 1);
        List<FundData> simileDataList = findSimChoose1(sourceList, recentYearData(dataList, 1), 1);
        List<List<FundData>> lists = new ArrayList<>();
        for (FundData simileData : simileDataList) {
            List<FundData> recentData = findRecentData(dataList, simileData.getNetValueDate(), 20);
            lists.add(recentData);
        }

        FundMetricBo business = new FundMetricBo(lists, 1);
        return FundMetricVo.builder()
                .metric(FundMetricEnum.ONE_WEEK_RISE_20)
                .businessData(business)
                .build();
    }

    private FundMetricVo RISE_THIS_MONTH_LAST_YEAR(List<FundData> dataList) {
        LocalDate first = DateUtil.firstDayOfMonth(LocalDate.now().minusYears(1));
        LocalDate last = DateUtil.lastDayOfMonth(LocalDate.now().minusYears(1));
        List<FundData> list = dataList.stream()
                .filter(item -> DateUtil.between(item.getNetValueDate(), first, last))
                .toList();

        FundMetricBo business = new FundMetricBo(list);
        return FundMetricVo.builder()
                .metric(FundMetricEnum.RISE_THIS_MONTH_LAST_YEAR)
                .businessData(business)
                .build();
    }

    private FundMetricVo RISE_RECENT_THIS_MONTH_LAST_YEAR(List<FundData> dataList) {
        LocalDate first = LocalDate.now().minusYears(1);
        LocalDate last = LocalDate.now().minusYears(1).plusMonths(1);
        List<FundData> list = dataList.stream()
                .filter(item -> DateUtil.between(item.getNetValueDate(), first, last))
                .toList();

        FundMetricBo business = new FundMetricBo(list);
        return FundMetricVo.builder()
                .metric(FundMetricEnum.RISE_RECENT_THIS_MONTH_LAST_YEAR)
                .businessData(business)
                .build();
    }

    private FundMetricVo RISE_THIS_MONTH_LAST_YEAR_2(List<FundData> dataList) {
        LocalDate first = DateUtil.firstDayOfMonth(LocalDate.now().minusYears(2));
        LocalDate last = DateUtil.lastDayOfMonth(LocalDate.now().minusYears(2));
        List<FundData> list = dataList.stream()
                .filter(item -> DateUtil.between(item.getNetValueDate(), first, last))
                .toList();

        FundMetricBo business = new FundMetricBo(list);
        return FundMetricVo.builder()
                .metric(FundMetricEnum.RISE_THIS_MONTH_LAST_YEAR_2)
                .businessData(business)
                .build();
    }

    private FundMetricVo RISE_RECENT_THIS_MONTH_LAST_YEAR_2(List<FundData> dataList) {
        LocalDate first = LocalDate.now().minusYears(2);
        LocalDate last = LocalDate.now().minusYears(2).plusMonths(1);
        List<FundData> list = dataList.stream()
                .filter(item -> DateUtil.between(item.getNetValueDate(), first, last))
                .toList();

        FundMetricBo business = new FundMetricBo(list);
        return FundMetricVo.builder()
                .metric(FundMetricEnum.RISE_RECENT_THIS_MONTH_LAST_YEAR_2)
                .businessData(business)
                .build();
    }

    private List<FundData> recentDayData(List<FundData> dataList, int beforeDay) {
        LocalDate before = LocalDate.now().minusDays(beforeDay);
        return dataList.stream()
                .filter(item -> DateUtil.between(item.getNetValueDate(), before, LocalDate.now()))
                .toList();
    }

    private List<FundData> recentYearData(List<FundData> dataList, int beforeYear) {
        LocalDate before = LocalDate.now().minusYears(beforeYear);
        return dataList.stream()
                .filter(item -> DateUtil.between(item.getNetValueDate(), before, LocalDate.now()))
                .toList();
    }

    private FundMetricBo calBusiness(List<FundData> list) {
        FundMetricBo business = new FundMetricBo(list);
        return business;
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
                boolean belong = MathUtil.between(increaseRateDay, min, max);
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
                .toList();
    }


    private List<FundData> findSimChoose1(List<FundData> sourceList, List<FundData> allList, Integer length) {
        List<FundData> res = new ArrayList<>();
//        比如当前是10个，选择2天，10-2+1=9 i的取值为0-8，当取8的时候可以选择8/9，是符合边界条件的
        for (int i = 0; i < allList.size() - length + 1; i++) {
//            source list 中第几个元素
            int sourceEleIndex = 0;
            while (sourceEleIndex < sourceList.size()) {
                FundData source = sourceList.get(sourceEleIndex);
                BigDecimal rate = source.getIncreaseRateDay();
                BigDecimal max = rate.multiply(BigDecimal.valueOf(1.2).pow(sourceEleIndex + 1));
                BigDecimal min = rate.multiply(BigDecimal.valueOf(0.8).pow(sourceEleIndex + 1));

                FundData all = allList.get(i + sourceEleIndex);
                BigDecimal increaseRateDay = all.getIncreaseRateDay();
                if (increaseRateDay == null) {
                    break;
                }
                boolean belong = MathUtil.between(increaseRateDay, min, max);
                if (belong) {
                    sourceEleIndex++;
                } else {
                    break;
                }
            }
            if (sourceEleIndex == sourceList.size()) {
                res.add(allList.get(i));
            }
        }
        return res;
    }

    private void fillView(List<FundDataVO> list) {
        list.forEach(data -> data.setDayOfWeek(data.getNetValueDate().getDayOfWeek()));
    }
}
