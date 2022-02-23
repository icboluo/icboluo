package com.icboluo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.object.businessobject.Student;
import com.icboluo.object.clientobject.RowCO;
import com.icboluo.service.impl.ExcelService;
import com.icboluo.util.ArrayHelper;
import com.icboluo.util.ExcelHelper;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.ValidateUtil;
import com.icboluo.util.listenter.RowDataListener;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/importExcel")
    public void importExcel(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mf = multipartRequest.getFile("file");
        if (mf == null) {
            return;
        }
        validateSuffix(mf);
        try (InputStream is = mf.getInputStream();) {
            ExcelReader er = EasyExcel.read(is).build();
            RowDataListener listener = new RowDataListener();
            ReadSheet rs = EasyExcel.readSheet(0).head(RowCO.class).headRowNumber(0).registerReadListener(listener).build();
            er.read(rs);
            List<RowCO> list = listener.list;
            String[][] arr = validateContext(list);
            removeErrData(list, arr);
            list.forEach(System.out::println);
        } catch (Exception e) {

        }
    }

    private void removeErrData(List<RowCO> list, String[][] arr) {
        for (int i = list.size() - 1; i >= 0; i--) {
            String[] row = arr[i - 3];
            boolean allEleIsEmpty = ArrayHelper.allEleIsEmpty(row);
            if (!allEleIsEmpty) {
                list.remove(i);
            }
        }
    }

    private String[][] validateContext(List<RowCO> list) {
        for (int i = 0; i < list.size(); i++) {
            RowCO row = list.get(i);
            Class<? extends RowCO> cla = row.getClass();
            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                Set<ConstraintViolation<RowCO>> constraintViolations = ValidateUtil.validateProperty(row, name);
                if (!CollectionUtils.isEmpty(constraintViolations)) {
                    List<ConstraintViolation<RowCO>> collect = constraintViolations.stream()
                            .sorted().toList();
                    String msg = collect.get(0).getMessage();
                } else {
                    String msg = validateOther();
                }
            }
        }
        return null;
    }

    private Comparator<ConstraintViolation<RowCO>> sort() {
        Class[] claArr = {NotNull.class, NotEmpty.class};
        Comparator<ConstraintViolation<RowCO>> comparator = (fir, sec) -> {

        };
        return comparator;
    }

    private String validateOther() {
    }

    private void validateSuffix(MultipartFile mf) {
        String of = mf.getOriginalFilename();
        if (of != null && !of.endsWith("xlsx")) {
            throw new IcBoLuoException();
        }
    }
}
