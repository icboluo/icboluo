package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.service.FundAttentionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * (FundAttention)表控制层
 *
 * @author icboluo
 * @since 2021-05-27 23:05:01
 */
@RestController
@RequestMapping("fundAttention")
public class FundAttentionController {

    @Resource
    private FundAttentionService fundAttentionService;

    @PostMapping("init")
    public PageInfo<FundAttentionVO> init(@RequestBody FundAttentionQuery query) {
        return fundAttentionService.init(query);
    }

    @PostMapping("delete")
    public void delete(@RequestBody String id) {
        fundAttentionService.delete(id);
    }
}
