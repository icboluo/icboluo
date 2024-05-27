package com.icboluo.controller;

import com.icboluo.entity.Monster;
import com.icboluo.entity.Player;
import com.icboluo.object.IdName;
import com.icboluo.object.co.AttackCo;
import com.icboluo.object.vo.PlayerVO;
import com.icboluo.service.MonsterService;
import com.icboluo.service.PlayerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 玩家(Player)表控制层
 *
 * @author makejava
 * @since 2022-03-13 01:38:06
 */
@RestController
@RequestMapping("player")
public class PlayerController {
    /**
     * 服务对象
     */
    @Resource
    private PlayerService playerService;

    @Resource
    private MonsterService monsterService;

    /**
     * 展示
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("exhibit")
    public PlayerVO queryById(@RequestBody Integer id) {
        return playerService.queryById(id);
    }

    /**
     * 我的角色
     *
     * @return 我的角色列表
     */
    @PostMapping("myRole")
    public List<IdName> myRole() {
        List<Player> players = playerService.selectAll();
        return players.stream()
                .map(player -> new IdName(player.getId(), player.getName()))
                .toList();
    }

    @PostMapping("nextMonster")
    public Monster nextMonster() {
        return monsterService.nextMonster();
    }

    @PostMapping("attack")
    public void attack(@RequestBody AttackCo client) {
        playerService.attack(client.getPlayerId(), client.getMonsterId());
    }

    @PostMapping("allMonster")
    public List<Monster> allMonster() {
        return monsterService.allMonster();
    }

    @PostMapping("addRole")
    public void addRole() {
        playerService.addRole();
    }
}

