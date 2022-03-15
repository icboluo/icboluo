package com.icboluo.service.impl;

import com.icboluo.entity.*;
import com.icboluo.mapper.CultivationCareerMapper;
import com.icboluo.mapper.DiePlayerMapper;
import com.icboluo.mapper.MonsterMapper;
import com.icboluo.mapper.PlayerMapper;
import com.icboluo.pojo.PlayerVO;
import com.icboluo.service.PlayerLevelService;
import com.icboluo.service.PlayerService;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.NameUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    @Resource
    private MessageSource messageSource;

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
                String message = messageSource.getMessage("no.find.the.role", null, LocaleContextHolder.getLocale());
                throw new IcBoLuoException(message);
            }
            throw new IcBoLuoException("cur.role.already.die");
        }
        PlayerVO view = BeanHelper.copyProperties(player, PlayerVO::new);
        PlayerLevel playerLevel = playerLevelService.queryById(player.getLevel());
        view.setLevel(playerLevel.getLevel());
        view.setCurTotalExperience(playerLevel.getExperience());

        if (player.getAge() >= playerLevel.getAge()) {
            throw new IcBoLuoException("you.are.dead");
        }
        return view;
    }

    @Override
    public void attack(Integer playerId, Integer monsterId) {
        Monster monster = monsterMapper.queryById(monsterId);
        Player player = playerMapper.queryById(playerId);
        if (player == null || monster == null) {
            return;
        }
        boolean attack = attack(player, monster);
        if (!attack) {
            return;
        }
        experienceHand(player, monster);
        playerMapper.updateByPrimaryKeySelective(player);
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

        player.setName(NameUtils.getCnName());
        playerMapper.insert(player);

        CultivationCareer cultivationCareer = new CultivationCareer();
        cultivationCareer.setPlayerId(player.getId());
        cultivationCareer.setOper("Games start");
        cultivationCareerMapper.insert(cultivationCareer);
        return player.getId();
    }

    /**
     * 攻击
     *
     * @param player  玩家
     * @param monster 怪物
     * @return 是否造成攻击行为
     */
    private boolean attack(Player player, Monster monster) {
        Integer plaBlo = player.getBlood();
        if (plaBlo <= 0) {
            return false;
        }
        Integer monBlo = monster.getBlood();
        if (monBlo <= 0) {
            monsterMapper.deleteById(monster.getId());
            return false;
        }

        int plaAtt = player.getAttack() + player.getLevel();
        Integer monAtt = monster.getAttack();

//        战斗之后怪物的血量
        int aftMonBlo = monBlo - plaAtt;
        player.setBlood(plaBlo - monAtt);
        monster.setBlood(aftMonBlo);

        player.setAge(player.getAge() + 1);
        return true;
    }

    /**
     * 经验处理
     *
     * @param player  玩家
     * @param monster 怪物
     */
    private void experienceHand(Player player, Monster monster) {
        CultivationCareer cultivationCareer = new CultivationCareer();
        cultivationCareer.setPlayerId(player.getId());
        if (monster.getBlood() > 0) {
            monsterMapper.updateByPrimaryKeySelective(monster);
            cultivationCareer.setOper("You spend 1 year attacking a monster");
        } else {
            monsterMapper.deleteById(monster.getId());
            getExperience(player, 1, cultivationCareer);
        }
        cultivationCareerMapper.insert(cultivationCareer);
    }

    private void getExperience(Player player, int experience, CultivationCareer cultivationCareer) {
        PlayerLevel playerLevel = playerLevelService.queryById(player.getLevel());
//        过量经验
        int excessExperience = player.getExperience() + experience - playerLevel.getExperience();
        if (excessExperience >= 0) {
            player.setExperience(excessExperience);
            player.setLevel(player.getLevel() + 1);
            player.setAttack(player.getAttack() + playerLevel.getIncreaseAttack());
            player.setMaxBlood(player.getMaxBlood() + playerLevel.getIncreaseBlood());
            cultivationCareer.setOper("you killed a monster, and you upgraded");
            if (player.getLevel() > 4) {
                throw new IcBoLuoException("you have reached the highest level");
            }
        } else {
            player.setExperience(player.getExperience() + experience);
            cultivationCareer.setOper("you killed a monster");
        }
        player.setTotalExperience(player.getTotalExperience() + experience);
    }
}
