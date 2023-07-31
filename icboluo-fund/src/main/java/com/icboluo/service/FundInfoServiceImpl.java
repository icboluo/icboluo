package com.icboluo.service;

import com.icboluo.entity.FundInfo;
import com.icboluo.mapper.FundInfoMapper;
import com.icboluo.object.vo.FundInfoVO;
import com.icboluo.service.FundInfoService;
import com.icboluo.util.BeanHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (FundInfo)表服务实现类
 *
 * @author icboluo
 * @since 2021-06-08 00:57:18
 */
@Service
public class FundInfoServiceImpl implements FundInfoService {
    @Resource
    private FundInfoMapper fundInfoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FundInfoVO fundInfoInit(String id) {
        FundInfo fundInfo = fundInfoMapper.queryById(id);
        return BeanHelper.copyProperties(fundInfo, FundInfoVO.class);
    }
}
