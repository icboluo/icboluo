package com.icboluo.service;

import com.icboluo.entity.PlayerLevel;
import com.icboluo.mapper.PlayerLevelMapper;
import com.icboluo.service.PlayerLevelService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (PlayerLevel)表服务实现类
 *
 * @author makejava
 * @since 2022-03-13 12:17:47
 */
@Service
public class PlayerLevelServiceImpl implements PlayerLevelService {
    @Resource
    private PlayerLevelMapper playerLevelMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PlayerLevel queryById(Integer id) {
        return playerLevelMapper.queryById(id);
    }
}
