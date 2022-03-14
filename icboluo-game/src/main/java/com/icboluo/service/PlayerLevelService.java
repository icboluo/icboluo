package com.icboluo.service;

import com.icboluo.entity.PlayerLevel;

/**
 * (PlayerLevel)表服务接口
 *
 * @author makejava
 * @since 2022-03-13 12:17:47
 */
public interface PlayerLevelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PlayerLevel queryById(Integer id);
}
