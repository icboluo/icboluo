package com.icboluo.service.impl;

import com.icboluo.entity.FundAttention;
import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.service.FundDataService;
import com.icboluo.util.BeanHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.DayOfWeek;
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

    /**
     * 修改数据
     *
     * @param fundData 实例对象
     * @return 实例对象
     */
    @Override
    public FundData update(FundData fundData) {
        this.fundDataMapper.update(fundData);
        return this.queryById(fundData.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.fundDataMapper.deleteById(id) > 0;
    }

    @Override
    public List<FundDataVO> selectByQuery(FundDataQuery query) {
        List<FundDataVO> list = fundDataMapper.selectByQuery(query);
        list.forEach(data -> {
            data.setDayOfWeek(data.getNetValueDate().getDayOfWeek());
        });
        return list;
    }

    @Override
    public FundDataCalVO cal(String fundId, String startTime) {
        FundAttention fundAttention = fundAttentionMapper.selectByFundIdDim(fundId);
        FundDataQuery query = new FundDataQuery();
        query.setFundId(fundId);
        query.setStartTime(startTime);
        List<FundDataVO> findList = fundDataMapper.selectByQuery(query);
        List<FundData> list = findList.stream()
                .filter(item -> !ObjectUtils.isEmpty(item.getIncreaseRateDay()))
                .map(item -> BeanHelper.copyProperties(item, FundData.class))
                .collect(Collectors.toList());
        DoubleSummaryStatistics summaryStatistics = list.stream()
                .map(FundData::getIncreaseRateDay)
                .collect(Collectors.summarizingDouble(Double::valueOf));
        int incrIncr = 0;
        int incrDecr = 0;
        int decrIncr = 0;
        int decrDecr = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            FundData fundData = list.get(i);
            String nextRate = list.get(i + 1).getIncreaseRateDay();
            if (Double.parseDouble(fundData.getIncreaseRateDay()) > 0) {
                if (Double.parseDouble(nextRate) > 0) {
                    incrIncr++;
                } else {
                    incrDecr++;
                }
            } else {
                if (Double.parseDouble(nextRate) > 0) {
                    decrIncr++;
                } else {
                    decrDecr++;
                }
            }
        }
        Map<DayOfWeek, List<FundData>> dayOfWeekMap = new HashMap<>();
        Map<Integer, List<FundData>> dayOfMonthMap = new HashMap<>();
        for (FundData fundData : list) {
            LocalDateTime netValueDate = fundData.getNetValueDate();
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
                    .collect(Collectors.summarizingDouble(Double::valueOf));
            weekMap.put(entry.getKey(), weekList.getAverage());
        }
        for (Map.Entry<Integer, List<FundData>> entry : dayOfMonthMap.entrySet()) {
            DoubleSummaryStatistics weekList = entry.getValue().stream()
                    .map(FundData::getIncreaseRateDay)
                    .collect(Collectors.summarizingDouble(Double::valueOf));
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
}
