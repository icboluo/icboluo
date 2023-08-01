package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.entity.CultivationCareer;
import com.icboluo.service.CultivationCareerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 修仙生涯(CultivationCareer)表控制层
 *
 * @author icboluo
 * @since 2022-03-15 00:50:25
 */
@RestController
@RequestMapping("/cultivationCareer")
public class CultivationCareerController {

    @Resource
    private CultivationCareerService cultivationCareerService;

    /**
     * 修仙生涯
     *
     * @param id 主键
     * @return 分页数据
     */
    @GetMapping("/cultivationCareer")
    public PageInfo<CultivationCareer> cultivationCareer(Integer id) {
        return cultivationCareerService.cultivationCareer(id);
    }
}

