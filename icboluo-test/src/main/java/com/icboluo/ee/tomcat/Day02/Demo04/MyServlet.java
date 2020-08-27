package com.icboluo.ee.tomcat.Day02.Demo04;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/myServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        String msg = request.getParameter("msg");
        //处理数据
        request.setAttribute("info", "帮忙面试一下！");
        //dispatch调度，分配
        RequestDispatcher helppeople = request.getRequestDispatcher("/helpPeopleServlet");
        helppeople.forward(request, response);
    }
}
