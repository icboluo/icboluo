package com.icboluo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
域对象的应用场景：请求转发过程中，共享数据
 request的生命周期：request到达服务器后，tomcat创建，经service方法后，销毁request，生命周期结束
 请求跳转：servlet-->html,jsp,servlet
  */
@WebServlet(urlPatterns = "/requestScope")
public class b3_RequestScope extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usernaem", "班长");
        // 获取浏览器提交的数据
        String name = request.getParameter("username");
        // 属性，获取自己设置的值
        String username = (String) request.getAttribute("username");
        request.removeAttribute("username");
    }
}
