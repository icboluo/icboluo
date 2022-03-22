package com.icboluo.service.impl;

import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundAsyncRecordMapper;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.mapper.FundInfoMapper;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.service.FundAttentionService;
import com.icboluo.service.FundDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (FundAttention)表服务实现类
 *
 * @author icboluo
 * @since 2021-05-27 23:05:00
 */
@Service
public class FundAttentionServiceImpl implements FundAttentionService {
    @Resource
    private FundAttentionMapper fundAttentionMapper;
    @Resource
    private FundInfoMapper fundInfoMapper;
    @Resource
    private FundDataMapper fundDataMapper;
    @Resource
    private FundAsyncRecordMapper fundAsyncRecordMapper;
    @Resource
    private FundDataService fundDataService;

    @Override
    public List<FundAttentionVO> init(FundAttentionQuery query) {
        List<FundAttentionVO> list = fundAttentionMapper.selectByQuery(query);
        List<FundData> fundDataList = fundDataMapper.selectAll();
//                        倒序是因为希望最近的数据在前面（因为是最重要的
        Map<String, List<FundData>> fundMap = fundDataList.stream()
                .collect(Collectors.groupingBy(FundData::getFundId, Collectors.collectingAndThen(Collectors.toList(), li -> li.stream()
                        .sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate())).toList())));
        for (FundAttentionVO view : list) {
            List<FundData> fundList = fundMap.get(view.getId());
            Map<Integer, BigDecimal> avgMap = fundDataService.yearAvg(fundList);
            view.setAvgMap(avgMap);
            BigDecimal tenAvg = fundDataService.last10DaysAvg(fundList);
            view.setTenAvg(tenAvg);
        }

        LocalDate startDate = query.getStartDate() == null ? LocalDate.now().minusYears(1) : query.getStartDate();
        LocalDate endDate = query.getEndDate() == null ? LocalDate.now() : query.getEndDate();
//            计算定投收益
        for (FundAttentionVO view : list) {
            List<FundData> fundList = fundMap.get(view.getId())
                    .stream()
                    .filter(fundData -> fundData.getNetValueDate().compareTo(startDate) >= 0)
                    .filter(fundData -> fundData.getNetValueDate().compareTo(endDate) <= 0)
                    .toList();
//            因并非立即生效，所以天数必须2天以上才有意义
            BigDecimal yesterday = BigDecimal.ZERO;
            int count = 0;
            for (int i = fundList.size() - 1; i >= 0; i--) {
                FundData fund = fundList.get(i);
                if (fund.getIncreaseRateDay() == null) {
                    continue;
                }
                BigDecimal today = yesterday.multiply(BigDecimal.valueOf(100).add(fund.getIncreaseRateDay())).divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_DOWN);
//                对于下一天而言，昨天的钱就是这样计算的
                yesterday = today.add(BigDecimal.valueOf(10000));
                count++;
            }
            if (count != 0) {
                view.setFixedInvestmentIncome(yesterday.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_DOWN));
                view.setTotalDay(count);
            }

//            亏损总收益
            yesterday = BigDecimal.ZERO;
            count = 0;
            for (int i = fundList.size() - 1; i >= 0; i--) {
                FundData fund = fundList.get(i);
                if (fund.getIncreaseRateDay() == null) {
                    continue;
                }
                yesterday = yesterday.multiply(BigDecimal.valueOf(100).add(fund.getIncreaseRateDay())).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING);
                if (fund.getIncreaseRateDay().compareTo(BigDecimal.ZERO) >= 0) {
                    continue;
                }
                yesterday = yesterday.add(BigDecimal.valueOf(10000));
                count++;
            }
            if (count != 0) {
                view.setLossInvestmentIncome(yesterday.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_DOWN));
                view.setLossTotalDay(count);
            }

//             亏损比率收益
            yesterday = BigDecimal.ZERO;
            BigDecimal totalMoney = BigDecimal.ZERO;
            for (int i = fundList.size() - 1; i >= 0; i--) {
                FundData fund = fundList.get(i);
                if (fund.getIncreaseRateDay() == null) {
                    continue;
                }
                yesterday = yesterday.multiply(BigDecimal.valueOf(100).add(fund.getIncreaseRateDay())).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING);
                if (fund.getIncreaseRateDay().compareTo(BigDecimal.ZERO) >= 0) {
                    continue;
                }
                BigDecimal addMoney = BigDecimal.valueOf(-10000).multiply(fund.getIncreaseRateDay());
                yesterday = yesterday.add(addMoney);
                totalMoney = totalMoney.add(addMoney);
            }
            if (totalMoney.compareTo(BigDecimal.ZERO) != 0) {
                view.setLossRatioIncome(yesterday.multiply(BigDecimal.valueOf(10000)).divide(totalMoney, 2, RoundingMode.HALF_DOWN));
            }


            yesterday = BigDecimal.ZERO;
            totalMoney = BigDecimal.ZERO;
            for (int i = fundList.size() - 1; i >= 0; i--) {
                FundData fund = fundList.get(i);
                if (fund.getIncreaseRateDay() == null) {
                    continue;
                }
                yesterday = yesterday.multiply(BigDecimal.valueOf(100).add(fund.getIncreaseRateDay())).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING);
                if (fund.getIncreaseRateDay().compareTo(BigDecimal.valueOf(-1)) >= 0) {
                    continue;
                }
                BigDecimal addMoney = BigDecimal.valueOf(-10000).multiply(fund.getIncreaseRateDay());
                yesterday = yesterday.add(addMoney);
                totalMoney = totalMoney.add(addMoney);
            }
            if (totalMoney.compareTo(BigDecimal.ZERO) != 0) {
                view.setBigLossIncome(yesterday.multiply(BigDecimal.valueOf(10000)).divide(totalMoney, 2, RoundingMode.HALF_DOWN));
            }
        }

        return list;
    }

    @Override
    public void delete(String id) {
        fundAttentionMapper.deleteById(id);
        fundAsyncRecordMapper.deleteById(id);
        fundDataMapper.deleteByFundId(id);
        fundInfoMapper.deleteById(id);
    }
}
