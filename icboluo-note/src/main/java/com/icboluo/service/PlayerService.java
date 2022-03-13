package com.icboluo.service;

import com.icboluo.entity.Monster;
import com.icboluo.entity.Player;

/**
 * 玩家(Player)表服务接口
 *
 * @author makejava
 * @since 2022-03-13 01:34:50
 */
public interface PlayerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Player queryById(Integer id);

    Monster attack(Integer id, Monster monster);
}
