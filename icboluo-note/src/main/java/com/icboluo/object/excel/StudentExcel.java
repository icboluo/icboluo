package com.icboluo.object.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author icboluo
 * @since 2022-04-05 13:40
 */
@Data
public class StudentExcel {

    @ExcelProperty(index = 0)
    private String code;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private String sex;

    @ExcelProperty(index = 3)
    private String age;
}
