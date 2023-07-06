package com.icboluo.controller;

import com.icboluo.entity.City;
import com.icboluo.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (City)表控制层
 *
 * @author icboluo
 * @since 2023-07-07 05:55:33
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Resource
    private CityService cityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public City queryById(@PathVariable("id") Integer id) {
        return cityService.queryById(id);
    }
}

