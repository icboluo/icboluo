package com.icboluo.controller;

import com.icboluo.entity.FundData;
import com.icboluo.service.FundDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FundData)表控制层
 *
 * @author icboluo
 * @since 2021-05-28 00:12:38
 */
@RestController
@RequestMapping("/fundData")
public class FundDataController {
    /**
     * 服务对象
     */
    @Resource
    private FundDataService fundDataService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public FundData selectOne(Long id) {
        return this.fundDataService.queryById(id);
    }

}
