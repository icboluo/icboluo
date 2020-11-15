package com.icboluo.controller;


import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author icboluo
 */
@RestController
@RequestMapping("/excel")
@Api(tags = "mysql生成工具")
public class ExcelController {
    @Resource
    private ExcelService excelService;
    @Resource
    private ReadExcelEntity readExcelEntity;
    @Resource
    private WriteExcelEntity writeExcelEntity;
    /**
     * 将配置文件中的属性读取出来
     */
    @Value("${read-excel-entity.excel-path}")
    private String excelPath;

    @GetMapping("/read")
    @ApiOperation(value = "将excel文档读成建表语句")
    public void read() {
        excelService.read(excelPath, readExcelEntity.getSheetName());
    }

    @GetMapping("/write")
    @ApiOperation(value = "将数据库写成excel文档")
    public void write() {
        excelService.write(writeExcelEntity.getDatabase(), writeExcelEntity.getTableName());
    }

    @GetMapping("/read2")
    @ApiOperation(value = "将excel文档读成建表语句")
    public void read2() {
        excelService.read2(excelPath, readExcelEntity.getSheetName());
    }

    @GetMapping("/write2")
    @ApiOperation(value = "将数据库写成excel文档")
    public void write2(HttpServletResponse response) {
        excelService.write2(writeExcelEntity.getDatabase(), writeExcelEntity.getTableName());
    }
}
