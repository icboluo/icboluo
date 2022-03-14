package com.icboluo.controller;

import com.icboluo.annotation.ResponseResult;
import com.icboluo.entity.Monster;
import com.icboluo.pojo.PlayerVO;
import com.icboluo.service.MonsterService;
import com.icboluo.service.PlayerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 玩家(Player)表控制层
 *
 * @author makejava
 * @since 2022-03-13 01:38:06
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/player")
@ResponseResult
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
    @GetMapping("/exhibit")
    public PlayerVO queryById(Integer id) {
        return playerService.queryById(id);
    }

    @GetMapping("/nextMonster")
    public Monster nextMonster() {
        return monsterService.nextMonster();
    }

    @GetMapping("/attack")
    public void attack(Integer playerId, Integer monsterId) {
        playerService.attack(playerId, monsterId);
    }

    @GetMapping("/allMonster")
    public List<Monster> allMonster() {
        return monsterService.allMonster();
    }

    @GetMapping("/startGame")
    public int startGame() {
        return playerService.startGame();
    }
}

