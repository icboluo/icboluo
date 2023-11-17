package com.icboluo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icboluo.common.redis.RedisHash;
import com.icboluo.common.redis.RedisList;
import com.icboluo.common.redis.RedisString;
import com.icboluo.entity.*;
import com.icboluo.mapper.DiePlayerMapper;
import com.icboluo.mapper.MonsterMapper;
import com.icboluo.mapper.PlayerMapper;
import com.icboluo.pojo.PlayerVO;
import com.icboluo.service.impl.StudentServiceImpl;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.IcBoLuoI18nException;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private CultivationCareerService cultivationCareerService;
    @Resource
    private RedisList<Player> redisList;
    @Resource
    private RedisHash<Object> redisHash;
    @Resource
    private RedisString<String> redisString;
    @Resource
    private RedisTemplate redisTemplate;

    private StudentService studentService = new StudentServiceImpl();

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
                throw new IcBoLuoException("no.find.the.role");
            }
            throw new IcBoLuoI18nException("cur.role.already.die");
        }
        PlayerVO view = BeanUtil.copyProperties(player, PlayerVO::new);
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

        player.setName(studentService.generateZhName());
        playerMapper.insert(player);

        CultivationCareer cultivationCareer = new CultivationCareer();
        cultivationCareer.setPlayerId(player.getId());
        cultivationCareer.setOper("Games start");
        cultivationCareerService.add(cultivationCareer);
        return player.getId();
    }

    /**
     * TODO,期望实现redis缓存，不知道使用哪一种数据结构更合适一点，redisHash使用起来非常困难
     *
     * @return
     */
    @Override
    public List<Player> selectAll() {
        // 对于分组查询，key需要增加冒号
        // 针对于项目启动需要同步进入redis的数据（初始化同步），需要增加同步功能
        // 不要试图完全相信redis，要有一定的恢复机制（这个会造成性能的一些损失
        String key = "game:player:";
        if (Boolean.TRUE.equals(redisString.containsKey("game:player"))) {
            ScanOptions build = ScanOptions.scanOptions().match(key + "*").count(10L).build();
            Cursor scan = redisTemplate.scan(build);
            List<Player> cacheList = new ArrayList<>();
            while (scan.hasNext()) {
                String next = (String) scan.next();
                Map<String, Object> hmget = redisHash.hmget(next);
                Player player = JSON.parseObject(JSON.toJSONString(hmget), Player.class);
                cacheList.add(player);
            }
            return cacheList;
        } else {
            List<Player> dbList = playerMapper.selectAll();
            for (Player db : dbList) {
                JSONObject jo = JSON.parseObject(JSON.toJSONString(db));
                redisHash.hmset(key + db.getId(), jo);
            }
            redisString.set("game:player", "true");
            return dbList;
        }
    }

    @Override
    public void update(Player player) {
        JSONObject jo = JSON.parseObject(JSON.toJSONString(player));
        redisHash.hmset("game:player:" + player.getId(), jo);
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
        cultivationCareerService.add(cultivationCareer);
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
