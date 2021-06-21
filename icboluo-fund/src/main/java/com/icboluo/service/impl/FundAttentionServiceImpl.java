package com.icboluo.service.impl;

import com.icboluo.entity.FundAttention;
import com.icboluo.mapper.FundAsyncRecordMapper;
import com.icboluo.mapper.FundAttentionMapper;
import com.icboluo.mapper.FundDataMapper;
import com.icboluo.mapper.FundInfoMapper;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.service.FundAttentionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FundAttention queryById(String id) {
        return this.fundAttentionMapper.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param fundAttention 实例对象
     * @return 实例对象
     */
    @Override
    public FundAttention insert(FundAttention fundAttention) {
        this.fundAttentionMapper.insert(fundAttention);
        return fundAttention;
    }

    /**
     * 修改数据
     *
     * @param fundAttention 实例对象
     * @return 实例对象
     */
    @Override
    public FundAttention update(FundAttention fundAttention) {
        this.fundAttentionMapper.update(fundAttention);
        return this.queryById(fundAttention.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.fundAttentionMapper.deleteById(id) > 0;
    }

    @Override
    public List<FundAttentionVO> init(FundAttentionQuery query) {
        return fundAttentionMapper.selectByQuery(query);
    }

    @Override
    public void delete(String id) {
        deleteById(id);
        fundAsyncRecordMapper.deleteById(id);
        fundDataMapper.deleteByFundId(id);
        fundInfoMapper.deleteById(id);
    }
}
