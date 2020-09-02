package com.icboluo.tomcat.Day01.optimize03;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
实现类在源码中为GenericServlet，sum提供一个专门解决http协议的HttpServlet extends GenericServlet
需要extends HttpServlet
	HttpServlet类也是一个抽象类，为什么没有像GenericServlet一样，将service方法设计成抽象方法供用户扩展呢？
	1、HttpServlet中的service方法统一接收所有的客户端发送的HTTP请求；
2、HTTP请求又可分为：GET,POST等请求，service方法在接收到请求后根据不同的请求方式，调用对应的方法来处理请求，如：doGet处理的是get请求，doPost处理的是post请求；
3、开发人员根据客户端发送的不同请求方式，重写不同的方法来处理请求即可；
 */
public class Demo02 extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("hellohttpservlet");
        System.out.println("hellohttpservlet");
    }
}