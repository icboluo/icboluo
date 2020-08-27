package com.icboluo.ee.tomcat.Day03.Demo02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//重定向
@WebServlet(urlPatterns = "/myServlet02")
public class MyServlet02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String msg = request.getParameter("msg");
        System.out.println(msg);

        response.setStatus(302);
        response.setHeader("location","/helpPeopleServlet02");
    }
}
