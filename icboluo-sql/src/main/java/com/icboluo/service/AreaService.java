package com.icboluo.service;

import com.icboluo.entity.Area;

/**
 * (Area)表服务接口
 *
 * @author icboluo
 * @since 2023-07-07 06:01:20
 */
public interface AreaService {

    /**
     * 通过ID查询单条数据
     *
     * @param areId 主键
     * @return 实例对象
     */
    Area queryById(Integer areId);
}
