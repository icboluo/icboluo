package com.icboluo.service.impl;

import com.icboluo.entity.FundInfo;
import com.icboluo.mapper.FundInfoMapper;
import com.icboluo.service.FundInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public FundInfo queryById(String id) {
        return this.fundInfoMapper.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param fundInfo 实例对象
     * @return 实例对象
     */
    @Override
    public FundInfo insert(FundInfo fundInfo) {
        this.fundInfoMapper.insert(fundInfo);
        return fundInfo;
    }

    /**
     * 修改数据
     *
     * @param fundInfo 实例对象
     * @return 实例对象
     */
    @Override
    public FundInfo update(FundInfo fundInfo) {
        this.fundInfoMapper.update(fundInfo);
        return this.queryById(fundInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.fundInfoMapper.deleteById(id) > 0;
    }
}
