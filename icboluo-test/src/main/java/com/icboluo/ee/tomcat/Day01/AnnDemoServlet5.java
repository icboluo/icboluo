package com.icboluo.ee.tomcat.Day01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
xml中配置过多的文件不易于管理，应该使用注解开发
加一个注解就可以了(@WebServlet("/Test02"))，映射路径就是url，也可以写name，url，这个注解相当于web.xml
注解自己配置路径：file-set-editor-fileandcode-web-javacode-..
 */
@WebServlet(urlPatterns = "/annDemoServlet")//默认值value，相当于web.xml中的url
public class AnnDemoServlet5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is doget method of anndemoservelt");
        String method = request.getMethod();
        System.out.println("处理" + method + "业务逻辑");
    }
}
