package com.icboluo.service.impl;

import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundAsyncRecordMapper;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.mapper.FundInfoMapper;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.service.FundAttentionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public List<FundAttentionVO> init(FundAttentionQuery query) {
        List<FundAttentionVO> list = fundAttentionMapper.selectByQuery(query);
        List<FundData> fundDataList = fundDataMapper.selectAll();
        for (FundAttentionVO vo : list) {
            List<FundData> collect = fundDataList.stream()
                    .filter(fundData -> fundData.getFundId().equals(vo.getId()))
                    .sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate()))
                    .collect(Collectors.toList());
            LocalDate now = LocalDate.now();
            Map<Integer, BigDecimal> avgMap = new HashMap<>();
            for (int i = 0; i < 5; i++) {
                LocalDate beforeDate = now.minusYears(i + 1);
                Double val = collect.stream()
                        .filter(fundData -> fundData.getNetValueDate().compareTo(beforeDate) >= 0)
                        .map(FundData::getIncreaseRateDay)
                        .filter(Objects::nonNull)
                        .collect(Collectors.averagingDouble(BigDecimal::doubleValue));
                avgMap.put(i, BigDecimal.valueOf(val));
            }
            vo.setAvgMap(avgMap);
            Double tenAvg = collect.subList(0, 10).stream()
                    .map(FundData::getIncreaseRateDay)
                    .filter(Objects::nonNull)
                    .collect(Collectors.averagingDouble(BigDecimal::doubleValue));
            vo.setTenAvg(BigDecimal.valueOf(tenAvg));
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
