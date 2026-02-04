package com.icboluo.service.impl;

import com.icboluo.entity.Monster;
import com.icboluo.mapper.MonsterMapper;
import com.icboluo.service.MonsterService;
import com.icboluo.service.StudentService;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

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
    @Resource
    private MessageSource messageSource;

    private StudentService studentService = new StudentServiceImpl();

    @Override
    public Monster nextMonster() {
        List<Monster> list = monsterMapper.selectList(null);
        if (list.size() >= 10) {
            throw new IcBoLuoException("monster.more.than.10.please.attack.before");
        }
        int attack = RandomUtil.interval(4, 8);
        int maxBlood = RandomUtil.interval(15, 30);
        Monster monster = new Monster();
        monster.setAttack(attack);
        monster.setBlood(maxBlood);
        monster.setMaxBlood(maxBlood);
        monster.setName(studentService.generateZhName());
        monsterMapper.insert(monster);
        return monster;
    }

    @Override
    public List<Monster> allMonster() {
        return monsterMapper.selectList(null);
    }
}
