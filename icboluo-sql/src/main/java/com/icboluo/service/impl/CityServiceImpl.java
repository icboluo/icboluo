package com.icboluo.service.impl;

import com.icboluo.entity.City;
import com.icboluo.mapper.CityMapper;
import com.icboluo.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (City)表服务实现类
 *
 * @author icboluo
 * @since 2023-07-07 06:01:42
 */
@Service
public class CityServiceImpl implements CityService {
    @Resource
    private CityMapper cityMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param cityId 主键
     * @return 实例对象
     */
    @Override
    public City queryById(Integer cityId) {
        return cityMapper.queryById(cityId);
    }
}
