package com.icboluo.ee.tomcat.Day02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
域对象的应用场景：请求转发过程中，共享数据
 request的生命周期：request到达服务器后，tomcat创建，经service方法后，销毁request，生命周期结束
 请求跳转：servlet-->html,jsp,servlet
  */
@WebServlet(urlPatterns = "/requestScopeDemoServlet")
public class RequestScopeDemoServlet03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usernaem", "班长");
        String name = request.getParameter("username");//获取浏览器提交的数据
        String username = (String) request.getAttribute("username");//属性，获取自己设置的值
        request.removeAttribute("username");
    }
}
