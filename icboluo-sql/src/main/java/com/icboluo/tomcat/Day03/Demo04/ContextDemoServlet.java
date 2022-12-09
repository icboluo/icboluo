package com.icboluo.tomcat.Day03.Demo04;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//servletcontext域对象
@WebServlet(urlPatterns = "/contextDemoServlet")
public class ContextDemoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2种获取context对象
        ServletContext servletContext = request.getServletContext();
        ServletContext servletContext1 = getServletContext();

        servletContext.setAttribute("name", "班长");

    }
}
