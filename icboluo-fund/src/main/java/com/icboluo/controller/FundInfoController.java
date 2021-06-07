package com.icboluo.controller;

import com.icboluo.entity.FundInfo;
import com.icboluo.service.FundInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FundInfo)表控制层
 *
 * @author icboluo
 * @since 2021-06-08 00:57:19
 */
@RestController
@RequestMapping("/fundInfo")
public class FundInfoController {
    /**
     * 服务对象
     */
    @Resource
    private FundInfoService fundInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public FundInfo selectOne(String id) {
        return this.fundInfoService.queryById(id);
    }

}
