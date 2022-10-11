package com.icboluo.controller;


import com.alibaba.excel.EasyExcel;
import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.constant.FileRelativePathPre;
import com.icboluo.object.business.Student;
import com.icboluo.object.client.RowCO;
import com.icboluo.object.excel.StudentExcel;
import com.icboluo.service.impl.ExcelService;
import com.icboluo.util.ExcelHelper;
import com.icboluo.util.listenter.RowDataListener;
import com.icboluo.util.listenter.StudentListener;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

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
        LocalDateTime gmtStart = LocalDateTime.now();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mf = multipartRequest.getFile("data");
        RowDataListener listener = new RowDataListener();
        List<RowCO> read = ExcelHelper.read(mf, listener, RowCO.class);
        String[][] arr = ExcelHelper.validateContext(read);
        ExcelHelper.removeErrData(read, arr);
        read.forEach(System.out::println);
    }

    @GetMapping("/importStudent")
    public void importStudent(HttpServletRequest request) {
        LocalDateTime gmtStart = LocalDateTime.now();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mf = multipartRequest.getFile("data");
        StudentListener listener = new StudentListener();
        List<StudentExcel> read1 = ExcelHelper.read(mf, listener, StudentExcel.class);
        List<StudentExcel> read = StudentExcel.generatorFactory(2);
        String[][] arr = ExcelHelper.validateContext(read);
        ExcelHelper.removeErrData(read, arr);
        read.forEach(System.out::println);
    }

    @GetMapping("/exportExcel")
    public void exportExcel() {
        EasyExcel.write(FileRelativePathPre.NOTE + FileRelativePathPre.RESOURCES + "student.xlsx")
                .head(Student.class)
                .build();
    }

    @GetMapping("/writeStudentExcel")
    public void writeStudentExcel() {
        // 不可能仅仅把file加载到内存中，修改内存就可以持久化；还需要一个fos来手动持久化
        writeColor(EnumSet.allOf(HSSFColor.HSSFColorPredefined.class), HSSFColor.HSSFColorPredefined::getIndex, 3);
        writeColor(EnumSet.allOf(IndexedColors.class), IndexedColors::getIndex, 12);
    }

    @SneakyThrows
    private <E extends Enum<E>> void writeColor(EnumSet<E> allOf, Function<E, Short> getIndex, int firstRow) {
        File file = new File(FileRelativePathPre.NOTE + FileRelativePathPre.RESOURCES + "dir/student.xlsx");
        int setSize = allOf.size();
        int rowSize = 8;
        int rowNum = (setSize + rowSize - 1) / rowSize;
        try (Workbook workbook = WorkbookFactory.create(file); FileOutputStream fos = new FileOutputStream(file, true)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = firstRow; i < firstRow + rowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }
                for (int j = 0; j < rowSize; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        cell = row.createCell(j);
                    }
                    CellStyle cellStyle = workbook.createCellStyle();
                    // 必须设置填充样式 （坚实的前景
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    // 设置填充前景色 方式一：自定义；
/*                XSSFColor xssfColor = new XSSFColor(new Color(236, 255, 243), new DefaultIndexedColorMap());
                ((XSSFCellStyle)cellStyle).setFillForegroundColor(xssfColor);*/
                    // 方式二：枚举色
                    Iterator<E> iterator = allOf.iterator();
                    if (!iterator.hasNext()) {
                        break;
                    }
                    E color = iterator.next();
                    iterator.remove();
                    Short idx = getIndex.apply(color);
                    cellStyle.setFillBackgroundColor(idx);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(color.toString());
                }
            }
            Row row2 = sheet.getRow(1);
            Cell cell = row2.getCell(1);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.RED1.index);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(cellStyle);

            workbook.write(fos);
        }
    }
}
