package com.icboluo.service.impl;

import com.icboluo.entity.*;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.mapper.CultivationCareerMapper;
import com.icboluo.mapper.DiePlayerMapper;
import com.icboluo.mapper.MonsterMapper;
import com.icboluo.mapper.PlayerMapper;
import com.icboluo.pojo.PlayerVO;
import com.icboluo.service.PlayerLevelService;
import com.icboluo.service.PlayerService;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.IcBoLuoException;
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
    @Resource
    private PlayerLevelService playerLevelService;
    @Resource
    private DiePlayerMapper diePlayerMapper;
    @Resource
    private CultivationCareerMapper cultivationCareerMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PlayerVO queryById(Integer id) {
        Player player = playerMapper.queryById(id);
        if (player == null) {
            DiePlayer diePlayer = diePlayerMapper.queryById(id);
            if (diePlayer == null) {
                throw new IcBoLuoException(ReEnum.NO_FIND_THE_ROLE);
            }
            throw new IcBoLuoException(ReEnum.CUR_ROLE_ALREADY_DIE);
        }
        player.setAttack(player.getAttack() + player.getLevel());
        player.setBlood(player.getBlood() + player.getLevel() * 5);

        PlayerVO view = BeanHelper.copyProperties(player, PlayerVO::new);
        PlayerLevel playerLevel = playerLevelService.queryById(player.getLevel());
        view.setLevel(playerLevel.getLevel());

        if (player.getAge() > 100) {
            throw new IcBoLuoException("you are die");
        }
        return view;
    }

    @Override
    public void attack(Integer playerId, Integer monsterId) {
        Monster monster = monsterMapper.queryById(monsterId);
        Integer monBlo = monster.getBlood();
        if (monBlo <= 0) {
            monsterMapper.deleteById(monster.getId());
            return;
        }
        Player player = playerMapper.queryById(playerId);
        CultivationCareer cultivationCareer = new CultivationCareer();
        cultivationCareer.setPlayerId(player.getId());
        Integer plaAtt = player.getAttack();
        if (player.getBlood() > 0) {
            Integer monAtt = monster.getAttack();
            Integer plaBlo = player.getBlood();
            player.setBlood(plaBlo - monAtt);
            monster.setBlood(monBlo - plaAtt);
        }
        if (monster.getBlood() > 0) {
            monsterMapper.updateByPrimaryKeySelective(monster);
            player.setAge(player.getAge() + 1);
            cultivationCareer.setOper("You spend 1 year attacking a monster");
        } else {
            monsterMapper.deleteById(monster.getId());
            player.setTotalExperience(player.getExperience() + 1);
            if (player.getExperience() >= 4) {
                player.setExperience(player.getExperience() - 4);
                player.setLevel(player.getLevel() + 1);
                cultivationCareer.setOper("you killed a monster, and you upgraded");
            } else {
                player.setExperience(player.getExperience() + 1);
                cultivationCareer.setOper("you killed a monster");
            }
        }
        playerMapper.updateByPrimaryKeySelective(player);

        cultivationCareerMapper.insert(cultivationCareer);
    }

    @Override
    public int startGame() {
        Player player = new Player();
        player.setBlood(200);
        player.setMaxBlood(200);
        player.setAge(20);
        player.setAttack(10);
        player.setLevel(1);
        player.setExperience(0);
        player.setTotalExperience(0);
        playerMapper.insert(player);

        CultivationCareer cultivationCareer = new CultivationCareer();
        cultivationCareer.setPlayerId(player.getId());
        cultivationCareer.setOper("Games start");
        cultivationCareerMapper.insert(cultivationCareer);
        return player.getId();
    }
}
