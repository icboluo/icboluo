package com.icboluo.controller;


import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.object.businessobject.Student;
import com.icboluo.service.ExcelService;
import com.icboluo.util.ExcelHelper;
import com.icboluo.util.R;
import com.icboluo.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    @ApiOperation(value = "将数据库excel文档读成建表语句")
    public Response read() {
        excelService.readDbDocument(excelPath, readExcelEntity.getSheetName());
        return R.correct();
    }

    @GetMapping("/write")
    @ApiOperation(value = "将数据库写成excel文档")
    public Response write() {
        excelService.write(writeExcelEntity.getDatabase(), writeExcelEntity.getTableName());
        return R.correct();
    }

    @GetMapping("/write2")
    @ApiOperation(value = "将数据库写成excel文档")
    public Response write2() {
        excelService.write2(writeExcelEntity.getDatabase(), writeExcelEntity.getTableName());
        return R.correct();
    }

    @GetMapping("www")
    public void www(HttpServletResponse response) {
        List<Student> data = new ArrayList<>();
        Student student = new Student();
        student.setAge(18);
        student.setId("22");
        data.add(student);
        List<String> strings = Arrays.asList("name", "id", "age");
        ExcelHelper.exportExcel(response, strings, Student.class, data);
    }
}
