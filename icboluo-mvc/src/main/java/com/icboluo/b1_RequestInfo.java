package com.icboluo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author icboluo
 * @since 2023-09-09 20:29
 */
@WebServlet(urlPatterns = "/requestInfo")
public class b1_RequestInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求行的请求方式
        String method = request.getMethod();
        System.out.println("method = " + method);

        //获取请求行的请求URL
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);

        //获取请求行的请求IP，当请求的ip为localhost的时候，获取的是IPv6的格式
        String remoteAddr = request.getRemoteAddr();//address
        System.out.println("remoteAddr = " + remoteAddr);

        //获取请求行的协议版本号
        String protocol = request.getProtocol();
        System.out.println("protocol = " + protocol);
        System.out.println("----------------------------------------------------------");
        String host = request.getHeader("host");
        String header = request.getHeader("user-agent");
        String referer = request.getHeader("referer");
    }
}

