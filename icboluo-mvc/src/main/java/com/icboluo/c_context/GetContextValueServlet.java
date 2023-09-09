package com.icboluo.c_context;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/getContextValueServlet")
public class GetContextValueServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        String name = (String) servletContext.getAttribute("name");
        System.out.println(name);
        //资源路径；web资源在tomcat运行时的物理地址
        String realPath = servletContext.getRealPath("upload");
        System.out.println(realPath);
        //获取资源的mimetype：text/html
        String mimeType = servletContext.getMimeType("1.zip");
        System.out.println(mimeType);
    }
}
