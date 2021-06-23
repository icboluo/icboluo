package com.icboluo.service.impl;

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


    @Override
    public List<FundAttentionVO> init(FundAttentionQuery query) {
        return fundAttentionMapper.selectByQuery(query);
    }

    @Override
    public void delete(String id) {
        fundAttentionMapper.deleteById(id);
        fundAsyncRecordMapper.deleteById(id);
        fundDataMapper.deleteByFundId(id);
        fundInfoMapper.deleteById(id);
    }
}
