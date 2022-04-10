package com.icboluo.object.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.icboluo.annotation.Config;
import com.icboluo.annotation.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
}
