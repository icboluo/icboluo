package com.icboluo.service.impl;

import com.icboluo.entity.Monster;
import com.icboluo.entity.Player;
import com.icboluo.mapper.MonsterMapper;
import com.icboluo.mapper.PlayerMapper;
import com.icboluo.service.PlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 玩家(Player)表服务实现类
 *
 * @author makejava
 * @since 2022-03-13 01:35:59
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Resource
    private PlayerMapper playerMapper;
    @Resource
    private MonsterMapper monsterMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Player queryById(Integer id) {
        Player player = playerMapper.queryById(id);
        player.setAttack(player.getAttack() + player.getLevel());
        player.setBlood(player.getBlood() + player.getLevel() * 5);
        return player;
    }

    @Override
    public Monster attack(Integer id, Monster monster) {
        Integer monBlo = monster.getBlood();
        if (monBlo <= 0) {
            monsterMapper.deleteById(monster.getId());
            return monster;
        }
        Player player = playerMapper.queryById(id);

        Integer plaAtt = player.getAttack();
        if (player.getBlood() > 0) {
            Integer monAtt = monster.getAttack();
            Integer plaBlo = player.getBlood();
            player.setBlood(plaBlo - monAtt);
            monster.setBlood(monBlo - plaAtt);
        }
        if (monster.getBlood() > 0) {
            monsterMapper.updateByPrimaryKeySelective(monster);
        } else {
            monsterMapper.deleteById(monster.getId());
            player.setTotalExperience(player.getExperience() + 1);
            if (player.getExperience() >= 4) {
                player.setExperience(player.getExperience() - 4);
                player.setLevel(player.getLevel() + 1);
            } else {
                player.setExperience(player.getExperience() + 1);
            }
        }
        playerMapper.updateByPrimaryKeySelective(player);
        return monster;
    }
}
