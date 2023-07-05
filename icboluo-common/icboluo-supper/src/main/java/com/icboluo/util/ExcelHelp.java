package com.icboluo.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @since 2023-06-30 20:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelHelp {
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
}
