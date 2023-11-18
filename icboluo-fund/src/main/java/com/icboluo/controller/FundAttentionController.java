package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.service.FundAttentionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (FundAttention)表控制层
 *
 * @author icboluo
 * @since 2021-05-27 23:05:01
 */
@RestController
@RequestMapping("/fundAttention")
public class FundAttentionController {

    @Resource
    private FundAttentionService fundAttentionService;

    @GetMapping("/init")
    public PageInfo<FundAttentionVO> init(FundAttentionQuery query) {
        List<FundAttentionVO> list = fundAttentionService.init(query);
        return PageInfo.of(list);
    }

    @GetMapping("/delete")
    public void delete(String id) {
        fundAttentionService.delete(id);
    }
}
