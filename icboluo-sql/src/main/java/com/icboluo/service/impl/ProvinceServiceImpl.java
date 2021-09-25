package com.icboluo.service.impl;

import com.icboluo.entity.Province;
import com.icboluo.mapper.ProvinceMapper;
import com.icboluo.service.ProvinceService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Province)表服务实现类
 *
 * @author icboluo
 * @since 2021-09-24 20:25:18
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Resource
    private ProvinceMapper provinceMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param proId 主键
     * @return 实例对象
     */
    @Override
    public Province queryById(Integer proId) {
        return provinceMapper.queryByIdsd(proId);
    }
}
