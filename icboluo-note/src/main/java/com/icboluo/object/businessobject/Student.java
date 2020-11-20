package com.icboluo.object.businessobject;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author icboluo
 * @date 2020/11/20 22:15
 */
@Data
public class Student {

    @ExcelProperty(value = "name", index = 2)
    private String name;

    @ExcelProperty
    private Integer age;

    @ExcelProperty(value = "code", index = 1)
    private String code;

    @ExcelProperty(value = "id", index = 0)
    private String id;
}
