package com.icboluo.controller;

import com.icboluo.annotation.ResponseResult;
import com.icboluo.object.vo.FundInfoVO;
import com.icboluo.service.FundInfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (FundInfo)表控制层
 *
 * @author icboluo
 * @since 2021-06-08 00:57:19
 */
@RestController
@RequestMapping("/fundInfo")
@CrossOrigin(origins = "*", maxAge = 3600)
@ResponseResult
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
    @GetMapping("/fundInfoInit")
    public FundInfoVO fundInfoInit(String id) {
        return fundInfoService.fundInfoInit(id);
    }
}
