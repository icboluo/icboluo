package com.icboluo.object.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.icboluo.annotation.Config;
import com.icboluo.annotation.Date;
import com.icboluo.util.DateHelper;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-04-05 13:40
 */
@Data
public class StudentExcel {

    @Config
    @Length(max = 2)
    @ExcelProperty(index = 0)
    private String code;

    @Config
    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private String sex;

    @ExcelProperty(index = 3)
    private String age;

    @Date
    @ExcelProperty(index = 4)
    private String startDate;

    public static List<StudentExcel> generatorFactory(int quantity) {
        List<StudentExcel> list = new ArrayList<>();
        StudentExcel student1 = new StudentExcel();
        student1.setCode("001");
        student1.setName("李明");
        student1.setAge("15");
        student1.setSex("男");
        student1.setStartDate(DateHelper.getCurrentDateFormat());
        StudentExcel student2 = new StudentExcel();
        list.add(student1);
        list.add(student2);
        return list;
    }
}
