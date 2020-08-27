package com.icboluo.ee.tomcat.Day03.Demo04;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
