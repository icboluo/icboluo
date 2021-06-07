package com.icboluo.service;

import com.icboluo.entity.FundInfo;

import java.util.List;

/**
 * (FundInfo)表服务接口
 *
 * @author icboluo
 * @since 2021-06-08 00:57:18
 */
public interface FundInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FundInfo queryById(String id);

    /**
     * 新增数据
     *
     * @param fundInfo 实例对象
     * @return 实例对象
     */
    FundInfo insert(FundInfo fundInfo);

    /**
     * 修改数据
     *
     * @param fundInfo 实例对象
     * @return 实例对象
     */
    FundInfo update(FundInfo fundInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
