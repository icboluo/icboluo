package com.icboluo.service.impl;

import com.icboluo.entity.Monster;
import com.icboluo.mapper.MonsterMapper;
import com.icboluo.service.MonsterService;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.RandomHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 玩家(Monster)表服务实现类
 *
 * @author makejava
 * @since 2022-03-13 02:52:53
 */
@Service
public class MonsterServiceImpl implements MonsterService {
    @Resource
    private MonsterMapper monsterMapper;

    @Override
    public Monster nextMonster() {
        List<Monster> list = monsterMapper.selectAll();
        if (list.size() >= 10) {
            throw new IcBoLuoException("monster more than 10, please attack before");
        }
        int attack = RandomHelper.interval(4, 8);
        int maxBlood = RandomHelper.interval(15, 30);
        Monster monster = new Monster();
        monster.setAttack(attack);
        monster.setBlood(maxBlood);
        monster.setMaxBlood(maxBlood);
        monsterMapper.insert(monster);
        return monster;
    }

    @Override
    public List<Monster> allMonster() {
        return monsterMapper.selectAll();
    }
}