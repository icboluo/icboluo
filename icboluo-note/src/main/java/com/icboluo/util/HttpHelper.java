package com.icboluo.util;

import javax.servlet.http.HttpServletResponse;

/**
 * @author icboluo
 * @date 2020/7/22 15:02
 */
public class HttpHelper {
    /**
     * 下载的时候，给http响应里面写入对应类型参数
     */
    public static void writeDownloadData(HttpServletResponse response) {
        //设置ConetentType CharacterEncoding Header,需要在excelWriter.write()之前设置
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=test.xlsx");
        response.setContentType("mutipart/form-data");
    }
}
