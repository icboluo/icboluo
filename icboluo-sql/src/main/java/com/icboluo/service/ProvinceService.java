package com.icboluo.service;

import com.icboluo.entity.Province;

import java.util.List;

/**
 * (Province)表服务接口
 *
 * @author icboluo
 * @since 2021-09-24 20:25:17
 */
public interface ProvinceService {

    /**
     * 通过ID查询单条数据
     *
     * @param proId 主键
     * @return 实例对象
     */
    Province queryById(Integer proId);

    List<Province> selectAll();
}
