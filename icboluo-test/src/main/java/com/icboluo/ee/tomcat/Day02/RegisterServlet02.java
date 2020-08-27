package com.icboluo.ee.tomcat.Day02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 请求体及乱码处理
 */
@WebServlet(urlPatterns = "/registerServlet02")
public class RegisterServlet02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//乱码处理放在上面
        String username = request.getParameter("username");//提交中文post会乱码哎,get已经不会了
        String hobby = request.getParameter("hobby");//获取表单多个提交的数据
        Map<String, String[]> map = request.getParameterMap();//获取整个表单提交的数据
    }
}
