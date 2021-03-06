package com.icboluo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 读cell中元素的时候如果是数字，不能直接读成字符串
 *
 * @author icboluo
 */
@Slf4j
public class ExcelHelper {
    /**
     * excel数据转换成java对象数据
     *
     * @param filePathName 文件路径+地址
     */
    public static void workbookToList(String filePathName) {
        Workbook workbook = ExcelHelper.getWorkbook(filePathName);
        Objects.requireNonNull(workbook, "workbook不能为null");
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            //判断sheet是否有数据
            if (sheetWhetherHaveData(sheet)) {
                continue;
            }
        }
    }

    /**
     * 设置模板的header 和 content
     *
     * @return 一个样式东西吧
     */
    public static HorizontalCellStyleStrategy setCellStyle() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.PINK.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteCellStyle.setWriteFont(headWriteFont);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    /**
     * excel下载
     * 1.写响应参数
     * 2.获得输出流并写入数据
     *
     * @param response
     */
    public static void download(HttpServletResponse response) throws IOException {
        HttpHelper.writeDownloadData(response, "test.xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
 /*           WriteSheet writeSheet = EasyExcel.writerSheet(0, "sheet名称").head(IcOnhandnumExVO.class).build();
            excelWriter.write(list, writeSheet);*/
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

    }

    /**
     * 获得单行数据的集合
     *
     * @param row 当前行
     * @return 行数据集合
     */
    private static List<String> getRowData(Row row) {
        if (row == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = row.getCell(j);
            String value = getCellValue(cell);
            list.add(value);
        }
        return list;
    }

    /**
     * 获得当前的excel表
     *
     * @param filePathName 文件路径+名称
     * @return excel表对象
     */
    public static Workbook getWorkbook(String filePathName) {
        //判断文件
        if (StringUtils.isEmpty(filePathName)) {
            log.error("文件不存在");
            return null;
        }
        File file = new File(filePathName);
        //判断格式
        if (!file.getName().endsWith("xls") && !file.getName().endsWith("xlsx")) {
            log.error("文件不是excel");
            return null;
        }
        //创建输入流对象
        InputStream is = FileHelper.getInputStream(file);
        Workbook workbook = null;
        //判断excel版本号
        if (file.getName().endsWith("xls")) {
            try {
                workbook = new HSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (file.getName().endsWith("xlsx")) {
            try {
                workbook = new XSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    /**
     * 下一行是否有数据
     *
     * @param sheet      表
     * @param presentRow 当前行
     * @return 如果下一行有数据返回true 否则返回false
     */
    private static boolean nextRowWhetherHaveData(Sheet sheet, int presentRow) {
        Row nextRow = sheet.getRow(presentRow + 1);
        return rowWhetherHaveData(nextRow);
    }

    /**
     * 当前行是否有数据
     *
     * @param row 当前行
     * @return 如果有数据返回true 否则返回false
     */
    private static boolean rowWhetherHaveData(Row row) {
        if (row == null || row.getPhysicalNumberOfCells() <= 0) {
            return false;
        }
        boolean b = true;
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            String value = getCellValue(row.getCell(i));
            b = b && StringUtils.isEmpty(value);
        }
        return !b;
    }

    /**
     * 单个工作表是否有数据
     *
     * @param sheet 当前工作表
     * @return 如果有数据返回true 否则返回false
     */
    private static boolean sheetWhetherHaveData(Sheet sheet) {
        return sheet != null && sheet.getPhysicalNumberOfRows() > 0;
    }


    /**
     * 将单元格元素转换成为java值
     *
     * @param cell 单元格元素
     * @return java值
     */
    private static String getCellValue(Cell cell) {
        if (cell == null || "".equals(cell.toString().trim())) {
            return null;
        }
        CellType cellType = cell.getCellTypeEnum();
        //存放值
        String value = "";
        //字符串
        if (cellType == CellType.STRING) {
            value = cell.getStringCellValue().trim();
            value = StringUtils.isEmpty(value) ? "" : value;
        }
        if (cellType == CellType.NUMERIC) {
            //判断日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = dff.format(cell.getDateCellValue());
            } else {
                //设置数据格式（"#.######"是几位小数）
                value = new DecimalFormat("#.######").format(cell.getNumericCellValue());
            }
        }
        if (cellType == CellType.BOOLEAN) {
            value = String.valueOf(cell.getBooleanCellValue());
        }
        return value;
    }

    @SneakyThrows(IOException.class)
    public static <T> void exportExcel(HttpServletResponse response, List<String> titleId, Class<T> clazz, List<T> data) {
        HttpHelper.writeDownloadData(response, "aaa.xlsx");
        setProper(clazz, titleId);
        ExcelWriter ew = EasyExcel.write(response.getOutputStream(), clazz).includeColumnFiledNames(titleId).build();
        WriteSheet ws = EasyExcel.writerSheet("aa").build();
        ew.write(data, ws);
        ew.finish();
    }

    @SneakyThrows({NoSuchFieldException.class, IllegalAccessException.class})
    private static <T> void setProper(Class<T> clazz, List<String> titleId) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            for (int i = 0; i < titleId.size(); i++) {
                if (!titleId.get(i).equals(field.getName())) {
                    continue;
                }
                ExcelProperty ep = field.getAnnotation(ExcelProperty.class);
                //获取 stu 这个代理实例所持有的 InvocationHandler
                InvocationHandler ih = Proxy.getInvocationHandler(ep);
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                Field hField = ih.getClass().getDeclaredField("memberValues");
                // 因为这个字段事 private final 修饰，所以要打开权限
                hField.setAccessible(true);
                // 获取 memberValues
                Map memberValues = (Map) hField.get(ih);
                memberValues.put("index", i);
                log.warn("index={},name={}", i, field.getName());
            }
        }
    }
}
