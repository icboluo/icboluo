package com.icboluo.service.impl;

import com.icboluo.entity.FundAttention;
import com.icboluo.entity.FundData;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.object.vo.FundDataCalVO;
import com.icboluo.object.vo.FundDataVO;
import com.icboluo.service.FundDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.DoubleSummaryStatistics;
import java.util.List;
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
        return fundDataMapper.selectByQuery(query);
    }

    @Override
    public FundDataCalVO cal(String fundId) {
        FundAttention fundAttention = fundAttentionMapper.selectByFundIdDim(fundId);
        String id = fundAttention.getId();
        List<FundData> list = fundDataMapper.selectByFundId(id);
        DoubleSummaryStatistics summaryStatistics = list.stream()
                .map(FundData::getIncreaseRateDay)
                .collect(Collectors.summarizingDouble(Double::valueOf));
        return FundDataCalVO.builder()
                .count(summaryStatistics.getCount())
                .avg(summaryStatistics.getAverage())
                .min(summaryStatistics.getMin())
                .max(summaryStatistics.getMax())
                .build();
    }
}
