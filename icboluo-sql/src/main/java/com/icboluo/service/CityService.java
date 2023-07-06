package com.icboluo.service;

import com.icboluo.entity.City;

/**
 * (City)表服务接口
 *
 * @author icboluo
 * @since 2023-07-07 06:01:42
 */
public interface CityService {

    /**
     * 通过ID查询单条数据
     *
     * @param cityId 主键
     * @return 实例对象
     */
    City queryById(Integer cityId);
}
