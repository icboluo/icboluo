package com.icboluo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.constant.FileRelativePathPre;
import com.icboluo.object.business.StudentBO;
import com.icboluo.object.client.RowCO;
import com.icboluo.object.excel.StudentExcel;
import com.icboluo.object.view.StudentVO;
import com.icboluo.service.ExcelService;
import com.icboluo.service.StudentService;
import com.icboluo.service.impl.StudentServiceImpl;
import com.icboluo.util.ExcelExportResolve;
import com.icboluo.util.ExcelHelper;
import com.icboluo.util.listenter.RowDataListener;
import com.icboluo.util.listenter.StudentListener;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    private StudentService studentService = new StudentServiceImpl();
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
        List<StudentBO> data = new ArrayList<>();
        StudentBO student = new StudentBO();
        student.setAge(18);
        student.setId("22");
        data.add(student);
        List<String> strings = Arrays.asList("name", "id", "age");
        ExcelHelper.exportExcel(response, strings, StudentBO.class, data);
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
        StudentListener listener = new StudentListener(StudentExcel.class);
        List<StudentExcel> read1 = ExcelHelper.read(mf, listener, StudentExcel.class);
        List<StudentExcel> read = StudentExcel.generatorFactory(2);
        String[][] arr = ExcelHelper.validateContext(read);
        ExcelHelper.removeErrData(read, arr);
        read.forEach(System.out::println);
    }

    @GetMapping("/exportExcel")
    public void exportExcel() {
        EasyExcel.write(FileRelativePathPre.NOTE + FileRelativePathPre.RESOURCES + "color.xlsx").head(StudentBO.class).build();
    }

    /**
     * 自定义列导出
     */
    @GetMapping("customizationExport")
    public void customizationExport() throws IllegalAccessException {
        String fileName = "title_customization_" + System.currentTimeMillis() + ".xlsx";
        File file = new File(FileRelativePathPre.NOTE + FileRelativePathPre.RESOURCES + "dir/" + fileName);
        ExcelExportResolve<StudentVO> resolve = new ExcelExportResolve<>(StudentVO.class);
        resolve.setSortFieldName(Arrays.asList("age", null, "name", "code", "id"));
        EasyExcelFactory.write(file)
                // 实测这个自定义列宽最大值太宽了，需要修改为 100|150
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .head(resolve.head())
                .sheet("学生")
                .doWrite(resolve.body(() ->
                        studentService.generateList(20).stream()
                                .map(StudentVO::new).toList()));
    }

    /**
     * 打印所有的Excel定义好的颜色
     */
    @GetMapping("/writeColor")
    public void writeColor() {
        writeColor(EnumSet.allOf(HSSFColor.HSSFColorPredefined.class), HSSFColor.HSSFColorPredefined::getIndex, 3);
        writeColor(EnumSet.allOf(IndexedColors.class), IndexedColors::getIndex, 12);
    }

    @SneakyThrows
    private <E extends Enum<E>> void writeColor(EnumSet<E> allOf, Function<E, Short> getIndex, int firstRow) {
        File file = new File(FileRelativePathPre.NOTE + FileRelativePathPre.RESOURCES + "dir/color.xlsx");
        int setSize = allOf.size();
        int rowSize = 8;
        int rowNum = (setSize + rowSize - 1) / rowSize;
        // 不可能仅仅把file加载到内存中，修改内存就可以持久化；还需要一个fos来手动持久化
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
                    cellStyle.setFillForegroundColor(idx);
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
