package com.icboluo.util;

import com.alibaba.excel.support.ExcelTypeEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

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
        // 这个会造成中文乱码，需要先url编码后解码
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
        String fileName = mf.getOriginalFilename();
        if (fileName == null || !(fileName.endsWith(ExcelTypeEnum.XLSX.getValue()) || fileName.endsWith(ExcelTypeEnum.XLS.getValue()))) {
            throw new IcBoLuoI18nException("{}.not.excel", new Object[]{fileName});
        }
        try (Workbook workbook = WorkbookFactory.create(mf.getInputStream())) {
            boolean b1 = workbook instanceof XSSFWorkbook && Objects.requireNonNull(mf.getOriginalFilename()).endsWith(ExcelTypeEnum.XLS.getValue());
            boolean b2 = workbook instanceof HSSFWorkbook && Objects.requireNonNull(mf.getOriginalFilename()).endsWith(ExcelTypeEnum.XLSX.getValue());
            if (b1 || b2) {
                throw new IcBoLuoI18nException("excel.suffix.not.match");
            }
        }
    }

    /**
     * 校验文件大小
     *
     * @param mf 文件
     */
    public static void validateFile(MultipartFile mf) {
        validateFile(mf, FileHelper.DEFAULT_MAX_SIZE);
    }

    /**
     * 校验文件大小
     *
     * @param mf   文件
     * @param size 文件限制大小
     */
    private static void validateFile(MultipartFile mf, long size) {
        if (mf == null || mf.isEmpty()) {
            throw new IcBoLuoI18nException("file.is.empty");
        }
        // MultipartFile.getSize 的单位是字节
        if (mf.getSize() > size) {
            throw new IcBoLuoI18nException("file.too.large");
        }
    }
}
