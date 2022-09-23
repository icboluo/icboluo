package com.icboluo.service;

import com.github.pagehelper.PageInfo;
import com.icboluo.entity.CultivationCareer;

/**
 * 修仙生涯(CultivationCareer)表服务接口
 *
 * @author icboluo
 * @since 2022-03-15 00:50:25
 */
public interface CultivationCareerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CultivationCareer queryById(Integer id);

    PageInfo<CultivationCareer> cultivationCareer(Integer id);

    void add(CultivationCareer data);

    void sync();
}
