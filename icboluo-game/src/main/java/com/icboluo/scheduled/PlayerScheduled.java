package com.icboluo.scheduled;

import com.icboluo.entity.CultivationCareer;
import com.icboluo.entity.DiePlayer;
import com.icboluo.entity.Player;
import com.icboluo.mapper.DiePlayerMapper;
import com.icboluo.service.CultivationCareerService;
import com.icboluo.service.PlayerService;
import com.icboluo.util.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author icboluo
 * @since 2022-03-14 22:41
 */
@Component
public class PlayerScheduled {
    @Resource
    private PlayerService playerService;
    @Resource
    private DiePlayerMapper diePlayerMapper;
    @Resource
    private CultivationCareerService cultivationCareerService;

    /**
     * 时间轴
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void timeLine() {
        List<Player> playerList = playerService.selectAll();
        CultivationCareer cultivationCareer = new CultivationCareer();
        for (Player player : playerList) {
            cultivationCareer.setPlayerId(player.getId());
            if (player.getAge() >= 100) {
                DiePlayer diePlayer = BeanUtil.copyProperties(player, DiePlayer::new);
                diePlayerMapper.insert(diePlayer);
                playerService.deleteById(player.getId());
                cultivationCareer.setOper("you are 100 years old, you are die");
            } else {
                player.setAge(player.getAge() + 1);
                int blood = Math.min(player.getMaxBlood(), player.getBlood() + 10);
                player.setBlood(blood);
                playerService.update(player);
                cultivationCareer.setOper("time flies, another year has passed");
            }
            cultivationCareerService.add(cultivationCareer);
        }
    }
}
