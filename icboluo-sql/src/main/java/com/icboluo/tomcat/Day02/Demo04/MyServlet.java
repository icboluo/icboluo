package com.icboluo.tomcat.Day02.Demo04;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
