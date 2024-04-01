package com.icboluo.controller;


import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.constant.FileRelativePathPre;
import com.icboluo.object.view.StudentVO;
import com.icboluo.service.ExcelService;
import com.icboluo.service.StudentService;
import com.icboluo.service.impl.StudentServiceImpl;
import com.icboluo.util.excel.ExcelExportResolve;
import com.icboluo.util.excel.ExcelUtil;
import com.icboluo.util.ExcelHelper;
import com.icboluo.util.IoUtil;
import com.icboluo.util.excel.ValidHeadBodyListener;
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
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * @author icboluo
 */
@RestController
@RequestMapping("excel")
public class ExcelController {
    @Resource
    private ExcelService excelService;
    @Resource
    private ReadExcelEntity readExcelEntity;
    @Resource
    private WriteExcelEntity writeExcelEntity;

    private final StudentService studentService = new StudentServiceImpl();
    /**
     * 将配置文件中的属性读取出来
     */
    @Value("${read-excel-entity.excel-path}")
    private String excelPath;

    @GetMapping("read")
    public void read() {
        excelService.readDbDocument(excelPath, readExcelEntity.getSheetName());
    }

    @GetMapping("write")
    public void write() {
        excelService.write(writeExcelEntity.getDatabase(), writeExcelEntity.getTableName());
    }

    @GetMapping("customizationTitleExport")
    public void customizationTitleExport(HttpServletResponse response) {
        List<StudentVO> students = studentService.generateList(10).stream().map(StudentVO::new).toList();
        List<String> strings = Arrays.asList("name", "id", "age");
        ExcelHelper.exportExcel(response, strings, StudentVO.class, students);
    }

    @GetMapping("importStudent")
    public void importStudent(HttpServletRequest request) throws IOException {
        LocalDateTime gmtStart = LocalDateTime.now();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mf = multipartRequest.getFile("data");
        assert mf != null;
        IoUtil.validateFile(mf);
        ExcelUtil.xlsAndXlsxValid(mf);
        ValidHeadBodyListener<StudentVO> listener = new ValidHeadBodyListener<>(StudentVO.class, 1);
        try (InputStream is = mf.getInputStream(); ExcelReader er = EasyExcelFactory.read(is).build()) {
            ReadSheet rs = EasyExcelFactory.readSheet(0)
                    .head(StudentVO.class)
                    .headRowNumber(listener.getHead())
                    .registerReadListener(listener)
                    .build();
            er.read(rs);
        }

        List<StudentVO> read = listener.getList();
        String[][] arr = listener.getArr();
        ExcelHelper.removeErrData(read, arr, listener.getHead());
        read.forEach(System.out::println);
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
                // 这个 api 会让 excel 上面空下来几行，可以做模版填充使用，给定表头模版，仅写入数据
                .relativeHeadRowIndex(0)
                .head(resolve.head())
                .sheet("学生")
                .doWrite(resolve.body(() ->
                        studentService.generateList(20).stream()
                                .map(StudentVO::new).toList()));
    }

    /**
     * 打印所有的Excel定义好的颜色
     */
    @GetMapping("writeColor")
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
