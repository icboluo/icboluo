package com.icboluo.object.businessobject;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author icboluo
 * @date 2020/11/20 22:15
 */
@Data
public class Student {

    @ExcelProperty
    private String id;

    @ExcelProperty
    private String code;

    @ExcelProperty
    private String name;

    @ExcelProperty
    private Integer age;




}
