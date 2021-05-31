package com.icboluo.controller;

import com.icboluo.common.ResponseResult;
import com.icboluo.entity.FundAttention;
import com.icboluo.service.FundAttentionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FundAttention)表控制层
 *
 * @author icboluo
 * @since 2021-05-27 23:05:01
 */
@RestController
@RequestMapping("/fundAttention")
@CrossOrigin(origins = "*", maxAge = 3600)
@ResponseResult
public class FundAttentionController {
    /**
     * 服务对象
     */
    @Resource
    private FundAttentionService fundAttentionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public FundAttention selectOne(String id) {
        return this.fundAttentionService.queryById(id);
    }

}
