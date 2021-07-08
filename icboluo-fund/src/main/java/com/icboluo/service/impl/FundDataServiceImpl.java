package com.icboluo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.object.query.FundDataChooseQuery;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    @Resource
    private FundAttentionMapper fundAttentionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FundData queryById(Long id) {
        return this.fundDataMapper.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param fundData 实例对象
     * @return 实例对象
     */
    @Override
    public FundData insert(FundData fundData) {
        this.fundDataMapper.insert(fundData);
        return fundData;
    }

    @Override
    public PageInfo<FundDataVO> selectByQuery(FundDataQuery query) {
//        如果不满足寻找相似的涨幅趋势条件
        if (ObjectUtils.isEmpty(query.getChooseDate()) || ObjectUtils.isEmpty(query.getChooseDateLength())) {
            PageHelper.startPage(query.getPageNum(), query.getPageSize());
            List<FundDataVO> list = fundDataMapper.selectByQuery(query);
            list.forEach(data -> {
                data.setDayOfWeek(data.getNetValueDate().getDayOfWeek());
            });
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
            BigDecimal max = BigDecimal.valueOf(-10);
            BigDecimal min = BigDecimal.valueOf(10);
            for (FundDataVO fundDataVO : sourceList) {
                BigDecimal rate = fundDataVO.getIncreaseRateDay();
                BigDecimal curMax = rate.multiply(BigDecimal.valueOf(1.2));
                BigDecimal curMin = rate.multiply(BigDecimal.valueOf(0.8));
                max = MathHelper.max(curMax, max);
                min = MathHelper.min(curMin, min);
            }
            curQuery.setMax(max);
            curQuery.setMin(min);
            List<FundDataVO> allList = fundDataMapper.selectChooseAll(curQuery);
            List<FundDataVO> simChoose = findSimChoose(sourceList, allList, query.getChooseDateLength());
            simChoose.forEach(data -> {
                data.setDayOfWeek(data.getNetValueDate().getDayOfWeek());
            });
            PageInfo<FundDataVO> pageInfo = new PageInfo<>();
            pageInfo.setTotal(simChoose.size());
            pageInfo.setList(simChoose.subList(0, Math.min(simChoose.size(), 20)));
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
        int incrIncr = 0;
        int incrDecr = 0;
        int decrIncr = 0;
        int decrDecr = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            FundData fundData = list.get(i);
            BigDecimal nextRate = list.get(i + 1).getIncreaseRateDay();
            if (fundData.getIncreaseRateDay().compareTo(BigDecimal.ZERO) > 0) {
                if (nextRate.compareTo(BigDecimal.ZERO) > 0) {
                    incrIncr++;
                } else {
                    incrDecr++;
                }
            } else {
                if (nextRate.compareTo(BigDecimal.ZERO) > 0) {
                    decrIncr++;
                } else {
                    decrDecr++;
                }
            }
        }
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
        Map<DayOfWeek, Double> weekMap = new HashMap<>();
        Map<Integer, Double> monthMap = new HashMap<>();
        for (Map.Entry<DayOfWeek, List<FundData>> entry : dayOfWeekMap.entrySet()) {
            DoubleSummaryStatistics weekList = entry.getValue().stream()
                    .map(FundData::getIncreaseRateDay)
                    .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));
            weekMap.put(entry.getKey(), weekList.getAverage());
        }
        for (Map.Entry<Integer, List<FundData>> entry : dayOfMonthMap.entrySet()) {
            DoubleSummaryStatistics weekList = entry.getValue().stream()
                    .map(FundData::getIncreaseRateDay)
                    .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));
            monthMap.put(entry.getKey(), weekList.getAverage());
        }
        return FundDataCalVO.builder()
                .count(summaryStatistics.getCount())
                .avg(summaryStatistics.getAverage())
                .min(summaryStatistics.getMin())
                .max(summaryStatistics.getMax())
                .incrIncr(incrIncr)
                .incrDecr(incrDecr)
                .decrIncr(decrIncr)
                .decrDecr(decrDecr)
                .weekMap(weekMap)
                .monthMap(monthMap)
                .build();
    }


    private List<FundDataVO> findSimChoose(List<FundDataVO> sourceList, List<FundDataVO> allList, Integer length) {
        List<FundDataVO> res = new ArrayList<>();
//        比如当前是10个，选择2天，10-2+1=9 i的取值为0-8，当取8的时候可以选择8/9，是符合边界条件的
        for (int i = 0; i < allList.size() - length + 1; i++) {
//                判断是否是相似区间
            boolean isSimInterval = true;
            for (int j = i; j < allList.size(); j++) {
                FundDataVO all = allList.get(j);
                for (int k = 0; k < sourceList.size(); k++) {
                    FundDataVO source = sourceList.get(k);
                    BigDecimal rate = source.getIncreaseRateDay();
                    BigDecimal max = rate.multiply(BigDecimal.valueOf(1.2));
                    BigDecimal min = rate.multiply(BigDecimal.valueOf(0.8));
                    BigDecimal increaseRateDay = all.getIncreaseRateDay();
                    if (increaseRateDay == null) {
                        isSimInterval = false;
                        j++;
                        break;
                    }
                    boolean belong = MathHelper.belongTo(increaseRateDay, min, max);
                    if (!belong) {
                        isSimInterval = false;
                        break;
                    } else {
                        j++;
                        k++;
                        if (k == sourceList.size()) {

                        }
                    }
                }
            }
            if (isSimInterval) {
                List<FundDataVO> subList = allList.subList(i, i + length);
                res.addAll(subList);
            }
        }
        return res.stream()
                .distinct()
                .sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate()))
                .collect(Collectors.toList());
    }
}
