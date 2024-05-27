package com.icboluo.controller;

import com.icboluo.entity.Area;
import com.icboluo.service.AreaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * (Area)表控制层
 *
 * @author icboluo
 * @since 2023-07-07 05:54:32
 */
@RestController
@RequestMapping("area")
public class AreaController {

    @Resource
    private AreaService areaService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Area queryById(@PathVariable("id") Integer id) {
        return areaService.queryById(id);
    }
}

