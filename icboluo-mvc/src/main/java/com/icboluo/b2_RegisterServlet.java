package com.icboluo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 请求体及乱码处理
 */
@WebServlet(urlPatterns = "/registerServlet")
public class b2_RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 乱码处理放在上面
        request.setCharacterEncoding("utf-8");
        // 提交中文post会乱码哎, get已经不会了
        String username = request.getParameter("username");
        // 获取表单多个提交的数据
        String hobby = request.getParameter("hobby");
        // 获取整个表单提交的数据
        Map<String, String[]> map = request.getParameterMap();
    }
}
