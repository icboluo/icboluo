package com.icboluo.z3_jedis;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/queryAllServlet3")
public class QueryAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        //处理数据
        StudentService studentService = new StudentService();
        //返回值1.从redis中查询的数据2.从数据库找那个查询的数据
        String jsonResult=studentService.queryAll();
        //响应数据
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(jsonResult);
    }
}
