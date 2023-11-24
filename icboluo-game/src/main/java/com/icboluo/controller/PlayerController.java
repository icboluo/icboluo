package com.icboluo.controller;

import com.icboluo.entity.Monster;
import com.icboluo.entity.Player;
import com.icboluo.object.IdName;
import com.icboluo.pojo.PlayerVO;
import com.icboluo.service.MonsterService;
import com.icboluo.service.PlayerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("exhibit")
    public PlayerVO queryById(@RequestParam Integer id) {
        return playerService.queryById(id);
    }

    /**
     * 我的角色
     *
     * @return 我的角色列表
     */
    @GetMapping("myRole")
    public List<IdName> myRole() {
        List<Player> players = playerService.selectAll();
        return players.stream()
                .map(player -> new IdName(player.getId(), player.getName()))
                .toList();
    }

    @GetMapping("nextMonster")
    public Monster nextMonster() {
        return monsterService.nextMonster();
    }

    @GetMapping("attack")
    public void attack(@RequestParam Integer playerId, @RequestParam Integer monsterId) {
        playerService.attack(playerId, monsterId);
    }

    @GetMapping("allMonster")
    public List<Monster> allMonster() {
        return monsterService.allMonster();
    }

    @GetMapping("addRole")
    public void addRole() {
        playerService.addRole();
    }
}

