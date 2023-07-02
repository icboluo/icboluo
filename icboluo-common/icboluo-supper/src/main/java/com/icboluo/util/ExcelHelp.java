package com.icboluo.util;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author icboluo
 * @since 2023-06-30 20:43
 */
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
}
