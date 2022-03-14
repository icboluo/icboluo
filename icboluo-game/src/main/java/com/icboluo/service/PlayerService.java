package com.icboluo.service;


import com.icboluo.pojo.PlayerVO;

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
    PlayerVO queryById(Integer id);

    void attack(Integer playerId, Integer monsterId);

    int startGame();
}
