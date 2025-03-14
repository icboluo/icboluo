package com.icboluo.util.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.I18nException;
import com.icboluo.util.IoHelper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author icboluo
 * @since 2023-06-30 20:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelUtil {

    /**
     * Excel导出设置Content
     *
     * @param response 响应
     * @param name     文件名称
     */
    public static void setContent(HttpServletResponse response, String name) {
        // 这个会造成中文乱码，需要先url编码后解码,这个不是标准的URL文件传参，使用工具(postman...)导出会显示文件名中文乱码（因前台适配的是这种方式，浏览器导出不会有乱码问题）
        // response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
        // 这个是标准的语法，不过2种都需要前段支持下
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8" + name + ".xlsx");
    }

    /**
     * 0168 Excel 数字转换为列号
     *
     * @param n 数字，从1开始
     * @return 列英文名称
     */
    public static String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }

    /**
     * 0171 Excel 列号转数字
     * 我们可以发现 BCM=(2*26+3)26+13
     *
     * @param columnTitle 列英文名称，不区分大小写
     * @return 列对应的序号，从1开始
     */
    public static int titleToNumber(String columnTitle) {
        if (columnTitle == null) {
            return -1;
        }
        int sum = 0;
        for (char ch : columnTitle.toUpperCase().toCharArray()) {
            sum *= 26;
            sum += ch - 'A' + 1;
        }
        return sum;
    }

    /**
     * 文件名称校验 次方法应该属于FileUtil
     *
     * @param mf 上传文件
     */
    public static void xlsAndXlsxValid(MultipartFile mf) throws IOException {
        xlsAndXlsxNameValid(mf);
        // 此块create是一个耗时函数，耗时会随着excel变大而变多，为了提高效率，可以不调用
        // 实测100行数据此块耗时36ms，监听器耗时48ms
        // 5万行数据此块耗时3.11s，监听器耗时5.49s，无用校验耗时占比1/3 过高
        // 10万行数据直接超出最大长度，报错了，实际上不create是可以导入成功的
        try (Workbook workbook = WorkbookFactory.create(mf.getInputStream())) {
            boolean b1 = workbook instanceof XSSFWorkbook && Objects.requireNonNull(mf.getOriginalFilename()).endsWith(ExcelTypeEnum.XLS.getValue());
            boolean b2 = workbook instanceof HSSFWorkbook && Objects.requireNonNull(mf.getOriginalFilename()).endsWith(ExcelTypeEnum.XLSX.getValue());
            if (b1 || b2) {
                throw new I18nException("excel.suffix.not.match");
            }
        }
    }

    public static void xlsAndXlsxNameValid(MultipartFile mf) {
        String fileName = mf.getOriginalFilename();
        if (fileName == null || !(fileName.endsWith(ExcelTypeEnum.XLSX.getValue()) || fileName.endsWith(ExcelTypeEnum.XLS.getValue()))) {
            throw new I18nException("{}.not.excel", new Object[]{fileName});
        }
    }

    /**
     * 获取导入列表
     *
     * @param listener 读取监听器
     * @param mf       文件
     * @param <T>      数据类型
     * @return 数据列表
     */
    @SneakyThrows
    public static <T> List<T> getList(ValidHeadListener<T> listener, MultipartFile mf) {
        xlsAndXlsxNameValid(mf);
        try (InputStream is = mf.getInputStream(); ExcelReader er = EasyExcelFactory.read(is).build()) {
            ReadSheet rs = EasyExcelFactory.readSheet(0).registerReadListener(listener).head(listener.getClass()).headRowNumber(listener.getHeadRowNumber()).build();
            er.read(rs);
        } catch (IOException exception) {
            throw new I18nException("excel.parse.error");
        }
        if (listener.getList().isEmpty()) {
            throw new I18nException("excel.is.empty");
        }
        return listener.getList();
    }


    @SneakyThrows
    public static <T> List<T> read(MultipartFile mf, ExcelListener<T> listener) {
        try (InputStream is = mf.getInputStream()) {
            return read(is, mf.getOriginalFilename(), listener);
        }
    }

    @SneakyThrows
    public static <T> List<T> readThenValid(MultipartFile mf, ExcelListener<T> listener) {
        try (InputStream is = mf.getInputStream()) {
            List<T> list = read(is, mf.getOriginalFilename(), listener);
            listener.validEmpty();
            listener.validBody();
            return list;
        }
    }

    public static <T> List<T> read(InputStream is, ExcelListener<T> listener) {
        return read(is, null, listener);
    }

    /**
     * 读取Excel文件(该方法使用场景是比较特殊的，需要获取到文件名后缀，一般情况下，直接传入File更合适
     *
     * @param is           输入流
     * @param filePathName 文件名称
     * @param listener     监听器
     * @param <T>          数据类型
     * @return 读取列表
     */
    public static <T> List<T> read(InputStream is, String filePathName, ExcelListener<T> listener) {
        try (ExcelReader er = createExcelReader(filePathName, is)) {
            ReadSheet rs = EasyExcelFactory.readSheet(0).registerReadListener(listener).head(listener.getClazz()).headRowNumber(listener.getHeadRowNumber()).build();
            if (er != null) {
                er.read(rs);
            }
        }
        return listener.getList();
    }

    public static <T> void readBySheetNameThenValid(ExcelReader excelReader, String sheetName, ExcelListener<T> listener) {
        ReadSheet readSheet = EasyExcelFactory.readSheet(sheetName).head(listener.getClazz()).headRowNumber(listener.getHeadRowNumber()).registerReadListener(listener).build();
        excelReader.read(readSheet);

        listener.validEmpty();
        listener.validBody(true);
    }

    private static ExcelReader createExcelReader(String fileName, InputStream is) {
        if (IoHelper.validateFileName(fileName, ExcelTypeEnum.CSV.getValue())) {
            return EasyExcelFactory.read(is).excelType(ExcelTypeEnum.CSV).charset(Charset.forName("gbk")).build();
        } else if (IoHelper.validateFileName(fileName, ExcelTypeEnum.XLSX.getValue(), ExcelTypeEnum.XLS.getValue(), "xlsm")) {
            return EasyExcelFactory.read(is).build();
        }
        // 如果文件名不是Excel，则抛异常
        if (org.springframework.util.StringUtils.hasText(fileName)) {
            throw new I18nException(ReEnum.NOT_EXCEL);
        } else {
            // 默认使用xlsx，xlsm文件读取
            return EasyExcelFactory.read(is).build();
        }
    }

    /**
     * Excel 导出
     *
     * @param list     数据
     * @param clazz    导出类型
     * @param fileName 文件名（有没有.xlsx后缀都可以）和sheet名
     * @param <T>      数据类型
     */
    public static <T> void write(List<T> list, Class<T> clazz, String fileName) {
        write(list, clazz, new ExcelWriteConfig(fileName));
    }

    /**
     * 导出列表
     *
     * @param list   需要导出的数据，该数据应该存在 @Excel注解功能
     * @param clazz  导出列表的class
     * @param config 配置
     * @param <T>    导出数据的类型
     * @see SimpleWriteConfig 简单的导出配置，拥有自适配列宽，和表头、体样式的调整
     */
    public static <T> void write(List<T> list, Class<T> clazz, ExcelWriteConfig config) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return;
        }
        setContent(response, config.getFileName());
        write(list, response, clazz, config);
    }

    @SneakyThrows
    public static <T> void write(List<T> list, HttpServletResponse response, Class<T> clazz, ExcelWriteConfig config) {
        ExcelResolve<T> resolve = new ExcelResolve<>(clazz);
        try (ServletOutputStream sos = response.getOutputStream(); ExcelWriter ew = EasyExcelFactory.write(sos).build()) {
            ExcelWriterSheetBuilder builder = EasyExcelFactory.writerSheet(0);
            config.getWriteHandlerList().forEach(builder::registerWriteHandler);
            WriteSheet writeSheet = builder.head(resolve.head()).relativeHeadRowIndex(0).build();
            ew.write(resolve.body(() -> list), writeSheet);
        }
    }

    public static <T> void write(InputStream is, List<T> list, HttpServletResponse response, Class<T> clazz, String fileName) {
        writeOrWithTemplate(is, list, response, clazz, new ExcelWriteConfig(fileName) {
        });
    }

    @SneakyThrows
    public static <T> void writeOrWithTemplate(InputStream is, List<T> list, HttpServletResponse response, Class<T> clazz, ExcelWriteConfig config) {
        setContent(response, config.getFileName());
        ServletOutputStream os = response.getOutputStream();
        writeOrWithTemplate(is, list, os, clazz, config);
    }

    @SneakyThrows
    public static <T> void writeOrWithTemplate(InputStream is, List<T> list, OutputStream os, Class<T> clazz, ExcelWriteConfig config) {
        if (list.size() > 100000) {
            // 数据量超过
            throw new I18nException("the.data.volume.exceeds.{0}", new Object[]{100000});
        }

        ExcelResolve<T> resolve = new ExcelResolve<>(clazz);
        Optional<String> sheetName = Optional.ofNullable(config.getSheetName());
        try (ExcelWriter ew = createWriter(os, is)) {
            ExcelWriterSheetBuilder builder = EasyExcelFactory.writerSheet(0, sheetName.orElse(null));
            config.getWriteHandlerList().forEach(builder::registerWriteHandler);
            // 输入流等于空的时候，说明是自行生成文件头（不是根据模板生成文件
            if (is == null) {
                builder = builder.head(resolve.head());
            }
            WriteSheet writeSheet = builder.build();
            ew.write(resolve.body(() -> list), writeSheet);
        }
    }


    private static ExcelWriter createWriter(OutputStream os, InputStream is) {
        if (is == null) {
            return EasyExcelFactory.write(os).build();
        } else {
            return EasyExcelFactory.write(os).withTemplate(is).build();
        }
    }
}
