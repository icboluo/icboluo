package com.icboluo.service;

import com.icboluo.entity.Monster;

/**
 * 玩家(Monster)表服务接口
 *
 * @author makejava
 * @since 2022-03-13 02:52:18
 */
public interface MonsterService {

    void updateSelective(Monster monster);

    Monster nextMonster();
}
