package com.icboluo.controller;

import com.icboluo.entity.FundInfo;
import com.icboluo.mapper.FundInfoMapper;
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
    @Resource
    private FundInfoMapper fundInfoMapper;

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

    @GetMapping("/add")
    public void add() {
        FundInfo fundInfo = new FundInfo();
        fundInfo.setId("111111");
        fundInfoMapper.insert(fundInfo);
    }

}
