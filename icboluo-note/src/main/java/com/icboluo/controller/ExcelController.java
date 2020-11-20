package com.icboluo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.object.businessobject.Student;
import com.icboluo.service.ExcelService;
import com.icboluo.util.HttpHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @GetMapping("www")
    public void www(HttpServletResponse response) throws IOException {
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setAge(18);
        student.setId("22");
        list.add(student);
        List<String> strings = Arrays.asList("id", "code", "name");
        HttpHelper.writeDownloadData(response, "aaa.xlsx");
        ExcelWriter ew = EasyExcel.write(response.getOutputStream(), Student.class)
                .build();
        WriteSheet ws = EasyExcel.writerSheet(0, "aaa").build();
        ew.write(list, ws);
        ew.finish();
    }
}
