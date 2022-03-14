package com.icboluo.scheduled;

import com.icboluo.entity.CultivationCareer;
import com.icboluo.entity.DiePlayer;
import com.icboluo.entity.Player;
import com.icboluo.mapper.CultivationCareerMapper;
import com.icboluo.mapper.DiePlayerMapper;
import com.icboluo.mapper.PlayerMapper;
import com.icboluo.util.BeanHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author icboluo
 * @date 2022-03-14 22:41
 */
@Component
public class PlayerScheduled {

    @Resource
    private PlayerMapper playerMapper;
    @Resource
    private DiePlayerMapper diePlayerMapper;
    @Resource
    private CultivationCareerMapper cultivationCareerMapper;

    /**
     * 时间轴
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void timeLine() {
        List<Player> playerList = playerMapper.selectAll();
        CultivationCareer cultivationCareer = new CultivationCareer();
        for (Player player : playerList) {
            cultivationCareer.setPlayerId(player.getId());
            if (player.getAge() >= 100) {
                DiePlayer diePlayer = BeanHelper.copyProperties(player, DiePlayer::new);
                diePlayerMapper.insert(diePlayer);
                playerMapper.deleteById(player.getId());
                cultivationCareer.setOper("you are 100 years old, you are die");
            } else {
                player.setAge(player.getAge() + 1);
                int blood = Math.min(player.getMaxBlood(), player.getBlood() + 10);
                player.setBlood(blood);
                playerMapper.updateById(player);
                cultivationCareer.setOper("time flies, another year has passed");
            }
            cultivationCareerMapper.insert(cultivationCareer);
        }
    }
}
