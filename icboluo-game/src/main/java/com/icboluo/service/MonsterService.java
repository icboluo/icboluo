package com.icboluo.service;

import com.icboluo.entity.Monster;

import java.util.List;

/**
 * 玩家(Monster)表服务接口
 *
 * @author makejava
 * @since 2022-03-13 02:52:18
 */
public interface MonsterService {

    Monster nextMonster();

    List<Monster> allMonster();
}
