package com.icboluo.service;

import com.icboluo.entity.DiePlayer;

/**
 * 玩家(DiePlayer)表服务接口
 *
 * @author icboluo
 * @since 2022-03-14 23:11:11
 */
public interface DiePlayerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DiePlayer queryById(Integer id);
}
